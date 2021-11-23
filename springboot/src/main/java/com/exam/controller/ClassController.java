package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exam.dto.ClassDto;
import com.exam.entity.Class;
import com.exam.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @PostMapping(value = "/insert")
    public void insert(@RequestBody Class c){
        classService.add(c);
    }
    @PostMapping(value = "/update")
    public void update(@RequestBody Class c){
        classService.update(c);
    }
    @PostMapping(value = "/delete")
    public void delete(@RequestBody Class c){
        classService.delete(c);
    }

    @PostMapping(value = "getClass")
    public IPage<Class> select(@RequestBody ClassDto classDto){
        return classService.selectBy(classDto);
    }

    @GetMapping(value = "slectClass")
    public List<Class> select(){
        return classService.select();
    }
}
