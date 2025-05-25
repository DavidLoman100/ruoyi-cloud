package com.ruoyi.system.dto.role.req;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author DavidLoman
 * @create 2025-05-25 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleStatusDTO implements Serializable {
    @NotNull(message = "角色ID不能为空")
    @Schema(description = "角色ID")
    private Long roleId;

    @NotBlank(message = "角色状态不能为空")
    @Schema(description = "角色启用状态")
    private String status;
}
