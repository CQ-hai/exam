package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exam.dto.BankSelectDto;
import com.exam.entity.ExamBank;

import java.util.List;

public interface ExamBankService {
    public IPage<ExamBank> selectBank(BankSelectDto bankSelectDto);
    void addExamBank(ExamBank examBank);

    void updateBank(ExamBank examBank);
    void deleteBank(ExamBank examBank);

    List<ExamBank>getBank();
}
