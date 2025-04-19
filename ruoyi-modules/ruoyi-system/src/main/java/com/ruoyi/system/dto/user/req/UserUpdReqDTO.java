package com.ruoyi.system.dto.user.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author DavidLoman
 * @create 2025-04-15 22:26
 */
@Data
@Schema(description = "修改用户")
public class UserUpdReqDTO implements Serializable {
    @NotNull(message = "用户id不能为空")
    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "部门编号")
    private Long deptId;

    @Schema(description = "登录名称")
    private String userName;

    @Schema(description = "用户名称")
    private String nickName;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "手机号码")
    private String phonenumber;

    /** 用户性别 */
    @Schema(description = "用户性别")
    private String sex;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "帐号状态（0正常 1停用）")
    private String status;


    @Schema(description = "角色Id集")
    private Long[] roleIds;

    @Schema(description = "岗位Id集")
    private Long[] postIds;

}
