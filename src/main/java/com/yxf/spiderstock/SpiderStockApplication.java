package com.yxf.spiderstock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpiderStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiderStockApplication.class, args);
    }

}
