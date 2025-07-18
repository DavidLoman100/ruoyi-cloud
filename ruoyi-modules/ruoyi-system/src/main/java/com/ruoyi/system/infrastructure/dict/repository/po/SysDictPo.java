package com.ruoyi.system.infrastructure.dict.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.ruoyi.common.core.commonEntity.BasePo;
import lombok.Data;

/**
 * 字典表
 * @TableName sys_dict
 */
@TableName(value ="sys_dict")
@Data
public class SysDictPo extends BasePo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典类型（唯一标识）
     */
    @TableField(value = "dict_code")
    private String dictCode;

    /**
     * 字典名称
     */
    @TableField(value = "dict_name")
    private String dictName;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;


}
