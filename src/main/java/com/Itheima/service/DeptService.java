package com.Itheima.service;

import com.Itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    //sql查询全部部门数据
    public List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    Dept query(Integer id);

    void update(Dept dept);
}
