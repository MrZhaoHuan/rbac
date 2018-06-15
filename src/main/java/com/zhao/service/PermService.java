package com.zhao.service;

import com.zhao.dao.PermissionMapper;
import com.zhao.pojo.Permission;
import com.zhao.util.PageObj;
import com.zhao.vo.PermVo;
import com.zhao.web.exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-14 15:23
 * @描述
 */
@Service
public class PermService {
    private PermissionMapper mapper;

    @Autowired
    public void setMapper(PermissionMapper mapper) {
        this.mapper = mapper;
    }

     /*
      *@描述  查询权限列表
      *@创建时间 2018/6/14 15:26
      *@参数
      *@返回值
      **/
    public PageObj getPermPageList(int permModuleId, PageObj pageObj) {
        int pageNo = pageObj.getPageNo()<1?1:pageObj.getPageNo();
        int pageSize = pageObj.getPageSize()<0?0:pageObj.getPageSize();
        int total = 0;  //总条数
        //更正页码和每页数量
        pageObj.setPageNo(pageNo);
        pageObj.setPageSize(pageSize);
        //查询权限列表
        List<PermVo> permVos = mapper.selectPageList(permModuleId,(pageNo-1)*pageSize,pageSize);
        if(permVos==null || permVos.size()==0){
            permVos = new ArrayList<>();
        }else{
            total =  permVos.get(0).getTotal();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("data",permVos);
        pageObj.setCode("0");
        pageObj.setData(map);
        return pageObj;
    }

    public void save(Permission permission) {
        permission.setId(null);
        //校验权限名称
        validPermName(permission);
        //保存
        mapper.insertSelective(permission);
    }

    private void validPermName(Permission permission){
        //校验：同1个权限模块下，不能有相同名称的权限
        int count  =  mapper.selectCountBySamePermModule(permission.getPermmoduleId(),permission.getName(),permission.getId());
        if(count>0){
            throw new ParamException("当前权限模块下,存在名称为"+permission.getName()+"的权限");
        }
    }

    public void update(Permission permission) {
        //校验权限名称
        validPermName(permission);
        //更新
        mapper.updateByPrimaryKeySelective(permission);
    }

    public List<Permission> getByUrl(String url) {
        return mapper.selectPermBySelect(url);
    }

    public List<Integer> getAllPermByUser(Integer id) {
        return mapper.getAllPermByUser(id);
    }
}