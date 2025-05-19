package com.ruoyi.system.vo.role;

import com.ruoyi.common.core.commonEntity.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author DavidLoman
 * @create 2025-05-11 15:21
 */
@Data
public class RoleVo extends BaseVo {

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色权限字符串")
    private String roleKey;

    @Schema(description = "显示顺序")
    private Integer roleSort;

    @Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    private String dataScope;

    @Schema(description = "菜单树选择项是否关联显示")
    private Integer menuCheckStrictly;

    @Schema(description = "部门树选择项是否关联显示")
    private Integer deptCheckStrictly;

    @Schema(description = "角色状态（0正常 1停用）")
    private String status;

    @Schema(description = "备注")
    private String remark;

}
