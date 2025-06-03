package com.ruoyi.system.domain.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author DavidLoman
 * @create 2025-06-01 22:59
 */
@Data
public class UserRoleQryEntity {

    @Schema(description = "每页数量", required = true)
    private Integer pageSize;

    @Schema(description = "页码", required = true)
    private Integer pageNum;

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "用户是否分配 false-未分配 true-已分配")
    private Boolean isAllocatedUser;

    @Schema(description = "用户账号")
    private String userName;

    @Schema(description = "手机号码")
    private String phonenumber;

    @Schema(description = "数据权限")
    private String dataScopeSql;
}
