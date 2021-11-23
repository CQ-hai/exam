package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ExamBank {
    @TableId(type = IdType.AUTO)
    private int bankId;
    private String bankName;
    private String desciption;
}
