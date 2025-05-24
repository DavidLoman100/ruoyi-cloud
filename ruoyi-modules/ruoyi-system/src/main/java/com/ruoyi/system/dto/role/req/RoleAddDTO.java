package com.ruoyi.system.dto.role.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-05-24 10:33
 */
@Data
public class RoleAddDTO implements Serializable {

    @NotBlank(message = "角色名称不能为空")
    @Schema(description = "角色名称")
    private String roleName;
    @NotBlank(message = "角色权限不能为空")
    @Schema(description = "角色权限字符串")
    private String roleKey;
    @NotNull(message = "角色状态不能为空")
    @Schema(description = "角色状态（0正常 1停用）")
    private String status;
    @NotNull(message = "角色排序不能为空")
    @Schema(description = "角色排序")
    private Integer roleSort;
    @NotNull(message = "菜单树选择项是否关联显示不能为空")
    @Schema(description = "菜单树选择项是否关联显示（ false：父子不互相关联显示 true：父子互相关联显示）")
    private boolean menuCheckStrictly;
    @Schema(description = "备注")
    private String remark;
    @Schema(description = "菜单ids")
    private List<Long> menuIds;
}
