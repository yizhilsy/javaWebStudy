package com.Itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ItheimaTliasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItheimaTliasApplication.class, args);
    }

}
