package com.exam.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankSelectDto {
    private Integer pageSize;
    private Integer pageIndex;
    private String bankName;
}
