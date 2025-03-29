package com.ruoyi.common.core.commonEntity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author DavidLoman
 * @create 2025-03-16 1:38
 */
@Data
@Schema(description = "分页参数")
public class PageRequest {
    @Schema(description = "每页数量", required = true)
    private Integer size;
    @Schema(description = "页码", required = true)
    private Integer page;
}