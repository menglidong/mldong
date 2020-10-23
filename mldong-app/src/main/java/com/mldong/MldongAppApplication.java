package com.mldong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.mldong.modules.*.mapper","com.mldong.modules.*.dao","com.mldong.modules.*.repo"})
@EnableTransactionManagement
public class MldongAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MldongAppApplication.class, args);
    }
}
