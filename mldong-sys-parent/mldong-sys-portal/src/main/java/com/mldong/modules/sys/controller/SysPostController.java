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
import com.mldong.modules.sys.dto.SysPostParam;
import com.mldong.modules.sys.dto.SysPostPageParam;
import com.mldong.modules.sys.entity.SysPost;
import com.mldong.modules.sys.service.SysPostService;

@RestController
@RequestMapping("/sys/post")
@Api(tags="sys-岗位管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="岗位管理",scope="sys:post:index")
    })
})
public class SysPostController {
	@Autowired
	private SysPostService sysPostService;

	@PostMapping("save")
	@ApiOperation(value="添加岗位", notes="添加岗位",authorizations={
		@Authorization(value="添加岗位",scopes={
	    	@AuthorizationScope(description="添加岗位",scope="sys:post:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysPostParam param) {
		int count = sysPostService.save(param);
		if(count>0) {
			return CommonResult.success("添加岗位成功", null);
		} else {
			return CommonResult.fail("添加岗位失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改岗位", notes="修改岗位",authorizations={
		@Authorization(value="修改岗位",scopes={
	    	@AuthorizationScope(description="修改岗位",scope="sys:post:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysPostParam param) {
		int count = sysPostService.update(param);
		if(count>0) {
			return CommonResult.success("修改岗位成功", null);
		} else {
			return CommonResult.fail("修改岗位失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除岗位", notes="删除岗位",authorizations={
		@Authorization(value="删除岗位",scopes={
	    	@AuthorizationScope(description="删除岗位",scope="sys:post:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysPostService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除岗位成功", null);
		} else {
			return CommonResult.fail("删除岗位失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取岗位", notes="通过id获取岗位",authorizations={
		@Authorization(value="通过id获取岗位",scopes={
	    	@AuthorizationScope(description="通过id获取岗位",scope="sys:post:get")
	    })
	})
	public CommonResult<SysPost> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取岗位成功",sysPostService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询岗位", notes="分页查询岗位",authorizations={
		@Authorization(value="分页查询岗位",scopes={
	    	@AuthorizationScope(description="分页查询岗位",scope="sys:post:list")
	    })
	})
	public CommonResult<CommonPage<SysPost>> list(@RequestBody @Validated SysPostPageParam param) {
		return CommonResult.success("查询岗位成功",sysPostService.list(param));
	}
}
