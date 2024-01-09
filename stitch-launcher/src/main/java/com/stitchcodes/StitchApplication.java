package com.stitchcodes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: stitch
 * @Date: 2023/4/27 19:55
 * @Description: 启动类
 */
@SpringBootApplication
@MapperScan(value = {"com.stitchcodes.core.mapper.**"})
public class StitchApplication {

    public static void main(String[] args) {
        SpringApplication.run(StitchApplication.class, args);
        System.out.println("☺Stitch Application Start Success...");
    }
}
