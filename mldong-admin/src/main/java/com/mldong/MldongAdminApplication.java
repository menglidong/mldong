package com.mldong;

import cn.xuyanwu.spring.file.storage.EnableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mldong
 * @date 2023/9/20
 */
@SpringBootApplication
@EnableFileStorage
public class MldongAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MldongAdminApplication.class, args);
    }
}
