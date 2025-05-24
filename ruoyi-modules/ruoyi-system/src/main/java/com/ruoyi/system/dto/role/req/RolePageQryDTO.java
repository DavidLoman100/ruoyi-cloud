package com.ruoyi.system.dto.role.req;

import com.ruoyi.common.core.commonEntity.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author DavidLoman
 * @create 2025-05-11 15:23
 */
@Data
public class RolePageQryDTO extends PageRequest {

    @Schema(description = "每页数量", required = true)
    private Integer pageSize;
    @Schema(description = "页码", required = true)
    private Integer pageNum;
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "角色权限字符串")
    private String roleKey;
    @Schema(description = "角色状态（0正常 1停用）")
    private String status;
    @Schema(description = "筛选创建时间")
    private LocalDate startCreateTime;
    @Schema(description = "筛选结束时间")
    private LocalDate endCreateTime;

}
