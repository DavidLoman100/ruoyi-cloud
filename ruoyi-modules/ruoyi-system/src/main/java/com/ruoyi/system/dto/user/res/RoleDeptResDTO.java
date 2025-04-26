package com.ruoyi.system.dto.user.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author DavidLoman
 * @create 2025-04-27 0:01
 */
@Data
public class RoleDeptResDTO {

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "部门ID")
    private Long deptId;
}
