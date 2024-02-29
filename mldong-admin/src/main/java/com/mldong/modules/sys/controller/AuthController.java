package com.mldong.modules.sys.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.Dict;
import com.mldong.base.CommonResult;
import com.mldong.captcha.CaptchaManager;
import com.mldong.captcha.CaptchaParam;
import com.mldong.context.constant.ConstantContextHolder;
import com.mldong.modules.sys.dto.LoginParam;
import com.mldong.modules.sys.enums.err.SysErrEnum;
import com.mldong.modules.sys.service.AuthService;
import com.mldong.util.HttpServletUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@RestController
@Api(tags = "授权管理")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CaptchaManager captchaManager;
    @PostMapping("/sys/login")
    @ApiOperation(value="登录")
    public CommonResult<?> login(@RequestBody @Validated LoginParam param) {
        if(ConstantContextHolder.getCaptchaOpenFlag()){
            if(!captchaManager.validate(HttpServletUtil.getRequest())) {
                return CommonResult.fail(SysErrEnum.CAPTCHA_ERROR);
            }
        }
        return CommonResult.data(authService.login(param));
    }
    @PostMapping("/sys/logout")
    @ApiOperation(value="退出")
    public CommonResult<?> logout(@RequestHeader(name = "Authorization") String authorization) {
        String token = authorization.replace("Bearer ","");
        authService.logout(token);
        return CommonResult.ok();
    }
    @PostMapping("/sys/playUser")
    @ApiOperation(value="扮演用户")
    @SaCheckPermission("sys:playUser")
    public CommonResult<?> playUser(@RequestBody Dict dict) {
        Long userId = dict.getLong("userId");
        return CommonResult.data(authService.playUser(userId));
    }
    @PostMapping("/sys/unPlayUser")
    @ApiOperation(value="退出扮演用户")
    public CommonResult<?> unPlayUser() {
        return CommonResult.data(authService.unPlayUser());
    }
    @PostMapping("/sys/captcha")
    @ApiOperation(value="图片验证码")
    public CommonResult<?> captcha(@RequestBody CaptchaParam param) {
        return CommonResult.data(captchaManager.create(param));
    }
    @PostMapping("/sys/getCaptchaOpenFlag")
    @ApiOperation(value="获取登录图片验证码是否启用标识")
    public CommonResult<?> getCaptchaOpenFlag() {
        Dict dict = Dict.create();
        dict.put("flag", ConstantContextHolder.getCaptchaOpenFlag());
        return CommonResult.data(dict);
    }
}
