package com.ruoyi.system.dto.user.req;

import com.ruoyi.common.core.commonEntity.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author DavidLoman
 * @create 2025-06-01 22:50
 */
@Data
public class UserRolePageQryDTO extends PageRequest {

    @NotNull(message = "角色ID不能为空")
    @Schema(description = "角色ID")
    private Long roleId;

    @NotNull(message = "用户是否分配不能为空")
    @Schema(description = "用户是否分配 false-未分配 true-已分配")
    private Boolean isAllocatedUser;

    @Schema(description = "用户账号")
    private String userName;

    @Schema(description = "手机号码")
    private String phonenumber;

}
