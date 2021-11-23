package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.dto.ExamDto;
import com.exam.dto.MyExamDto;
import com.exam.dto.QuestionDto;
import com.exam.entity.ApiResult;
import com.exam.entity.ExamManage;
import com.exam.entity.PaperManage;
import com.exam.service.PaperService;
import com.exam.serviceimpl.ExamManageServiceImpl;
import com.exam.serviceimpl.FillQuestionServiceImpl;
import com.exam.serviceimpl.JudgeQuestionServiceImpl;
import com.exam.serviceimpl.MultiQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExamManageController {

    @Autowired
    private ExamManageServiceImpl examManageService;
    @Autowired
    MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    FillQuestionServiceImpl fillQuestionService;

    @Autowired
    JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    PaperService paperService;
    @GetMapping("/exams")
    public ApiResult findAll(){
        System.out.println("不分页查询所有试卷");
        ApiResult apiResult;
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", examManageService.findAll());
        return apiResult;
    }

    @GetMapping("/exams/{page}/{size}")
    public ApiResult findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        System.out.println("分页查询所有试卷");
        ApiResult apiResult;
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findAll(examManage);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }
    @PostMapping("/exams/my/{page}/{size}")
    public ApiResult findByMy(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody MyExamDto myExamDto){
        System.out.println("查找个人信息试卷");
        ApiResult apiResult;
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findByMy(examManage,myExamDto);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }
    @GetMapping("/exam/{examCode}")
    public ApiResult findById(@PathVariable("examCode") Integer examCode){
        System.out.println("根据ID查找");
        ExamManage res = examManageService.findById(examCode);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"考试编号不存在",null);
        }
        return ApiResultHandler.buildApiResult(200,"请求成功！",res);
    }

    @DeleteMapping("/exam/{examCode}")
    public ApiResult deleteById(@PathVariable("examCode") Integer examCode){
        int res = examManageService.delete(examCode);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }

    @PutMapping("/exam")
    public ApiResult update(@RequestBody ExamManage exammanage){
        int res = examManageService.update(exammanage);
//        if (res == 0) {
//            return ApiResultHandler.buildApiResult(20000,"请求参数错误");
//        }
        System.out.print("更新操作执行---");
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }

    @PostMapping("/exam")
    public ApiResult add(@RequestBody ExamDto examDto){
        int res = examManageService.add(examDto.getExam());
    for (QuestionDto questionDto: examDto.getQuestion()){
        // 选择题
        Integer changeNumber = questionDto.getMulti();
        // 填空题
        Integer fillNumber = questionDto.getFill();
        // 判断题
        Integer judgeNumber = questionDto.getJudge();
        //出卷id
        Integer paperId = examDto.getExam().getPaperId();

        // 选择题数据库获取
        List<Integer> changeNumbers = multiQuestionService.findByBank(questionDto.getBankId(), changeNumber);
        if(changeNumbers==null){
            return ApiResultHandler.buildApiResult(400,"选择题数据库获取失败",null);
        }
        for (Integer number : changeNumbers) {
            PaperManage paperManage = new PaperManage(paperId,1,number);
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"选择题组卷保存失败",null);
        }

        // 填空题
        List<Integer> fills = fillQuestionService.findByBank(questionDto.getBankId(), fillNumber);
        if(fills==null)
            return ApiResultHandler.buildApiResult(400,"填空题数据库获取失败",null);
        for (Integer fillNum : fills) {
            PaperManage paperManage = new PaperManage(paperId,2,fillNum);
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"填空题题组卷保存失败",null);
        }
        // 判断题
        List<Integer> judges = judgeQuestionService.findByBank(questionDto.getBankId(), judgeNumber);
        if(fills==null)
            return ApiResultHandler.buildApiResult(400,"判断题数据库获取失败",null);
        for (Integer judge : judges) {
            PaperManage paperManage = new PaperManage(paperId,3,judge);
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"判断题题组卷保存失败",null);
        }
    }
        return ApiResultHandler.buildApiResult(200,"试卷组卷成功",null);
    }

    @GetMapping("/examManagePaperId")
    public ApiResult findOnlyPaperId() {
        ExamManage res = examManageService.findOnlyPaperId();
        if (res != null) {
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"请求失败",res);
    }
}
