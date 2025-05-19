package com.ruoyi.common.core.enums.dict;

import lombok.Getter;

/**
 * @author DavidLoman
 * @create 2025-05-18 15:22
 */
@Getter
public enum RolePermEnum {

    DATA_SCOPE_ALL("1","全部数据权限"),

     DATA_SCOPE_CUSTOM("2","自定数据权限"),

     DATA_SCOPE_DEPT ("3","部门数据权限"),

     DATA_SCOPE_DEPT_AND_CHILD ("4","部门及以下数据权限"),

     DATA_SCOPE_SELF ("5","仅本人数据权限"),;

    private String code;
    private String info;

    RolePermEnum(String code, String info)
    {
        this.code = code;
        this.info = info;
    }
}
