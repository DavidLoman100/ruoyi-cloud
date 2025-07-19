package com.ruoyi.common.core.enums.error;

import com.ruoyi.common.core.commonEntity.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author DavidLoman
 * @create 2025-07-19 23:24
 */
@Getter
@RequiredArgsConstructor
public enum DictErrorEnum implements BaseEnum {
    CODE_REPEAT(10601, "编码已经存在！"),
    ;
    private final int code;
    private final String msg;
}
