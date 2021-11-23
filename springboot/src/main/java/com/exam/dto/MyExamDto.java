package com.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyExamDto {
    private String grade;
    private String major;
    private String institute;
    private String clazz;
}
