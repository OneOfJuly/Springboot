package com.gsq.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: springboot_project
 * @Date: 2019/4/6 18:05
 * @Author: Mr.GUO
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.gsq.springboot.mapper")
public class SpringbootApplication {
    public static void main(String[] args) {

        SpringApplication.run(SpringbootApplication.class,args);
    }
}
