package com.Itheima.service.impl;

import com.Itheima.mapper.EmpMapper;
import com.Itheima.pojo.Emp;
import com.Itheima.pojo.PageBean;
import com.Itheima.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,
                         LocalDate begin, LocalDate end) {
        //原始方法
//        //1. 获取总记录数
//        Long count = empMapper.count();
//        //2. 获取当前页的结果
//        List<Emp> empList = empMapper.page((page-1)*pageSize,pageSize);
//        //3. 封装PageBean对象
//        PageBean pageBean = new PageBean(count,empList);
//        return pageBean;

        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);
        //2. 执行查询，此时已经是分页查询结果的封装了
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) empList;
        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    //批量删除
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }
}
