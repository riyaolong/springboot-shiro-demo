package com.xlzhou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //添加启动类注解
@MapperScan("com.xlzhou.mapper")  //配置mapper扫描地址
public class SpringbootShiroDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroDemoApplication.class, args);
    }

}
