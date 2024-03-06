package com.Itheima.controller;

import com.Itheima.pojo.Emp;
import com.Itheima.pojo.Result;
import com.Itheima.service.EmpService;
import com.Itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){  //自动将json格式的数据转换为对象
        log.info("员工登录：{}",emp);
        Emp empResult = empService.login(emp);
        if(empResult != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",empResult.getId());
            claims.put("username",empResult.getUsername());
            claims.put("name",empResult.getName());
            //生成JWT令牌并下发
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return Result.error("用户名或密码错误");
    }
}
