package com.ruoyi.system.infrastructure.cost.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ruoyi.common.core.commonEntity.BasePo;
import lombok.Data;

/**
 * 用电支出
 * @TableName sys_electricity_cost
 */
@TableName(value ="sys_electricity_cost")
@Data
public class SysElectricityCostPo extends BasePo {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 每千瓦时费用
     */
    @TableField(value = "price_per_kwh")
    private BigDecimal pricePerKwh;

    /**
     * 总千瓦时 单位1000
     */
    @TableField(value = "total_kwh")
    private BigDecimal totalKwh;

    /**
     * 费用合计
     */
    @TableField(value = "cost")
    private BigDecimal cost;

    /**
     * 生活时间
     */
    @TableField(value = "life_date")
    private LocalDate lifeDate;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}