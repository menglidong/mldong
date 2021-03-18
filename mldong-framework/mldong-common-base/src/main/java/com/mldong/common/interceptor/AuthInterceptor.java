package com.mldong.common.interceptor;

import com.mldong.common.access.AccessInitProcessor;
import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.annotation.LogIgnore;
import com.mldong.common.base.SsoUser;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
import com.mldong.common.logger.ILoggerStore;
import com.mldong.common.logger.IRequestLogStore;
import com.mldong.common.logger.LoggerModel;
import com.mldong.common.tool.StringTool;
import com.mldong.common.web.RequestHolder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
@Component
@ConditionalOnBean(AuthInterceptorService.class)
public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	private AuthInterceptorService authInterceptorService;
	@Autowired
	private ILoggerStore loggerStore;
	@Autowired(required = false)
	private IRequestLogStore requestLogStore;
	@Value("${security.enable:false}")
	private Boolean securityEnabled;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		if(uri.equals("/error") || antPathMatcher.match("/oauth/**", uri)) {
			return true;
		}
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            // 在这里新增logger的记录
			LoggerModel loggerModel = new LoggerModel();
			RequestHolder.setLoggerModel(loggerModel);
			loggerModel.setStartTime(System.currentTimeMillis());
			String trackId = UUID.randomUUID().toString();
			loggerModel.setTrackId(trackId);
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
			if(Boolean.FALSE.equals(securityEnabled)) {
				AuthIgnore authIgnore = handlerMethod.getMethodAnnotation(AuthIgnore.class);
				if (null != authIgnore) {
					// 要忽略权限
					return true;
				}
				String token = RequestHolder.getToken();
				loggerModel.setToken(token);
				if ("".equals(token)) {
					throw new BizException(GlobalErrEnum.GL99990401);
				}
				Long userId = authInterceptorService.getUserId(token);
				RequestHolder.setUserId(userId);
				loggerModel.setUserId(userId);
				String userName = authInterceptorService.getUserName(token);
				loggerModel.setUserName(userName);
				Map<String, Object> ext = authInterceptorService.getExt(token);
				loggerModel.setExt(ext);
				if (!authInterceptorService.verifyToken(token)) {
					// token校验不通过
					throw new BizException(GlobalErrEnum.GL99990401);
				}
				String access = AccessInitProcessor.getAccess(apiOperation);
				if (StringTool.isEmpty(access)) {
					// 没有定义，直接放行
					return true;
				}
				if (!authInterceptorService.hasAuth(userId, access)) {
					// 无权限访问
					throw new BizException(GlobalErrEnum.GL99990403);
				}
			} else {
				// oauth2授权模式
				Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if(obj instanceof  SsoUser) {
					SsoUser user = (SsoUser) obj;
					loggerModel.setUserId(user.getUserId());
					loggerModel.setUserName(user.getUsername());
					loggerModel.setExt(user.getExt());
					loggerModel.setToken(RequestHolder.getToken());
					RequestHolder.setUserId(user.getUserId());
				}
				return true;
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
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			LogIgnore authIgnore = handlerMethod.getMethodAnnotation(LogIgnore.class);
			if (null == authIgnore) {
				loggerStore.save(loggerModel);
				if(requestLogStore!=null) {
					requestLogStore.saveRequestLog(loggerModel);
				}
			}
		}
		// 记得要移除！！！！！
		RequestHolder.removeLoggerModel();
		// 记得要移除！！！！！
		RequestHolder.removeUserId();
	}
}
