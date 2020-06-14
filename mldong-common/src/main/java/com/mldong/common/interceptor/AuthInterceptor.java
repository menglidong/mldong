package com.mldong.common.interceptor;

import io.swagger.annotations.ApiOperation;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mldong.common.access.AccessInitProcessor;
import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
import com.mldong.common.logger.ILoggerStore;
import com.mldong.common.logger.LoggerModel;
import com.mldong.common.web.RequestHolder;
@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	private AuthInterceptorService authInterceptorService;
	@Autowired
	private ILoggerStore loggerStore;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            // 在这里新增logger的记录
			LoggerModel loggerModel = new LoggerModel();
			RequestHolder.setLoggerModel(loggerModel);
			loggerModel.setStartTime(System.currentTimeMillis());
			String trackId = UUID.randomUUID().toString();
			loggerModel.setTrackId(trackId);
			String uri = request.getRequestURI();
			loggerModel.setUri(uri);
			String queryString = request.getQueryString();
			loggerModel.setQueryString(null==queryString?"":queryString);
			String method = request.getMethod();
			loggerModel.setMethod(method);
			String ip = RequestHolder.getIPAddress();
			loggerModel.setIp(ip);
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			ApiOperation apiOperation =  handlerMethod.getMethodAnnotation(ApiOperation.class);
			if(null!=apiOperation) {
				loggerModel.setDescription(apiOperation.value());
			}
			AuthIgnore authIgnore = handlerMethod.getMethodAnnotation(AuthIgnore.class);
			if(null != authIgnore) {
				// 要忽略权限
				return true;
			}
			String token = RequestHolder.getToken();
			loggerModel.setToken(token);
			if("".equals(token)) {
				throw new BizException(GlobalErrEnum.GL99990401);
			}
			Long userId = authInterceptorService.getUserId(token);
			loggerModel.setUserId(userId);
			if(!authInterceptorService.verifyToken(token)) {
				// token校验不通过
				throw new BizException(GlobalErrEnum.GL99990401);
			}
			RequestHolder.setUserId(userId);
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
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LoggerModel loggerModel = RequestHolder.getLoggerModel();
		if(null != loggerModel) {
			loggerModel.setEndTime(System.currentTimeMillis());
			loggerStore.save(loggerModel);
		}
		// 记得要移除！！！！！
		RequestHolder.removeLoggerModel();
		// 记得要移除！！！！！
		RequestHolder.removeUserId();
	}
}
