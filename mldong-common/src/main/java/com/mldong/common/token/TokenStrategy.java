package com.mldong.common.token;

import java.util.Map;

public interface TokenStrategy {
	/**
	 * 通过用户id和用户名称生成token
	 * @param userId 用户id
	 * @param userName 用户密码
	 * @param map 额外参数
	 * @return
	 */
	public String generateToken(Long userId,String userName, Map<String,Object> map);
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

	/**
	 * 获取额外参数
	 * @param  token
	 * @return
	 */
	public Map<String,Object> getExt(String token);

}
