package com.ruoyi.system.common.interceptor;

import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.common.context.PermissionContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author DavidLoman
 * @create 2025-05-18 14:55
 */
@Component
public class PermissionCodeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequiresPermissions methodAnnotation = handlerMethod.getMethodAnnotation(RequiresPermissions.class);
            if (methodAnnotation != null) {
                String[] perms = methodAnnotation.value();
                // 存入 ThreadLocal
                PermissionContext.set(perms);
                System.out.println("当前方法权限码: " + Arrays.toString(perms));
            }
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除 ThreadLocal，防止内存泄漏
        PermissionContext.clear();
    }
}
