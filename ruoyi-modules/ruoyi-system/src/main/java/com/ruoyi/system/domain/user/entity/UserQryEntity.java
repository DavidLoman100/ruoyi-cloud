package com.ruoyi.system.domain.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息表
 * @TableName sys_user
 */
@Data
public class UserQryEntity {

    @Schema(description = "每页数量", required = true)
    private Integer pageSize;

    @Schema(description = "页码", required = true)
    private Integer pageNum;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "部门ID")
    private Long deptId;

    @Schema(description = "用户账号")
    private String userName;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "用户类型（00系统用户）")
    private String userType;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "手机号码")
    private String phonenumber;

    @Schema(description = "用户性别（0男 1女 2未知）")
    private String sex;

    @Schema(description = "帐号状态（0正常 1停用）")
    private String status;

}