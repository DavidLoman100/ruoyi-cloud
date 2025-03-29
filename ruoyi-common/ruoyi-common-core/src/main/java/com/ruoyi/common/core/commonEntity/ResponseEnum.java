package com.ruoyi.common.core.commonEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author DavidLoman
 * @create 2025-03-29 18:03
 */
@Getter
@RequiredArgsConstructor
public enum ResponseEnum implements BaseEnum {
    SUCCESS(200, "成功"),
    FAILED(0, "失败");;
    private final int code;
    private final String msg;

}
