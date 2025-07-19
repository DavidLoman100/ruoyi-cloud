package com.ruoyi.system.dto.dict.req;

import com.ruoyi.common.core.commonEntity.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author DavidLoman
 * @create 2025-07-19 11:42
 */
@Data
public class UpdDictDTO extends PageRequest {

    @NotNull(message = "id不能为空")
    @Schema(description = "主键")
    private Long id;

    @NotBlank(message = "字典类型不能为空")
    @Schema(description = "字典类型")
    private String dictCode;

    @NotBlank(message = "字典名称不能为空")
    @Schema(description = "字典名称")
    private String dictName;


    @Schema(description = "备注")
    private String remark;
}
