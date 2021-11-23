package com.exam.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionDto {
    private Integer bankId;
    private String bankName;
    private Integer multi;
    private Integer fill;
    private Integer judge;
}
