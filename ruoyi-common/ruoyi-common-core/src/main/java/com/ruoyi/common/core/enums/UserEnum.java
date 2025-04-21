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
public enum UserEnum implements BaseEnum {
    NO_OPT_ADMIN(10100, "不允许操作超级管理员"),
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
