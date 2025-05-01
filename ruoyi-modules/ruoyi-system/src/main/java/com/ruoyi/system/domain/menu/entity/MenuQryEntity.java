package com.ruoyi.system.domain.menu.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-05-01 1:19
 */
@Data
public class MenuQryEntity {

    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "菜单状态（0正常 1停用）")
    private String status;

    @Schema(description = "菜单状态（0显示 1隐藏）")
    private String visible;

    @Schema(description = "角色ids")
    private List<Long> roles;
}
