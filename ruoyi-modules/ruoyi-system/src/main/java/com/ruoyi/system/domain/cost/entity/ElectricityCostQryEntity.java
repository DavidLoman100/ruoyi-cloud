package com.ruoyi.system.domain.cost.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author DavidLoman
 * @create 2025-06-28 16:04
 */
@Data
public class ElectricityCostQryEntity {

    @Schema(description = "每页数量")
    private Integer pageSize;
    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "筛选生活开始时间")
    private LocalDate startLifeDate;
    @Schema(description = "筛选生活结束时间")
    private LocalDate endLifeDate;
}
