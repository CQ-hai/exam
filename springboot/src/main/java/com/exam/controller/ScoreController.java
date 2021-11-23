package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.*;
import com.exam.service.FillQuestionService;
import com.exam.service.JudgeQuestionService;
import com.exam.service.MultiQuestionService;
import com.exam.serviceimpl.ScoreServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.vo.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ScoreController {
    @Autowired
    private ScoreServiceImpl scoreService;

    @Autowired
    private FillQuestionService fillQuestionService;
    @Autowired
    private JudgeQuestionService judgeQuestionService;
    @Autowired
    private MultiQuestionService multiQuestionService;
    @Value("${scoreforeach}")
    private Integer scoreforeach;

    @GetMapping("/scores")
    public ApiResult findAll() {
        List<Score> res = scoreService.findAll();
        return ApiResultHandler.buildApiResult(200,"查询所有学生成绩",res);
    }
//    分页
    @GetMapping("/score/{page}/{size}/{studentId}")
    public ApiResult findById(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("studentId") Integer studentId) {
        Page<Score> scorePage = new Page<>(page, size);
        IPage<Score> res = scoreService.findById(scorePage, studentId);
        return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
    }

//    不分页
    @GetMapping("/score/{studentId}")
        public ApiResult findById(@PathVariable("studentId") Integer studentId) {
        List<Score> res = scoreService.findById(studentId);
        if (!res.isEmpty()) {
            return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
        } else {
            return ApiResultHandler.buildApiResult(400, "ID不存在", res);
        }
    }

    @PostMapping("/score")
    public ApiResult add(@RequestBody Score score) {
        int res = scoreService.add(score);
        if (res == 0) {
            return ApiResultHandler.buildApiResult(400,"成绩添加失败",res);
        }else {
            return ApiResultHandler.buildApiResult(200,"成绩添加成功",res);
        }
    }

    @GetMapping("/scores/{examCode}")
    public ApiResult findByExamCode(@PathVariable("examCode") Integer examCode) {
        List<Score> scores = scoreService.findByExamCode(examCode);
        return ApiResultHandler.buildApiResult(200,"查询成功",scores);
    }


    @PostMapping("/score/judege")
    public ApiResult judgeScore(@RequestBody Score score) {
        Integer totalScore = 0;
        if (score.getAnswerVOS() != null && score.getAnswerVOS().size() == 0) {
            for (AnswerVO answerVO : score.getAnswerVOS()) {
                if ("选择题".equals(answerVO.getType())) {
                    MultiQuestion multiQuestion = multiQuestionService.findQuestionById(answerVO.getQuestionId());
                    if (multiQuestion.getRightAnswer().equals(answerVO.getAnswer())) {
                        totalScore += scoreforeach;
                    }
                }
                if ("判断题".equals(answerVO.getType())) {
                    JudgeQuestion judgeQuestion = judgeQuestionService.findQuestionById(answerVO.getQuestionId());
                    if (judgeQuestion.getAnswer().equals(answerVO.getAnswer())) {
                        totalScore += scoreforeach;
                    }
                }
                if ("填空题".equals(answerVO.getType())) {
                    FillQuestion fillQuestion = fillQuestionService.findQuestionById(answerVO.getQuestionId());
                    if (fillQuestion.getAnswer().equals(answerVO.getAnswer())) {
                        totalScore += scoreforeach;
                    }
                }
            }
        }
        score.setScore(totalScore);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        score.setAnswerDate(sdf.format(LocalDate.now()));
        int res = scoreService.add(score);
        if (res == 0) {
            return ApiResultHandler.buildApiResult(400, "成绩添加失败", res);
        } else {
            return ApiResultHandler.buildApiResult(200, "成绩添加成功", res);
        }
    }
}
