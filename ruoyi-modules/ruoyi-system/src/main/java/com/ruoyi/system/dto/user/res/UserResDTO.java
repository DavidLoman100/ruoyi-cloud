package com.ruoyi.system.dto.user.res;

import com.ruoyi.common.core.commonEntity.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author DavidLoman
 * @create 2025-03-18 23:27
 */
@Data
@Schema(description = "用户信息")
public class UserResDTO extends BaseDto {
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
    @Schema(description = "头像地址")
    private String avatar;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "帐号状态（0正常 1停用）")
    private String status;
    @Schema(description = "最后登录IP")
    private String loginIp;
    @Schema(description = "最后登录时间")
    private Date loginDate;
    @Schema(description = "备注")
    private String remark;
}
