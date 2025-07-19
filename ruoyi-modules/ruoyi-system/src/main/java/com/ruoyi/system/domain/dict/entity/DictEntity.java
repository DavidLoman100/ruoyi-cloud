package com.ruoyi.system.domain.dict.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author DavidLoman
 * @create 2025-07-19 11:47
 */
@Data
public class DictEntity implements Serializable {

    @Schema(description = "每页数量")
    private Integer pageSize;
    @Schema(description = "页码")
    private Integer pageNum;
    @Schema(description = "字典类型")
    private String dictCode;
    @Schema(description = "字典名称")
    private String dictName;
}
