package com.ruoyi.common.core.commonEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author DavidLoman
 * @create 2025-03-29 12:11
 */
@Data
@Schema(description = "基础返回对象")
public class BaseDto {
    @Schema(description = "创建者")
    private String createBy;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "更新者")
    private String updateBy;
    @Schema(description = "更新时间")
    private Date updateTime;
    @Schema(description = "删除标志 (0代表存在 1代表删除)")
    private String isDeleted;
}
