package com.mldong.common.interceptor;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mldong.common.access.AccessInitProcessor;
import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.base.constant.CommonConstants;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Autowired(required=false)
	private AuthInterceptorService authInterceptorService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			AuthIgnore authIgnore = handlerMethod.getMethodAnnotation(AuthIgnore.class);
			if(null != authIgnore) {
				// 要忽略权限
				return true;
			}
			String token = getToken(request);
			if("".equals(token)) {
				throw new BizException(GlobalErrEnum.GL99990401);
			}
			if(authInterceptorService == null) {
				// 空就不做处理了
				return true;
			}
			if(!authInterceptorService.verifyToken(token)) {
				// token校验不通过
				throw new BizException(GlobalErrEnum.GL99990401);
			}
			ApiOperation apiOperation = handlerMethod.getMethodAnnotation(ApiOperation.class);
			String access = AccessInitProcessor.getAccess(apiOperation);
			if(null == access) {
				// 没有定义，直接放行
				return true;
			}
			if(!authInterceptorService.hasAuth(token, access)){
				// 无权限访问
				throw new BizException(GlobalErrEnum.GL99990403);
			}
		}
		return true;
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
