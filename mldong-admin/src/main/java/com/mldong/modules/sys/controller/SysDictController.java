package com.mldong.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

import java.util.List;

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
import com.mldong.common.scanner.model.DictModel;
import com.mldong.common.validator.Groups;
import com.mldong.modules.sys.dto.SysDictKeyParam;
import com.mldong.modules.sys.dto.SysDictPageParam;
import com.mldong.modules.sys.dto.SysDictParam;
import com.mldong.modules.sys.entity.SysDict;
import com.mldong.modules.sys.service.SysDictService;

@RestController
@RequestMapping("/sys/dict")
@Api(tags="sys-字典管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="字典管理",scope="sys:dict:index")
    })
})
public class SysDictController {
	@Autowired
	private SysDictService sysDictService;

	@PostMapping("save")
	@ApiOperation(value="添加字典", notes="添加字典",authorizations={
		@Authorization(value="添加字典",scopes={
	    	@AuthorizationScope(description="添加字典",scope="sys:dict:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysDictParam param) {
		int count = sysDictService.save(param);
		if(count>0) {
			return CommonResult.success("添加字典成功", null);
		} else {
			return CommonResult.fail("添加字典失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改字典", notes="修改字典",authorizations={
		@Authorization(value="修改字典",scopes={
	    	@AuthorizationScope(description="修改字典",scope="sys:dict:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysDictParam param) {
		int count = sysDictService.update(param);
		if(count>0) {
			return CommonResult.success("修改字典成功", null);
		} else {
			return CommonResult.fail("修改字典失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除字典", notes="删除字典",authorizations={
		@Authorization(value="删除字典",scopes={
	    	@AuthorizationScope(description="删除字典",scope="sys:dict:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysDictService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除字典成功", null);
		} else {
			return CommonResult.fail("删除字典失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取字典", notes="通过id获取字典",authorizations={
		@Authorization(value="通过id获取字典",scopes={
	    	@AuthorizationScope(description="通过id获取字典",scope="sys:dict:get")
	    })
	})
	public CommonResult<SysDict> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取字典成功",sysDictService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询字典", notes="分页查询字典",authorizations={
		@Authorization(value="分页查询字典",scopes={
	    	@AuthorizationScope(description="分页查询字典",scope="sys:dict:list")
	    })
	})
	public CommonResult<CommonPage<SysDict>> list(@RequestBody @Validated SysDictPageParam param) {
		return CommonResult.success("查询字典成功",sysDictService.list(param));
	}
	@PostMapping("getByDictKey")
	@ApiOperation(value="通过字典唯一编码查询", notes="通过字典唯一编码查询")
	public CommonResult<DictModel> getByDictKey(@RequestBody @Validated SysDictKeyParam param) {
		return CommonResult.success("通过字典唯一编码查询成功",sysDictService.getByDictKey(param));
	}
	@PostMapping("listAllEnum")
	@ApiOperation(value="获取所有字典枚举", notes="获取所有字典枚举",authorizations={
		@Authorization(value="获取所有字典枚举",scopes={
	    	@AuthorizationScope(description="获取所有字典枚举",scope="sys:dict:listAllEnum")
	    })
	})
	public CommonResult<List<DictModel>> listAllEnum() {
		return CommonResult.success("获取所有字典枚举成功",sysDictService.listAllEnum());
	}
}
