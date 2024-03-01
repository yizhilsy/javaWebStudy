package com.Itheima.mapper;

import com.Itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    //查询总记录数
//    @Select("select count(*) from emp")
//    public Long count();
//
//    //分页查询获取列表数据
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start, Integer pageSize);

    //使用pagehelper实现员工信息查询
    //使用xml映射文件来写sql语句
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void insert(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);
}
