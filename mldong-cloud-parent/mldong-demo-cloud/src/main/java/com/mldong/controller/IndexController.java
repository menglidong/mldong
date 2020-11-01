package com.mldong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("/")
    public String index() {
        return applicationName;
    }
}
