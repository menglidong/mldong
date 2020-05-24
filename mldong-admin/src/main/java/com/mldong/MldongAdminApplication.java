package com.mldong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = {"com.mldong.modules.*.mapper","com.mldong.modules.*.dao","com.mldong.modules.*.repo"})
//@EnableTransactionManagement
public class MldongAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(MldongAdminApplication.class, args);
	}
}
