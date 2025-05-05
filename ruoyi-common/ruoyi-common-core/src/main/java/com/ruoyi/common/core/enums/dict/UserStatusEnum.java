package com.ruoyi.common.core.enums.dict;

import lombok.Getter;

/**
 * 用户状态
 * 
 * @author ruoyi
 */
@Getter
public enum UserStatusEnum {
    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private String code;
    private String info;

    UserStatusEnum(String code, String info)
    {
        this.code = code;
        this.info = info;
    }
}
