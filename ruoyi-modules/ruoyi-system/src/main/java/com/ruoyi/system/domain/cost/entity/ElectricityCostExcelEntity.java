package com.ruoyi.system.domain.cost.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author DavidLoman
 * @create 2025-06-29 16:50
 */
@Data
public class ElectricityCostExcelEntity implements Serializable {
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty("生活时间")
    private LocalDate lifeDate;

    @ExcelProperty("每千瓦时费用")
    private BigDecimal pricePerKwh;

    @ExcelProperty("总千瓦时")
    private BigDecimal totalKwh;
}
