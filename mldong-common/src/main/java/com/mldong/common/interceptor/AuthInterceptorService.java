package com.mldong.common.interceptor;

import java.util.Map;

/**
 * 权限拦截器所需要的服务，由业务层实现
 * @author mldong
 *
 */
public interface AuthInterceptorService {
	/**
	 * 校验token
	 * @param token
	 * @return
	 */
	public boolean verifyToken(String token);
	/**
	 * 用户是否有权限
	 * @param token token
	 * @param access 权限标识
	 * @return
	 */
	public boolean hasAuth(String token,String access);
	/**
	 * 通过token获取id
	 * @param token
	 * @return
	 */
	public Long getUserId(String token);

	/**
	 * 获取用户名
	 * @param token
	 * @return
	 */
	public String getUserName(String token);

	/**
	 * 获取扩展参数
	 * @param token
	 * @return
	 */
	public Map<String,Object> getExt(String token);
}
