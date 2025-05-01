package com.ruoyi.common.core.commonEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author DavidLoman
 * @create 2025-03-29 12:11
 */
@Data
@Schema(description = "基础返回对象")
public class BaseVo {
    @Schema(description = "创建者")
    private String createBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新者")
    private String updateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    @Schema(description = "删除标志 (0代表存在 1代表删除)")
    private String isDeleted;
}
