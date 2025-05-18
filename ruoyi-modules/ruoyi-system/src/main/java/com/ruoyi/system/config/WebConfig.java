package com.ruoyi.system.config;

import com.ruoyi.system.common.interceptor.PermissionCodeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author DavidLoman
 * @create 2025-05-18 15:03
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private PermissionCodeInterceptor permissionCodeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissionCodeInterceptor).addPathPatterns("/**");
    }
}