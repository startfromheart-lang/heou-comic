package com.heou.comic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.heou.comic.mapper")
@EnableScheduling
public class HeouComicApplication {
    public static void main(String[] args) {
        SpringApplication.run(HeouComicApplication.class, args);
    }
}
