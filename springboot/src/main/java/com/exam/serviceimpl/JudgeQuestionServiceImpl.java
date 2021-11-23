package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeQuestion;
import com.exam.mapper.JudgeQuestionMapper;
import com.exam.service.JudgeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeQuestionServiceImpl implements JudgeQuestionService {

    @Autowired
    private JudgeQuestionMapper judgeQuestionMapper;

    @Override
    public List<JudgeQuestion> findByIdAndType(Integer paperId) {
        return judgeQuestionMapper.findByIdAndType(paperId);
    }

    @Override
    public IPage<JudgeQuestion> findAll(Page<JudgeQuestion> page) {
        return judgeQuestionMapper.findAll(page);
    }

    @Override
    public JudgeQuestion findOnlyQuestionId() {
        return judgeQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public int add(JudgeQuestion judgeQuestion) {
        return judgeQuestionMapper.add(judgeQuestion);
    }

    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo) {
        return judgeQuestionMapper.findBySubject(subject,pageNo);
    }

    @Override
    public Integer selectCountByBank(Integer bankId) {
        QueryWrapper<JudgeQuestion> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("bank_id",bankId);
        return judgeQuestionMapper.selectCount(queryWrapper);
    }

    @Override
    public List<Integer> findByBank(Integer bankId, Integer pageNo) {
        return judgeQuestionMapper.findByBank(bankId,pageNo);
    }


    @Override
    public JudgeQuestion findQuestionById(Integer questionId) {

        return judgeQuestionMapper.findByquestionId(questionId);
    }
}
