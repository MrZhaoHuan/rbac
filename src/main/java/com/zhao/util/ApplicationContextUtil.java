package com.zhao.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-12 22:49
 * @描述  获取spring容器上下文
 */
public class ApplicationContextUtil implements ApplicationContextAware{
    private static  ApplicationContext appContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    public static ApplicationContext getAppContext(){
         return  appContext;
    }
}