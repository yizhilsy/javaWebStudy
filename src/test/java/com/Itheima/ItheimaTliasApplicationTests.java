package com.Itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ItheimaTliasApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 生成jwt测试
     */
    @Test
    public void testGenJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itheima")   //签名算法
                .setClaims(claims)  //自定义内容，即载荷
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000 ) )    //设置有效期为1h
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcwOTY1Njk2MH0.GJfA1U4ta11gNgvCsR-5b-1euCQ-24EQ9veP1lDW0dg")
                .getBody();
        System.out.println(claims);
    }

}
