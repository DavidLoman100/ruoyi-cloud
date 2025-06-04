package com.ruoyi.system.dto.role.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-06-04 20:42
 */
@Data
public class RoleAuthUserDTO implements Serializable {
    @NotNull(message = "角色ID不能为空")
    @Schema(description = "角色ID")
    private Long roleId;

    @NotEmpty(message = "用户id不能为空")
    @Schema(description = "用户ids")
    private List<Long> userIds;
}
