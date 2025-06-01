package com.ruoyi.common.core.enums.error;

import com.ruoyi.common.core.commonEntity.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 10300-10399
 * @author DavidLoman
 * @create 2025-05-24 18:16
 */
@Getter
@RequiredArgsConstructor
public enum RoleErrorEnum implements BaseEnum {

    ROLE_NAME_EXIST(10300, "角色名称已存在！"),
    ROLE_KEY_EXIST(10301, "角色权限字符已存在！"),

    USER_EXIST_ROLE(10302, "角色已被用户绑定"),
    ;

    private final int code;
    private final String msg;
}
