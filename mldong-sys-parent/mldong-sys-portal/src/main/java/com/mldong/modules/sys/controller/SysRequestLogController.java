package com.mldong.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.IdParam;
import com.mldong.common.base.IdsParam;
import com.mldong.common.validator.Groups;
import com.mldong.modules.sys.dto.SysRequestLogParam;
import com.mldong.modules.sys.dto.SysRequestLogPageParam;
import com.mldong.modules.sys.entity.SysRequestLog;
import com.mldong.modules.sys.service.SysRequestLogService;

@RestController
@RequestMapping("/sys/requestLog")
@Api(tags="sys-请求日志管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="请求日志管理",scope="sys:requestLog:index")
    })
})
public class SysRequestLogController {
	@Autowired
	private SysRequestLogService sysRequestLogService;

	@PostMapping("save")
	@ApiOperation(value="添加请求日志", notes="添加请求日志",authorizations={
		@Authorization(value="添加请求日志",scopes={
	    	@AuthorizationScope(description="添加请求日志",scope="sys:requestLog:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysRequestLogParam param) {
		int count = sysRequestLogService.save(param);
		if(count>0) {
			return CommonResult.success("添加请求日志成功", null);
		} else {
			return CommonResult.fail("添加请求日志失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改请求日志", notes="修改请求日志",authorizations={
		@Authorization(value="修改请求日志",scopes={
	    	@AuthorizationScope(description="修改请求日志",scope="sys:requestLog:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysRequestLogParam param) {
		int count = sysRequestLogService.update(param);
		if(count>0) {
			return CommonResult.success("修改请求日志成功", null);
		} else {
			return CommonResult.fail("修改请求日志失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除请求日志", notes="删除请求日志",authorizations={
		@Authorization(value="删除请求日志",scopes={
	    	@AuthorizationScope(description="删除请求日志",scope="sys:requestLog:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysRequestLogService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除请求日志成功", null);
		} else {
			return CommonResult.fail("删除请求日志失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取请求日志", notes="通过id获取请求日志",authorizations={
		@Authorization(value="通过id获取请求日志",scopes={
	    	@AuthorizationScope(description="通过id获取请求日志",scope="sys:requestLog:get")
	    })
	})
	public CommonResult<SysRequestLog> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取请求日志成功",sysRequestLogService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询请求日志", notes="分页查询请求日志",authorizations={
		@Authorization(value="分页查询请求日志",scopes={
	    	@AuthorizationScope(description="分页查询请求日志",scope="sys:requestLog:list")
	    })
	})
	public CommonResult<CommonPage<SysRequestLog>> list(@RequestBody @Validated SysRequestLogPageParam param) {
		return CommonResult.success("查询请求日志成功",sysRequestLogService.list(param));
	}
}
