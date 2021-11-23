package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.dto.ClassDto;
import com.exam.entity.Class;
import com.exam.mapper.ClassMapper;
import com.exam.service.ClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ClassServiceImpl implements ClassService {

    @Resource
    private ClassMapper classMapper;
    @Override
    public void add(Class c) {
        classMapper.insert(c);
    }

    @Override
    public void update(Class c) {
        classMapper.updateById(c);
    }

    @Override
    public void delete(Class c) {
        classMapper.deleteById(c.getId());
    }

    @Override
    public IPage<Class> selectBy(ClassDto classDto) {
        Page<Class>page=new Page<>(classDto.getPageIndex(),classDto.getPageSize());
        QueryWrapper queryWrapper=new QueryWrapper();
        if (!classDto.getName().isEmpty()){
            queryWrapper.like("name",classDto.getName());
        }
        return classMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Class> select() {
        return classMapper.selectList(null);
    }


}
