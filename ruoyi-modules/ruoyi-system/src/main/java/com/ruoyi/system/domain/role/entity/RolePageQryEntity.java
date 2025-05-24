package com.ruoyi.system.domain.role.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author DavidLoman
 * @create 2025-05-11 15:54
 */
@Data
public class RolePageQryEntity {

    @Schema(description = "每页数量", required = true)
    private Integer pageSize;
    @Schema(description = "页码", required = true)
    private Integer pageNum;
    @Schema(description = "角色ID")
    private Long roleId;
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "角色权限字符串")
    private String roleKey;
    @Schema(description = "角色状态（0正常 1停用）")
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "筛选创建时间")
    private LocalDate startCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "筛选结束时间")
    private LocalDate endCreateTime;

    @Schema(description = "数据权限")
    private String dataScopeSql;
}
