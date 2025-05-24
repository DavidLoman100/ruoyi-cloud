package com.ruoyi.common.core.enums.error;

import com.ruoyi.common.core.commonEntity.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 菜单枚举 10200-10299
 *
 * @author DavidLoman
 * @create 2025-05-05 10:02
 */
@Getter
@RequiredArgsConstructor
public enum MenuErrorEnum implements BaseEnum {

    NAME_EXIST(10201, "菜单名称已存在！"),
    NO_VALID_PATH(10202, "外部链接的地址必须以http(s)://开头！"),
    NO_VALID_PARENT(10203, "父菜单是无效菜单！"),
    HAS_CHILD(10204, "存在子菜单，不允许删除！"),
    HAS_ASSIGNED(10205, "菜单已分配，不允许删除！"),
    ;


    private final int code;
    private final String msg;

}
