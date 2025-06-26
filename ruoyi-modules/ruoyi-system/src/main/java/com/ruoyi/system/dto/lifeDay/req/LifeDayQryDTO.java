package com.ruoyi.system.dto.lifeDay.req;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.common.core.commonEntity.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author DavidLoman
 * @create 2025-06-14 21:07
 */
@Data
public class LifeDayQryDTO extends PageRequest {
    @Schema(description = "筛选生活开始时间")
    private LocalDate startLifeDate;
    @Schema(description = "筛选生活结束时间")
    private LocalDate endLifeDate;

}
