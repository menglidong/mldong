package com.mldong.modules.wf.engine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mldong"})
@MapperScan({"com.mldong.flow.engine.mapper"})
public class FlowApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowApplication.class,args);
    }
}