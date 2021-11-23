package com.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class ExamManage {
    @TableField(value = "examCode")
    private Integer examCode;

    private String description;

    private String source;
    @TableField(value = "paperId")
    private Integer paperId;
    @TableField(value = "examDate")
    private String examDate;
    @TableField(value = "totalTime")
    private Integer totalTime;

    private String grade;

    private String term;

    private String major;

    private String institute;
    @TableField(value = "totalScore")
    private Integer totalScore;

    private String type;

    private String tips;
}