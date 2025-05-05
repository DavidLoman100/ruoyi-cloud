package com.ruoyi.common.core.enums.error;

import com.ruoyi.common.core.commonEntity.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author DavidLoman
 * @create 2025-04-19 9:36
 */
@Getter
@RequiredArgsConstructor
public enum UserErrorEnum implements BaseEnum {
    NO_OPT_ADMIN(10100, "不允许操作超级管理员！"),
    ACCOUNT_ERROR(10101, "账号异常！"),
    ;
    private final int code;
    private final String msg;

}
