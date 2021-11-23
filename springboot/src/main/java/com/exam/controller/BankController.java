package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exam.dto.BankSelectDto;
import com.exam.entity.ExamBank;
import com.exam.entity.MultiQuestion;
import com.exam.service.ExamBankService;
import com.exam.service.FillQuestionService;
import com.exam.service.JudgeQuestionService;
import com.exam.service.MultiQuestionService;
import com.exam.vo.BankNumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bank")
public class BankController {

    @Autowired
    private ExamBankService examBankService;

    @Autowired
    private FillQuestionService fillQuestionService;
    @Autowired
    private MultiQuestionService multiQuestionService;
    @Autowired
    private JudgeQuestionService judgeQuestionService;
    @PostMapping(value = "/getExamBank")
    public IPage<ExamBank> getExamBank(@RequestBody BankSelectDto bankSelectDto) {
        return examBankService.selectBank(bankSelectDto);
    }

    @GetMapping(value = "/selectExamBank")
    public List<ExamBank> selectExamBank() {
        return examBankService.getBank();
    }

    @PostMapping(value = "/getBankNum")
    public BankNumVo getBankNum(@RequestBody ExamBank examBank) {
        BankNumVo bankNumVo=new BankNumVo();
        bankNumVo.setFillNum(fillQuestionService.selectCountByBank(examBank.getBankId()));
        bankNumVo.setJudgeNum(judgeQuestionService.selectCountByBank(examBank.getBankId()));
        bankNumVo.setMultiNum(multiQuestionService.selectCountByBank(examBank.getBankId()));
        return bankNumVo;
    }
    @PostMapping(value = "/addExamBank")
    public void addExamBank(@RequestBody ExamBank examBank) {
         examBankService.addExamBank(examBank);
    }
    @PostMapping(value = "/updateExamBank")
    public void updateExamBank(@RequestBody ExamBank examBank) {
        examBankService.updateBank(examBank);
    }
    @PostMapping(value = "/deleteExamBank")
    public void deleteExamBank(@RequestBody ExamBank examBank) {
        examBankService.deleteBank(examBank);
    }
}
