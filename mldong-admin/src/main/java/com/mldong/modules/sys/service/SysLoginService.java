package com.mldong.modules.sys.service;

import com.mldong.modules.sys.dto.SysLoginParam;
import com.mldong.modules.sys.vo.SysLoginVo;

/**
 * 登录退出接口
 * @author mldong
 *
 */
public interface SysLoginService {
	/**
	 * 登录
	 * @param param
	 * @return
	 */
	public SysLoginVo login(SysLoginParam param);
	/**
	 * 退出
	 * @param token 临时凭证
	 * @return
	 */
	public int logout(String token);
}
