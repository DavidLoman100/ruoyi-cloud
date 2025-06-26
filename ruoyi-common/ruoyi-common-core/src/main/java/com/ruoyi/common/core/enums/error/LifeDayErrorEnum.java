package com.ruoyi.common.core.enums.error;

import com.ruoyi.common.core.commonEntity.BaseEnum;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author DavidLoman
 * @create 2025-06-15 18:02
 */
@Getter
@RequiredArgsConstructor
public enum LifeDayErrorEnum implements BaseEnum {
    DATE_EXIST(10401, "日期已经存在"),
            ;
    private final int code;
    private final String msg;
}
