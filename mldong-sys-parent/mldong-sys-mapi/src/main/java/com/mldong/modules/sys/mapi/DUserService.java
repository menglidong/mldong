package com.mldong.modules.sys.mapi;

import com.mldong.modules.sys.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(tags = "用户服务")
public interface DUserService {
    @ApiOperation(value = "获取用户个人信息")
    public SysUser get(Long userId);
}
