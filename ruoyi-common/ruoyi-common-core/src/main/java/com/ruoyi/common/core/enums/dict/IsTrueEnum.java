package com.ruoyi.common.core.enums.dict;

import com.ruoyi.common.core.commonEntity.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author DavidLoman
 * @create 2025-05-05 22:01
 */
@Getter
public enum IsTrueEnum {
    FALSE("0", "否"),

    TRUE("1", "是"),
    ;
    private String code;
    private String info;

    IsTrueEnum(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

}
