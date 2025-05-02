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
    PARAM_VALID_ERROR(10003, "参数校验异常"),
    INVALID_INFO(10004, "无效信息"),

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
