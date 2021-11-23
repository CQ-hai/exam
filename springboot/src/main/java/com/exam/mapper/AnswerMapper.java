package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.vo.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface AnswerMapper {
    @Select("select question, subject, score, section,level,bank_name as bankName,\"选择题\" as type from multi_question m ,exam_bank e where m.bank_id=e.bank_id  " +
            "union select  question, subject, score, section,level,bank_name as bankName, \"判断题\" as type  from judge_question  j ,exam_bank e where j.bank_id=e.bank_id " +
            "union select  question, subject, score, section,level,bank_name as bankName, \"填空题\" as type from fill_question  f ,exam_bank e where f.bank_id=e.bank_id")
    IPage<AnswerVO> findAll(Page page);
}
