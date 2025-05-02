package com.ruoyi.system.vo.menu;

import com.ruoyi.common.core.commonEntity.BaseVo;
import com.ruoyi.system.infrastructure.menu.repository.po.SysMenuPo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-05-02 19:01
 */
@Data
public class MenuTreeVo extends BaseVo {

    @Schema(description = "菜单ID")
    private Long menuId;
    @Schema(description = "菜单名称")
    private String menuName;
    @Schema(description = "父菜单ID")
    private Long parentId;
    @Schema(description = "显示顺序")
    private Integer orderNum;
    @Schema(description = "路由地址")
    private String path;
    @Schema(description = "组件路径")
    private String component;
    @Schema(description = "路由参数")
    private String query;
    @Schema(description = "路由名称")
    private String routeName;
    @Schema(description = "是否为外链（0是 1否）")
    private Integer isFrame;
    @Schema(description = "是否缓存（0缓存 1不缓存）")
    private Integer isCache;
    @Schema(description = "菜单类型（M目录 C菜单 F按钮）")
    private String menuType;
    @Schema(description = "菜单状态（0显示 1隐藏）")
    private String visible;
    @Schema(description = "菜单状态（0正常 1停用）")
    private String status;
    @Schema(description = "权限标识")
    private String perms;
    @Schema(description = "菜单图标")
    private String icon;
    @Schema(description = "备注")
    private String remark;
    @Schema(description = "子菜单")
    private List<MenuTreeVo> children = new ArrayList<MenuTreeVo>();


}
