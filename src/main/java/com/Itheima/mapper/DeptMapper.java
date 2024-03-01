package com.Itheima.mapper;

import com.Itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    //查询全部部门
    @Select("select * from dept")
    public List<Dept> list();

    @Delete("delete from dept where id = #{id}")
    public void deleteById(Integer id);

    @Insert("insert into dept(name,create_time,update_time)"+
            "values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept query(Integer id);

    @Update("update dept set name = #{name} where id = #{id}")
    void update(Dept dept);

}
