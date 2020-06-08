package com.mldong.common.token;

public interface TokenStrategy {
	/**
	 * 通过用户id和用户生成token
	 * @param userId 用户id
	 * @param userName 用户密码
	 * @return
	 */
	public String generateToken(Long userId,String userName);
	/**
	 * 校验token是否合法
	 * @param token 临时令牌
	 * @return
	 */
	public boolean verifyToken(String token);
	/**
	 * 从token中获取用户id
	 * @param token
	 * @return
	 */
	public Long getUserId(String token);
	/**
	 * 从token中获取用户名
	 * @param token
	 * @return
	 */
	public String getUserName(String token);
}
