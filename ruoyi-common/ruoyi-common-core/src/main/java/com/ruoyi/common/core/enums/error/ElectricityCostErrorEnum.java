package com.ruoyi.common.core.enums.error;

import com.ruoyi.common.core.commonEntity.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author DavidLoman
 * @create 2025-06-28 21:45
 */
@Getter
@RequiredArgsConstructor
public enum ElectricityCostErrorEnum implements BaseEnum {
    DATE_EXIST(10501, "日期已经存在"),
    FILE_EMPTY_ERROR(10502, "上传文件数据为空"),
    FILE_DATE_REPEAT(10503, "上传文件日期重复"),

    ;

    private final int code;
    private final String msg;
}
