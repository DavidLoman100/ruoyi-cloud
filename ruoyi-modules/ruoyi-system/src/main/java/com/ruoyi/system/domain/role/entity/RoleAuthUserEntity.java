package com.ruoyi.system.domain.role.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-06-04 20:48
 */
@Data
public class RoleAuthUserEntity {
    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "用户ids")
    private List<Long> userIds;

}
