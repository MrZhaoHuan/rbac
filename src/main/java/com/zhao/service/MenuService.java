package com.zhao.service;

import com.zhao.dao.PermissionMapper;
import com.zhao.util.SuperAdminUtil;
import com.zhao.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-15 21:48
 * @描述
 */
@Service
public class MenuService{
    private PermissionMapper permMapper;

    @Autowired
    public void setPermMapper(PermissionMapper permMapper) {
        this.permMapper = permMapper;
    }

    public List<MenuVo> getUserMenuList(Integer userId) {
        List<MenuVo> findMenuList = new ArrayList<>();
        //查询所有菜单类型的权限url
        List<MenuVo> menuPermList = permMapper.getMenuPermList();
        if(menuPermList==null || menuPermList.size()==0){
             return findMenuList;
        }
        //超级管理员返回所有菜单
        if(SuperAdminUtil.isSuperAdmin()){
            return  menuPermList;
        }

        //如果不是超级管理员,则只展示当前用户配置过的菜单
        for(MenuVo m:menuPermList){
            if(userId.equals(m.getUserId())){
                findMenuList.add(m);
            }
        }
        return  findMenuList;
    }
}