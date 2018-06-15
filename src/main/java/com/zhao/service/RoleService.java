package com.zhao.service;

import com.google.common.collect.ArrayListMultimap;
import com.zhao.dao.RoleMapper;
import com.zhao.pojo.Role;
import com.zhao.pojo.RolePermission;
import com.zhao.pojo.UserRole;
import com.zhao.util.IpUtil;
import com.zhao.util.RequestHolder;
import com.zhao.util.SuperAdminUtil;
import com.zhao.vo.PermModuleVo;
import com.zhao.vo.PermRoleVo;
import com.zhao.vo.RoleVo;
import com.zhao.vo.UserRoleVo;
import com.zhao.web.exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-14 20:08
 * @描述
 */
@Service
public class RoleService {
    private RoleMapper mapper;
    private PermModuleService permModuleService;
    private UserRoleService urService;

    @Autowired
    public void setUrService(UserRoleService urService) {
        this.urService = urService;
    }

    @Autowired
    public void setPermModuleService(PermModuleService permModuleService) {
        this.permModuleService = permModuleService;
    }

    @Autowired
    public void setMapper(RoleMapper mapper) {
        this.mapper = mapper;
    }

    public List<RoleVo> getAllRole() {
        return  mapper.selectAll();
    }

    public void save(Role role) {
        role.setId(null);
        //校验：角色名称不能重复
        validRoleName(role.getId(),role.getName());
        //保存
        mapper.insertSelective(role);
    }

    public void update(Role role) {
        //校验：角色名称不能重复
        validRoleName(role.getId(),role.getName());
        //更新
        mapper.updateByPrimaryKeySelective(role);
    }

    //保存或更新时:校验角色名称是否重复
    private void validRoleName(Integer roleId,String roleName){
         int count  = mapper.selectCountByIdAndName(roleId, roleName);
        if(count>0){
            throw new ParamException("已经存在名称为"+roleName+"的角色");
        }
    }

      /*
       *@描述 查询角色权限tree
       *@创建时间 2018/6/15 10:10
       *@参数 roleId
       *@返回值 List<PermModuleVo>
       **/
    public List<PermModuleVo> getRolePermTree(int roleId) {
        //查询权限模块tree数据
        List<PermModuleVo> permModuleList = permModuleService.getPermModuleList();
        if(permModuleList.isEmpty()){
            return  permModuleList;
        }
        //查询所有权限点
        List<PermRoleVo> allPerms =  mapper.selectAllPerm();
        if(allPerms==null || allPerms.size()==0){
            return  new ArrayList();
        }
        //查询当前登录用户的权限点
        List<PermRoleVo> userPerms = SuperAdminUtil.isSuperAdmin()?allPerms:mapper.selectPermByUser(RequestHolder.getLoginUser().getId());
        //查询角色id为roleId的权限点
        List<PermRoleVo> rolePerms = mapper.selectPermByRole(roleId);

        //根据查询到的userPerms和rolePerms,更改allPerms中每个权限点的checked和hasPerm属性
        for(PermRoleVo p:allPerms){
             if(userPerms.contains(p)){
                 p.setHasPerm(true);
             }
             if(rolePerms.contains(p)){
                 p.setChecked(true);
             }
        }
        //权限点排序
        allPerms.sort(new Comparator<PermRoleVo>() {
            @Override
            public int compare(PermRoleVo o1, PermRoleVo o2) {
                return o1.getId()-o2.getId();
            }
        });
        //构造数据结构：权限模块id——权限点集合
        ArrayListMultimap<Integer, PermRoleVo> listMultimap = ArrayListMultimap.create();
        for(PermRoleVo p:allPerms){
            listMultimap.put(p.getPermModuleId(),p);
        }
        //递归:把权限点挂在权限模块permModuleList下
        recursive(permModuleList,listMultimap);

        //todo: 删除空模块节点
        //List<Integer> empIds = new ArrayList<>();
        //findEmpty(permModuleList,empIds);
        return permModuleList;
    }

    //private void isEmpty(List<PermModuleVo> pvoList,List<Integer> empIds){
    //    for(PermModuleVo pvo:pvoList){
    //        if(pvo.getPermRoleVoList().isEmpty()){
    //            findEmpty(pvo.getPermModuleVoList(),empIds);
    //        }
    //    }
    //}

