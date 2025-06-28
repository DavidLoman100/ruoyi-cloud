package com.ruoyi.system.dto.cost.req;

import com.ruoyi.common.core.commonEntity.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author DavidLoman
 * @create 2025-06-28 15:57
 */
@Data
public class ElectricityCostPageQryDTO extends PageRequest {
    @Schema(description = "筛选生活开始时间")
    private LocalDate startLifeDate;
    @Schema(description = "筛选生活结束时间")
    private LocalDate endLifeDate;
}
