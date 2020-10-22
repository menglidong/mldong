package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;

import com.mldong.common.base.PageParam;
import com.mldong.modules.sys.entity.SysUser;
@ApiModel(description="用户分页查询实体")
public class SysUserPageParam extends PageParam<SysUser> {
	
	
}
