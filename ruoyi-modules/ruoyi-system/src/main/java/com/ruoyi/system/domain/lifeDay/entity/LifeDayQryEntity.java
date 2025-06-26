package com.ruoyi.system.domain.lifeDay.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author DavidLoman
 * @create 2025-06-14 21:25
 */
@Data
public class LifeDayQryEntity implements Serializable {

    @Schema(description = "每页数量")
    private Integer pageSize;
    @Schema(description = "页码")
    private Integer pageNum;
    @Schema(description = "筛选生活开始时间")
    private LocalDate startLifeDate;
    @Schema(description = "筛选生活结束时间")
    private LocalDate endLifeDate;

}
