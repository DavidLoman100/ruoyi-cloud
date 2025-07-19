package com.ruoyi.system.vo.Dict;

import com.ruoyi.common.core.commonEntity.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author DavidLoman
 * @create 2025-07-19 11:38
 */
@Data
public class DictVo extends BaseVo {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "字典类型")
    private String dictCode;

    @Schema(description = "字典名称")
    private String dictName;

    @Schema(description = "备注")
    private String remark;
}
