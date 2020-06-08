package com.mldong.modules.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.constant.CommonConstants;
import com.mldong.modules.sys.dto.SysLoginParam;
import com.mldong.modules.sys.service.SysLoginService;
import com.mldong.modules.sys.vo.SysLoginVo;

@RestController
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
	public CommonResult<SysLoginVo> login(@RequestBody @Validated SysLoginParam param) {
		return CommonResult.success("登录成功", sysLoginService.login(param));
	}
	/**
	 * 退出系统
	 * @return
	 */
	@PostMapping("/sys/logout")
	@AuthIgnore
	public CommonResult<?> logout(HttpServletRequest request) {
		String token = getToken(request);
		sysLoginService.logout(token);
		return CommonResult.success("退出成功", null);
	}
	private String getToken(HttpServletRequest request) {
		String token = "";
		token = request.getHeader(CommonConstants.TOKEN);
		if(StringUtils.isEmpty(token)) {
			token = request.getParameter(CommonConstants.TOKEN);
		}
		return token;
	}
}
