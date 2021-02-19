package com.mldong.modules.sys.controller;

import com.mldong.common.annotation.VerifyCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.base.CommonResult;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.sys.dto.SysLoginParam;
import com.mldong.modules.sys.service.SysLoginService;
import com.mldong.modules.sys.vo.SysLoginVo;

@RestController
@Api(tags="sys-登录模块")
public class SysLoginController {
	@Autowired
	private SysLoginService sysLoginService;
	/**
	 * 登录系统
	 * @param param
	 * @return
	 */
	@PostMapping("/sys/login")
	@AuthIgnore
	@VerifyCaptcha
	@ApiOperation(value="登录系统", notes="登录系统")
	public CommonResult<SysLoginVo> login(@RequestBody @Validated SysLoginParam param) {
		return CommonResult.success("登录成功", sysLoginService.login(param));
	}
	/**
	 * 退出系统
	 * @return
	 */
	@PostMapping("/sys/logout")
	@ApiOperation(value="退出系统", notes="退出系统")
	@AuthIgnore
	public CommonResult<?> logout() {
		String token = RequestHolder.getToken();
		sysLoginService.logout(token);
		return CommonResult.success("退出成功", null);
	}
}
