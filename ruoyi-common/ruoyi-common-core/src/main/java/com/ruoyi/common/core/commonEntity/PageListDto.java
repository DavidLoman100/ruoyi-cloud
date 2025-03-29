package com.ruoyi.common.core.commonEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-03-29 12:46
 */
@Data
public class PageListDto<T> {
    @Schema(description ="总条数")
    int total;
    @Schema(description = "每页数量")
    private String size;
    @Schema(description = "页码")
    private String page;
    @Schema(description = "列表信息")
    List<T> list;
}