package com.ruoyi.system.infrastructure.life.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.ruoyi.common.core.commonEntity.BasePo;
import lombok.Data;

/**
 * 生活日表
 * @TableName sys_life_day
 */
@TableName(value ="sys_life_day")
@Data
public class SysLifeDayPo extends BasePo {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * it面试
     */
    @TableField(value = "it_ms")
    private int itMs;

    /**
     * it技能
     */
    @TableField(value = "it_skill")
    private int itSkill;

    /**
     * it项目
     */
    @TableField(value = "it_project")
    private int itProject;

    /**
     * itSQl题
     */
    @TableField(value = "it_mysql")
    private int itMysql;

    /**
     * it算法
     */
    @TableField(value = "it_algorithm")
    private int itAlgorithm;

    /**
     * 运动
     */
    @TableField(value = "sport")
    private int sport;

    /**
     * 金融学习
     */
    @TableField(value = "finance_skill")
    private int financeSkill;

    /**
     * 解读股市
     */
    @TableField(value = "equity_mkt_interpret")
    private int equityMktInterpret;

    /**
     * 英语单词
     */
    @TableField(value = "eng_word")
    private int engWord;

    /**
     * 摄影技能
     */
    @TableField(value = "photography_skill")
    private int photographySkill;

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