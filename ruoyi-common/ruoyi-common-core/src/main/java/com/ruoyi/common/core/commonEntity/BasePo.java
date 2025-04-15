package com.ruoyi.common.core.commonEntity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * @author DavidLoman
 * @create 2025-03-29 12:11
 */
@Data
public class BasePo {
    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
    /**
     * 删除标志 (0代表存在 1代表删除)
     */
    @TableField(value = "is_deleted")
    @TableLogic(value = "0", delval = "1")
    private String isDeleted;
}
