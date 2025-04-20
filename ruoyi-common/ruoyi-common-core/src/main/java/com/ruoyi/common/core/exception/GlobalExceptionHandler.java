package com.ruoyi.common.core.exception;

import com.ruoyi.common.core.commonEntity.Response;
import com.ruoyi.common.core.enums.CommonEnum;
import com.ruoyi.common.core.exception.auth.NotPermissionException;
import com.ruoyi.common.core.exception.auth.NotRoleException;
import com.ruoyi.common.core.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * @author DavidLoman
 * @create 2025-04-19 9:22
 */
@Slf4j
@RestController
public class GlobalExceptionHandler {


    /**
     * 权限码异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public Response<String> handleNotPermissionException(NotPermissionException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限码校验失败'{}'", requestURI, e.getMessage());
        return Response.failed(CommonEnum.NO_PERMISSION);
    }

    /**
     * 角色权限异常
     */
    @ExceptionHandler(NotRoleException.class)
    public Response<String> handleNotRoleException(NotRoleException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',角色权限校验失败'{}'", requestURI, e.getMessage());
        return Response.failed(CommonEnum.NO_PERMISSION);
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Response<String> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return Response.failed(CommonEnum.REQ_METHOD_NOT_SPT);
    }


    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    public Response<String> handleBizException(BizException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',业务异常:'{}',{}", requestURI, e.getMessage(), e);
        return Response.failed(e.getCode(), e.getMessage());
    }


    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<String> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("请求地址'{}',参数校验异常'{}'", requestURI, msg);
        return Response.failed(CommonEnum.PARAM_VALID_ERROR.getCode(), msg);
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Response<String> handleValidationException(Exception ex, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',系统异常'{}'", requestURI, ex);
        return Response.failed(CommonEnum.INTERNAL_ERROR);
    }


}
