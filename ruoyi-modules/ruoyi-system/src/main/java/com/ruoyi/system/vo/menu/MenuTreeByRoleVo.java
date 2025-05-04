package com.ruoyi.system.vo.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-05-04 15:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeByRoleVo {

    @Schema(description = "角色对应的菜单ID")
    private List<Long> menuIds;

    @Schema(description = "菜单树")
    private List<MenuTreeVo> menuTree;
}
