package com.mldong.controller;

import com.mldong.common.base.CommonResult;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.mapi.DUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @Value("${spring.application.name}")
    private String applicationName;
    @DubboReference(version = "1.0.0")
    private DUserService dUserService;
    @RequestMapping("/")
    public String index() {
        return applicationName;
    }
    @GetMapping("getUser")
    public CommonResult<SysUser> getUser(Long userId) {
        return CommonResult.success(dUserService.get(userId));
    }
}
