package com.ruoyi.system.dto.role.req;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-05-25 14:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDataScopeDTO implements Serializable {
    @NotNull(message = "角色ID不能为空")
    @Schema(description = "角色ID")
    private Long roleId;
    @NotBlank(message = "数据范围不能为空")
    @Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    private String dataScope;
    @NotNull(message = "部门树选择项是否关联显示不能为空")
    @Schema(description = "部门树选择项是否关联显示 true-父子联动 false-不联动")
    private Boolean deptCheckStrictly;
    @Schema(description = "部门ids")
    private List<Long> deptIds;
}
