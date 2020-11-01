package com.mldong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RefreshScope
public class IndexController {
    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping("/index")
    public boolean index() {
        return useLocalCache;
    }
}
