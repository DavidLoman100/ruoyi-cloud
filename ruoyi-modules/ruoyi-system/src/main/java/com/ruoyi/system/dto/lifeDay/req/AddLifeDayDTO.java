package com.ruoyi.system.dto.lifeDay.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author DavidLoman
 * @create 2025-06-15 11:25
 */
@Data
public class AddLifeDayDTO implements Serializable {

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "生活时间")
    private LocalDate lifeDate;
    @Schema(description = "备注")
    private String remark;
}
