package com.exam.dto;

import com.exam.entity.ExamManage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExamDto {
    private List<QuestionDto> question;

    private ExamManage exam;
}
