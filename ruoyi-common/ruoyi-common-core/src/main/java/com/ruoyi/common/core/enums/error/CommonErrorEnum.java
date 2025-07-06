package com.ruoyi.common.core.enums.error;

import com.ruoyi.common.core.commonEntity.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 10000-10099
 * @author DavidLoman
 * @create 2025-04-19 9:36
 */
@Getter
@RequiredArgsConstructor
public enum CommonErrorEnum implements BaseEnum {
    INTERNAL_ERROR(10000, "服务异常"),
    NO_PERMISSION(10001, "没有访问权限，请联系管理员授权"),
    REQ_METHOD_NOT_SPT(10002, "请求方法不支持"),
    PARAM_VALID_ERROR(10003, "参数校验异常"),
    INVALID_INFO(10004, "无效信息"),
    READ_FILE_ERROR(10005, "读取文件失败"),

    ;
    private final int code;
    private final String msg;
}
