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
import com.mldong.modules.sys.dto.SysDictItemParam;
import com.mldong.modules.sys.dto.SysDictItemPageParam;
import com.mldong.modules.sys.entity.SysDictItem;
import com.mldong.modules.sys.service.SysDictItemService;

@RestController
@RequestMapping("/sys/dictItem")
@Api(tags="sys-字典项管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="字典项管理",scope="sys:dictItem:index")
    })
})
public class SysDictItemController {
	@Autowired
	private SysDictItemService sysDictItemService;

	@PostMapping("save")
	@ApiOperation(value="添加字典项", notes="添加字典项",authorizations={
		@Authorization(value="添加字典项",scopes={
	    	@AuthorizationScope(description="添加字典项",scope="sys:dictItem:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysDictItemParam param) {
		int count = sysDictItemService.save(param);
		if(count>0) {
			return CommonResult.success("添加字典项成功", null);
		} else {
			return CommonResult.fail("添加字典项失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改字典项", notes="修改字典项",authorizations={
		@Authorization(value="修改字典项",scopes={
	    	@AuthorizationScope(description="修改字典项",scope="sys:dictItem:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysDictItemParam param) {
		int count = sysDictItemService.update(param);
		if(count>0) {
			return CommonResult.success("修改字典项成功", null);
		} else {
			return CommonResult.fail("修改字典项失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除字典项", notes="删除字典项",authorizations={
		@Authorization(value="删除字典项",scopes={
	    	@AuthorizationScope(description="删除字典项",scope="sys:dictItem:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysDictItemService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除字典项成功", null);
		} else {
			return CommonResult.fail("删除字典项失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取字典项", notes="通过id获取字典项",authorizations={
		@Authorization(value="通过id获取字典项",scopes={
	    	@AuthorizationScope(description="通过id获取字典项",scope="sys:dictItem:get")
	    })
	})
	public CommonResult<SysDictItem> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取字典项成功",sysDictItemService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询字典项", notes="分页查询字典项",authorizations={
		@Authorization(value="分页查询字典项",scopes={
	    	@AuthorizationScope(description="分页查询字典项",scope="sys:dictItem:list")
	    })
	})
	public CommonResult<CommonPage<SysDictItem>> list(@RequestBody @Validated SysDictItemPageParam param) {
		return CommonResult.success("查询字典项成功",sysDictItemService.list(param));
	}
}
