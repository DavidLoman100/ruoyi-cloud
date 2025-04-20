package com.ruoyi.common.core.enums;

import com.ruoyi.common.core.commonEntity.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author DavidLoman
 * @create 2025-04-19 9:36
 */
@Getter
@RequiredArgsConstructor
public enum CommonEnum implements BaseEnum {
    INTERNAL_ERROR(10000, "服务异常"),
    NO_PERMISSION(10001, "没有访问权限，请联系管理员授权"),
    REQ_METHOD_NOT_SPT(10002, "请求方法不支持"),
    PARAM_VALID_ERROR(10001, "参数校验异常"),
    USER_NOT_EXIST(10002, "用户不存在"),
    USER_PASSWORD_ERROR(10003, "用户密码错误"),
    USER_NOT_LOGIN(10004, "用户未登录"),
    USER_NOT_AUTHORIZED(10005, "用户未授权"),
    USER_NOT_PERMISSION(10006, "用户无权限"),
    USER_NOT_ROLE(10007, "用户未角色"),
    ;
    private final int code;
    private final String msg;

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
