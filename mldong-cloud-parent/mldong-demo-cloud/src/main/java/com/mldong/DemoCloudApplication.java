package com.mldong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients//表示启用feign
public class DemoCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoCloudApplication.class, args);
    }
}