    private void recursive(List<PermModuleVo> permModuleList,ArrayListMultimap<Integer, PermRoleVo> listMultimap){
        for(PermModuleVo p:permModuleList){
            List<PermRoleVo> permRoleVos = listMultimap.get(p.getId());
            if(permRoleVos!=null && permRoleVos.size()>0){
                //如果权限模块下存在权限点
                p.setPermRoleVoList(permRoleVos);
            }
            //递归遍历，绑定权限点列表
            recursive(p.getPermModuleVoList(),listMultimap);
        }
    }

    public void savePerm(Integer roleId, String aclIds) {
        //先查询原来的权限，如果传进来的aclIds和原来相同，就返回
        List<PermRoleVo> permRoleVos = mapper.selectPermByRole(roleId);
        if(permRoleVos==null){
            permRoleVos = new ArrayList<>();
        }
        //todo: jdk1.8新特性-stream
        List<Integer> perms = permRoleVos.stream().map(PermRoleVo::getId).collect(Collectors.toList());
        List<Integer> paramPerms = aclIds.trim().equals("")?new ArrayList<>():Arrays.asList(aclIds.split(",")).stream().map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toList());
        if(perms.size()==paramPerms.size()){
            perms.removeAll(paramPerms);
            if(perms.size()==0){
                return;
            }
        }
        //删除角色对应的权限
        mapper.deletePermByRole(roleId);
        //保存aclIds
        List<RolePermission> rolePermissions = new ArrayList<>();
        for(Integer permId:paramPerms){
             RolePermission rp  = new RolePermission();
             rp.setRoleid(roleId);
             rp.setPermid(permId);
             rp.setOperator(RequestHolder.getLoginUser().getUsername());
             rp.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
             rp.setOperateTime(new Date());
             rolePermissions.add(rp);
        }
        if(rolePermissions.size()>0){
            mapper.insertPermByRole(rolePermissions);
        }
    }


    public void saveUsers(Integer roleId, String userIds) {
        //先查询原来的角色下用户，如果传进来的userIds和原来相同，就返回
        List<Integer> roleUserIds = urService.selectUserIdsByRole(roleId);
        if(roleUserIds==null){
            roleUserIds = new ArrayList<>();
        }
        List<Integer> paramUserIds = userIds.trim().equals("")?new ArrayList<>():Arrays.asList(userIds.split(",")).stream().map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toList());
        if(roleUserIds.size()==paramUserIds.size()){
            roleUserIds.removeAll(paramUserIds);
            if(roleUserIds.size()==0){
                return;
            }
        }
        //删除角色对应的用户
        urService.deleteUserByRole(roleId);
        //保存userIds
        List<UserRole> saveUserRoles = new ArrayList<>();
        for(Integer userId:paramUserIds){
            UserRole ur  = new UserRole();
            ur.setUserid(userId);
            ur.setRoleid(roleId);
            ur.setOperator(RequestHolder.getLoginUser().getUsername());
            ur.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
            ur.setOperateTime(new Date());
            saveUserRoles.add(ur);
        }
        if(saveUserRoles.size()>0){
            urService.insertUsersByRole(saveUserRoles);
        }
    }


    public void deleteRole(Integer roleId) {
        //todo: 根据实际需求
        //角色下存在用户的话，不能删除
        int count  = mapper.selectUserCountByRole(roleId);
        if(count>0){
            throw new ParamException("当前角色下绑定着用户,不能删除");
        }
        //删除当前角色和角色对应的权限
        mapper.deleteByPrimaryKey(roleId);
        mapper.deletePermByRole(roleId);
    }

     /*
      *@描述  获取角色下的用户 和  非角色下的用户
      *@创建时间 2018/6/15 15:06
      *@参数
      *@返回值
      **/
    public Map<String, List<UserRoleVo>> getUsers(int roleId) {
        Map<String,List<UserRoleVo>> userMap = new HashMap<>();
        userMap.put("unselected", new ArrayList<>());
        userMap.put("selected", new ArrayList<>());

        //查询所有用户，isSelected区分是否在当前角色下
        List<UserRoleVo> userRoleVos = urService.selectAllUsersByRole(roleId);
        if(userRoleVos!=null){
            for(UserRoleVo urvo:userRoleVos){
                    if("true".equals(urvo.getIsSelected())){
                        userMap.get("selected").add(urvo);
                    }else{
                        userMap.get("unselected").add(urvo);
                    }
            }
        }
        return userMap;
    }

}