package com.ruoyi.system.dto.dict.req;

import com.ruoyi.common.core.commonEntity.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author DavidLoman
 * @create 2025-07-19 11:42
 */
@Data
public class DictQryDTO extends PageRequest {

    @Schema(description = "字典类型")
    private String dictCode;

    @Schema(description = "字典名称")
    private String dictName;
}
