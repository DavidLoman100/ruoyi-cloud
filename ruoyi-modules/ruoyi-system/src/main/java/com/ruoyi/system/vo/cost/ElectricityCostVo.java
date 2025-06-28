package com.ruoyi.system.vo.cost;

import com.ruoyi.common.core.commonEntity.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author DavidLoman
 * @create 2025-06-28 15:53
 */
@Data
public class ElectricityCostVo extends BaseVo {

    @Schema(description = "主键id")
    private Long id;
    @Schema(description = "每千瓦时费用")
    private BigDecimal pricePerKwh;
    @Schema(description = "总千瓦时 单位1000")
    private BigDecimal totalKwh;
    @Schema(description = "费用合计")
    private BigDecimal cost;
    @Schema(description = "生活时间")
    private LocalDate lifeDate;
    @Schema(description = "备注")
    private String remark;

}
