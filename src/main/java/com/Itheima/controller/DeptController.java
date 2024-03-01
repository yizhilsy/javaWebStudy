package com.Itheima.controller;

import com.Itheima.pojo.Dept;
import com.Itheima.pojo.Result;
import com.Itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
    //private static Logger log = (Logger) LoggerFactory.getLogger(DeptController.class);
    //可以直接使用@Slf4j注解来生成日志对象
    @GetMapping
    //@GetMapping("/depts")
    public Result list(){
        //使用日志记录框架来记录框架
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }
    //删除部门
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);
        //利用service删除部门
        deptService.delete(id);
        return Result.success();
    }

    //新增部门，获取json格式的数据
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门: {}",dept);
        //调用service新增部门
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result query(@PathVariable Integer id){
        log.info("查询部门号: {}的部门",id);
        Dept deptResult = deptService.query(id);
        if(deptResult==null){
            return Result.error("none such result");
        }else{
            return Result.success(deptResult);
        }
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门号: {}的部门名称为: {}",dept.getId(),dept.getName());
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptService.update(dept);
        return Result.success();
    }
}
