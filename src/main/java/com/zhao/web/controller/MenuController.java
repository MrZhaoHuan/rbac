package com.zhao.web.controller;

import com.zhao.service.MenuService;
import com.zhao.util.JsonObj;
import com.zhao.util.RequestHolder;
import com.zhao.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-15 21:43
 * @描述  查询用户的菜单权限，动态遍历生成
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public JsonObj list(){
        List<MenuVo> menuVos = menuService.getUserMenuList(RequestHolder.getLoginUser().getId());
        return JsonObj.success().setData(menuVos);
    }
}