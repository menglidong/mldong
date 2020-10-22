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
import com.mldong.modules.sys.dto.SysRoleParam;
import com.mldong.modules.sys.dto.SysRolePageParam;
import com.mldong.modules.sys.entity.SysRole;
import com.mldong.modules.sys.service.SysRoleService;

@RestController
@RequestMapping("/sys/role")
@Api(tags="sys-角色管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="角色管理",scope="sys:role:index")
    })
})
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;

	@PostMapping("save")
	@ApiOperation(value="添加角色", notes="添加角色",authorizations={
		@Authorization(value="添加角色",scopes={
	    	@AuthorizationScope(description="添加角色",scope="sys:role:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysRoleParam param) {
		int count = sysRoleService.save(param);
		if(count>0) {
			return CommonResult.success("添加角色成功", null);
		} else {
			return CommonResult.fail("添加角色失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改角色", notes="修改角色",authorizations={
		@Authorization(value="修改角色",scopes={
	    	@AuthorizationScope(description="修改角色",scope="sys:role:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysRoleParam param) {
		int count = sysRoleService.update(param);
		if(count>0) {
			return CommonResult.success("修改角色成功", null);
		} else {
			return CommonResult.fail("修改角色失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除角色", notes="删除角色",authorizations={
		@Authorization(value="删除角色",scopes={
	    	@AuthorizationScope(description="删除角色",scope="sys:role:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysRoleService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除角色成功", null);
		} else {
			return CommonResult.fail("删除角色失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取角色", notes="通过id获取角色",authorizations={
		@Authorization(value="通过id获取角色",scopes={
	    	@AuthorizationScope(description="通过id获取角色",scope="sys:role:get")
	    })
	})
	public CommonResult<SysRole> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取角色成功",sysRoleService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询角色", notes="分页查询角色",authorizations={
		@Authorization(value="分页查询角色",scopes={
	    	@AuthorizationScope(description="分页查询角色",scope="sys:role:list")
	    })
	})
	public CommonResult<CommonPage<SysRole>> list(@RequestBody @Validated SysRolePageParam param) {
		return CommonResult.success("查询角色成功",sysRoleService.list(param));
	}
}
