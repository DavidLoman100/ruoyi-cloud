package com.ruoyi.system.dto.menu.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author DavidLoman
 * @create 2025-05-05 2:05
 */
@Data
public class MenuAddDTO   {
    @NotBlank(message = "菜单名称不能为空")
    @Schema(description = "菜单名称")
    private String menuName;
    @NotNull(message = "父菜单ID不能为空")
    @Schema(description = "父菜单ID")
    private Long parentId;
    @NotNull(message = "显示顺序不能为空")
    @Schema(description = "显示顺序")
    private Integer orderNum;
    @NotBlank(message = "路由地址不能为空")
    @Schema(description = "路由地址")
    private String path;
    @Schema(description = "组件路径")
    private String component;
    @Schema(description = "路由参数")
    private String query;
    @Schema(description = "路由名称")
    private String routeName;
    @NotNull(message = "是否为外链不能为空")
    @Schema(description = "是否为外链（0是 1否）")
    private Integer isFrame;
    @NotNull(message = "是否缓存不能为空")
    @Schema(description = "是否缓存（0缓存 1不缓存）")
    private Integer isCache;
    @NotBlank(message = "菜单类型不能为空")
    @Schema(description = "菜单类型（M目录 C菜单 F按钮）")
    private String menuType;
    @NotBlank(message = "菜单状态不能为空")
    @Schema(description = "菜单显示状态（0显示 1隐藏）")
    private String visible;
    @NotBlank(message = "菜单启动状态不能为空")
    @Schema(description = "菜单状态（0正常 1停用）")
    private String status;
    @Schema(description = "权限标识")
    private String perms;
    @NotBlank(message = "菜单启动状态不能为空")
    @Schema(description = "菜单图标")
    private String icon;
    @Schema(description = "备注")
    private String remark;
}
