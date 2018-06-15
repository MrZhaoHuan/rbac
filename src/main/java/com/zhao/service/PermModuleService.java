package com.zhao.service;

import com.zhao.dao.PermissionModuleMapper;
import com.zhao.pojo.PermissionModule;
import com.zhao.vo.PermModuleVo;
import com.zhao.web.exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-14 10:26
 * @描述
 */
@Service
public class PermModuleService {
    private PermissionModuleMapper mapper;
    private Integer ROOT = 0;

    @Autowired
    public void setMapper(PermissionModuleMapper mapper) {
        this.mapper = mapper;
    }

    public void save(PermissionModule permissionModule) {
        permissionModule.setId(null);
        int count = mapper.selectPermModuleCountBySameLevel(permissionModule.getParentId(), permissionModule.getName(), permissionModule.getId());
        if (count > 0) {
            throw new ParamException("同一个父级权限模块下，名称不能重复");
        }
        mapper.insertSelective(permissionModule);
    }

    public String getPermModuleLevel(Integer parentId) {
        String permModuleLevel = mapper.selectPermModuleLevel(parentId);
        return permModuleLevel == null ? "0" : permModuleLevel;
    }

    public void update(PermissionModule permModule) {

        int count = mapper.selectPermModuleCountBySameLevel(permModule.getParentId(), permModule.getName(), permModule.getId());
        if (count > 0) {
            throw new ParamException("同一个父级权限模块下，名称不能重复");
        }
        PermissionModule dbPermModule = mapper.selectByPrimaryKey(permModule.getId()); //数据库原始值
        PermissionModule parentPermModule = new PermissionModule();
        if (permModule.getParentId().equals(0)) {
            //如果是最顶层
            parentPermModule.setLevel("0");
            parentPermModule.setParentId(0);
        } else {
            parentPermModule = mapper.selectByPrimaryKey(permModule.getParentId());
        }

        if (!dbPermModule.getParentId().equals(permModule.getParentId())) {
            //查询所有下级
            List<PermissionModule> childPermModules = mapper.selectAllChildPermModule(dbPermModule.getLevel() + "." + dbPermModule.getId());
            for (PermissionModule child : childPermModules) {
                //修改level属性
                child.setLevel(child.getLevel().replace(dbPermModule.getLevel() + "." + dbPermModule.getId(), parentPermModule.getLevel() + "." + parentPermModule.getId()));
            }
            //更新所有下级部门信息
            if (childPermModules.size() > 0) {
                mapper.batchUpdate(childPermModules);
            }
        }
        //更新当前部门信息
        permModule.setLevel(parentPermModule.getLevel() + "." + parentPermModule.getId());
        mapper.updateByPrimaryKeySelective(permModule);
    }


    public List<PermModuleVo> getPermModuleList() {
        List<PermModuleVo> permModuleVoList = mapper.selectAllPermModuleVo();
        List<PermModuleVo> resultList = new ArrayList<>();
        //如果是空
        if (permModuleVoList == null || permModuleVoList.size() == 0) {
            return resultList;
        }
        //根据seq字段排序
        Collections.sort(permModuleVoList, new Comparator<PermModuleVo>() {
            @Override
            public int compare(PermModuleVo o1, PermModuleVo o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });

        //查找根节点
        ROOT = 0;
        for (PermModuleVo vo : permModuleVoList) {
            if (ROOT.equals(vo.getParentId())) {
                resultList.add(vo);
            }
        }
        return findDeptTree(permModuleVoList, resultList);
    }

    private List<PermModuleVo> findDeptTree(List<PermModuleVo> permModuleVoList, List<PermModuleVo> resultList) {
        //查找同一层级
        for (int i = 0; i < resultList.size(); i++) {
            PermModuleVo permModuleVo = resultList.get(i);
            ROOT = permModuleVo.getId();
            //todo: 这里可以用这个工具类的，就不用遍历了 org.apache.commons.collections.MultiMap
            for (PermModuleVo vo : permModuleVoList) {
                if (ROOT.equals(vo.getParentId())) {
                    permModuleVo.getPermModuleVoList().add(vo);
                }
            }
            //递归查找下级节点
            findDeptTree(permModuleVoList, permModuleVo.getPermModuleVoList());
        }
        return resultList;
    }

    public void deletePermModuleById(int permModuleId) {
        //存在下级权限模块情况下不能删除
        int childCount =  mapper.selectChildCount(permModuleId);
        if(childCount>0){
            throw new ParamException("当前权限模块存在下级,不能删除");
        }
        //存在权限的情况下不能删除
        int permCount =  mapper.selectPermCountByPermModule(permModuleId);
        if(permCount>0){
            throw new ParamException("当前权限模块下存在权限,不能删除");
        }
        //删除当前权限模块
        mapper.deleteByPrimaryKey(permModuleId);
    }
}