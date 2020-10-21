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
import com.mldong.modules.sys.dto.SysDeptParam;
import com.mldong.modules.sys.dto.SysDeptPageParam;
import com.mldong.modules.sys.entity.SysDept;
import com.mldong.modules.sys.service.SysDeptService;

@RestController
@RequestMapping("/sys/dept")
@Api(tags="sys-部门管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="部门管理",scope="sys:dept:index")
    })
})
public class SysDeptController {
	@Autowired
	private SysDeptService sysDeptService;

	@PostMapping("save")
	@ApiOperation(value="添加部门", notes="添加部门",authorizations={
		@Authorization(value="添加部门",scopes={
	    	@AuthorizationScope(description="添加部门",scope="sys:dept:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysDeptParam param) {
		int count = sysDeptService.save(param);
		if(count>0) {
			return CommonResult.success("添加部门成功", null);
		} else {
			return CommonResult.fail("添加部门失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改部门", notes="修改部门",authorizations={
		@Authorization(value="修改部门",scopes={
	    	@AuthorizationScope(description="修改部门",scope="sys:dept:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysDeptParam param) {
		int count = sysDeptService.update(param);
		if(count>0) {
			return CommonResult.success("修改部门成功", null);
		} else {
			return CommonResult.fail("修改部门失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除部门", notes="删除部门",authorizations={
		@Authorization(value="删除部门",scopes={
	    	@AuthorizationScope(description="删除部门",scope="sys:dept:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysDeptService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除部门成功", null);
		} else {
			return CommonResult.fail("删除部门失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取部门", notes="通过id获取部门",authorizations={
		@Authorization(value="通过id获取部门",scopes={
	    	@AuthorizationScope(description="通过id获取部门",scope="sys:dept:get")
	    })
	})
	public CommonResult<SysDept> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取部门成功",sysDeptService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询部门", notes="分页查询部门",authorizations={
		@Authorization(value="分页查询部门",scopes={
	    	@AuthorizationScope(description="分页查询部门",scope="sys:dept:list")
	    })
	})
	public CommonResult<CommonPage<SysDept>> list(@RequestBody @Validated SysDeptPageParam param) {
		return CommonResult.success("查询部门成功",sysDeptService.list(param));
	}
}
