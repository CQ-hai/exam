package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.dto.BankSelectDto;
import com.exam.entity.ExamBank;
import com.exam.mapper.ExamBankMapper;
import com.exam.service.ExamBankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamBankServiceImp implements ExamBankService {
    @Resource
    private ExamBankMapper examBankMapper;
    @Override
    public IPage<ExamBank> selectBank(BankSelectDto bankSelectDto) {
        Page<ExamBank>examBankPage=new Page<>(bankSelectDto.getPageIndex(),bankSelectDto.getPageSize());
        QueryWrapper<ExamBank>queryWrapper=new QueryWrapper<>();
        if (bankSelectDto.getBankName()!=null&&!bankSelectDto.getBankName().isEmpty()){
            queryWrapper.like("bank_name",bankSelectDto.getBankName()+"%");
        }
        return examBankMapper.selectPage(examBankPage,queryWrapper);
    }

    @Override
    public void addExamBank(ExamBank examBank) {
        examBankMapper.insert(examBank);
    }

    @Override
    public void updateBank(ExamBank examBank) {
examBankMapper.updateById(examBank);
    }

    @Override
    public void deleteBank(ExamBank examBank) {
examBankMapper.deleteById(examBank.getBankId());
    }

    @Override
    public List<ExamBank> getBank() {
        return examBankMapper.selectList(null);
    }
}
