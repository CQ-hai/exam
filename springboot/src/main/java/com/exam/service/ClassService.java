package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exam.dto.ClassDto;
import com.exam.entity.Class;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClassService {
    void add(Class c);

    void update(Class c);
    void delete(Class c);
    IPage<Class> selectBy(ClassDto classDto);
    List<Class> select();
}
