package com.mldong.common.interceptor;
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
}
