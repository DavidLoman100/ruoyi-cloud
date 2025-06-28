package com.ruoyi.system.dto.cost.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author DavidLoman
 * @create 2025-06-28 17:19
 */
@Data
public class ElectricityCostAddDTO implements Serializable {

    @Schema(description = "每千瓦时费用")
    @NotNull(message = "每千瓦时费用不能为空")
    private BigDecimal pricePerKwh;
    @Schema(description = "总千瓦时 单位1000")
    @NotNull(message = "总千瓦时不能为空")
    private BigDecimal totalKwh;
    @Schema(description = "生活时间")
    @NotNull(message = "生活时间不能为空")
    private LocalDate lifeDate;
    @Schema(description = "备注")
    private String remark;
}
