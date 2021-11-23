package com.exam.vo;

import com.alibaba.druid.filter.AutoLoad;
import com.exam.entity.ExamBank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankNumVo {
    private List<ExamBank> examBanks;
    private Integer multiNum;
    private Integer fillNum;
    private Integer judgeNum;
}
