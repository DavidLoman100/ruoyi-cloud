package com.ruoyi.system.vo.lifeDay;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.commonEntity.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author DavidLoman
 * @create 2025-06-14 17:13
 */
@Data
public class LifeDayVo extends BaseVo {

    @Schema(description = "主键id")
    private Long id;
    @Schema(description = "it面试")
    private int itMs;
    @Schema(description = "it技能")
    private int itSkill;
    @Schema(description = "it项目")
    private int itProject;
    @Schema(description = "itSQl题")
    private int itMysql;
    @Schema(description = "it算法")
    private int itAlgorithm;
    @Schema(description = "运动")
    private int sport;
    @Schema(description = "金融学习")
    private int financeSkill;
    @Schema(description = "解读股市")
    private int equityMktInterpret;
    @Schema(description = "英语单词")
    private int engWord;
    @Schema(description = "摄影技能")
    private int photographySkill;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "生活时间")
    private Date lifeDate;
    @Schema(description = "备注")
    private String remark;
}
