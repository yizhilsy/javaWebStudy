package com.Itheima.service.impl;

import com.Itheima.mapper.DeptMapper;
import com.Itheima.pojo.Dept;
import com.Itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept query(Integer id) {
        Dept deptResult = deptMapper.query(id);
        return deptResult;
    }

    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }
}
