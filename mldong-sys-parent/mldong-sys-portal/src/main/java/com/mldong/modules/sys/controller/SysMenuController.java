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
import com.mldong.modules.sys.dto.SysMenuParam;
import com.mldong.modules.sys.dto.SysMenuPageParam;
import com.mldong.modules.sys.entity.SysMenu;
import com.mldong.modules.sys.service.SysMenuService;

@RestController
@RequestMapping("/sys/menu")
@Api(tags="sys-菜单管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="菜单管理",scope="sys:menu:index")
    })
})
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;

	@PostMapping("save")
	@ApiOperation(value="添加菜单", notes="添加菜单",authorizations={
		@Authorization(value="添加菜单",scopes={
	    	@AuthorizationScope(description="添加菜单",scope="sys:menu:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysMenuParam param) {
		int count = sysMenuService.save(param);
		if(count>0) {
			return CommonResult.success("添加菜单成功", null);
		} else {
			return CommonResult.fail("添加菜单失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改菜单", notes="修改菜单",authorizations={
		@Authorization(value="修改菜单",scopes={
	    	@AuthorizationScope(description="修改菜单",scope="sys:menu:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysMenuParam param) {
		int count = sysMenuService.update(param);
		if(count>0) {
			return CommonResult.success("修改菜单成功", null);
		} else {
			return CommonResult.fail("修改菜单失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除菜单", notes="删除菜单",authorizations={
		@Authorization(value="删除菜单",scopes={
	    	@AuthorizationScope(description="删除菜单",scope="sys:menu:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysMenuService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除菜单成功", null);
		} else {
			return CommonResult.fail("删除菜单失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取菜单", notes="通过id获取菜单",authorizations={
		@Authorization(value="通过id获取菜单",scopes={
	    	@AuthorizationScope(description="通过id获取菜单",scope="sys:menu:get")
	    })
	})
	public CommonResult<SysMenu> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取菜单成功",sysMenuService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询菜单", notes="分页查询菜单",authorizations={
		@Authorization(value="分页查询菜单",scopes={
	    	@AuthorizationScope(description="分页查询菜单",scope="sys:menu:list")
	    })
	})
	public CommonResult<CommonPage<SysMenu>> list(@RequestBody @Validated SysMenuPageParam param) {
		return CommonResult.success("查询菜单成功",sysMenuService.list(param));
	}
}
