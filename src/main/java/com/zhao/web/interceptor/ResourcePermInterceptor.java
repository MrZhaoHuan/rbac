package com.zhao.web.interceptor;

import com.zhao.pojo.Permission;
import com.zhao.service.PermService;
import com.zhao.util.JsonObj;
import com.zhao.util.RequestHolder;
import com.zhao.util.SuperAdminUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-15 19:47
 * @描述 对请求的资源进行拦截
 */
public class ResourcePermInterceptor implements HandlerInterceptor{
    private String errorPage = "/user/noAuth.page";
    private PermService permService;

    @Autowired
    public void setPermService(PermService permService) {
        this.permService = permService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String url = request.getRequestURI();
        boolean isJsonReq = url.endsWith(".json");

        if(SuperAdminUtil.isSuperAdmin()){
            //超级管理员，直接放行
            return true;
        }

        //权限点sys_perm表中找不到请求url，说明不对此url拦截，放行
        //todo: 权限表中权限根据需求可能会配置成正则表达式，这里先直接全路径匹配
        List<Permission> permVos =  permService.getByUrl(url);
        if(permVos==null || permVos.size()==0){
            return true;
        }
        //如果找到的权限点全部是冻结状态,则放行
        boolean isPass = true; //是否放行
        List<Integer> validPermIds =  new ArrayList<>(); //存放状态为有效的权限id
        for(Permission p:permVos){
            if(p.getStatus().equals("0")){
                isPass = false;
                validPermIds.add(p.getId());
            }
        }
        if(isPass){
            return  true;
        }
        //判断有效的权限集合validPermIds中，当前登录用户是否有访问权限
            //查询当前登录用户的所有权限
            List<Integer> userPerms =  permService.getAllPermByUser(RequestHolder.getLoginUser().getId());
            for(Integer permId:validPermIds){
                if(userPerms.contains(permId)){
                    isPass = true;
                    break;
                }
            }

         if(isPass){
             return  true;
         }
        //对于不同的请求方式，做不同的处理
        if(isJsonReq){
            //返回json格式提示信息
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(JsonObj.error("您没有访问权限,如有需要,请联系管理员。"));
        }else{
            //返回错误页面
            //response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.sendRedirect((request.getContextPath()+errorPage));
            //response.getWriter().print("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
            //        + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + "<head>\n" + "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n"
            //        + "<title>跳转中...</title>\n" + "</head>\n" + "<body>\n" + "跳转中，请稍候...\n" + "<script type=\"text/javascript\">//<![CDATA[\n"
            //        + "window.location.href='" + (request.getContextPath()+errorPage) + "?ret='+encodeURIComponent(window.location.href);\n" + "//]]></script>\n" + "</body>\n" + "</html>\n");

        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
