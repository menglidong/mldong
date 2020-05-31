package com.mldong.modules.sys.dao;

import java.util.List;

import com.mldong.modules.sys.dto.SysUserResult;

public interface SysUserDao {
	public List<SysUserResult> selectWithRoleName();
}
