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
import com.mldong.modules.sys.dto.SysConfigParam;
import com.mldong.modules.sys.dto.SysConfigPageParam;
import com.mldong.modules.sys.entity.SysConfig;
import com.mldong.modules.sys.service.SysConfigService;
// START###################
// ###################END
@RestController
@RequestMapping("/sys/config")
@Api(tags="sys-参数配置管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="参数配置管理",scope="sys:config:index")
    })
})
public class SysConfigController {
	@Autowired
	private SysConfigService sysConfigService;
	// START###################
	// ###################END
	@PostMapping("save")
	@ApiOperation(value="添加参数配置", notes="添加参数配置",authorizations={
		@Authorization(value="添加参数配置",scopes={
	    	@AuthorizationScope(description="添加参数配置",scope="sys:config:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysConfigParam param) {
		int count = sysConfigService.save(param);
		if(count>0) {
			return CommonResult.success("添加参数配置成功", null);
		} else {
			return CommonResult.fail("添加参数配置失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改参数配置", notes="修改参数配置",authorizations={
		@Authorization(value="修改参数配置",scopes={
	    	@AuthorizationScope(description="修改参数配置",scope="sys:config:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysConfigParam param) {
		int count = sysConfigService.update(param);
		if(count>0) {
			return CommonResult.success("修改参数配置成功", null);
		} else {
			return CommonResult.fail("修改参数配置失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除参数配置", notes="删除参数配置",authorizations={
		@Authorization(value="删除参数配置",scopes={
	    	@AuthorizationScope(description="删除参数配置",scope="sys:config:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysConfigService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除参数配置成功", null);
		} else {
			return CommonResult.fail("删除参数配置失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取参数配置", notes="通过id获取参数配置",authorizations={
		@Authorization(value="通过id获取参数配置",scopes={
	    	@AuthorizationScope(description="通过id获取参数配置",scope="sys:config:get")
	    })
	})
	public CommonResult<SysConfig> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取参数配置成功",sysConfigService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询参数配置", notes="分页查询参数配置",authorizations={
		@Authorization(value="分页查询参数配置",scopes={
	    	@AuthorizationScope(description="分页查询参数配置",scope="sys:config:list")
	    })
	})
	public CommonResult<CommonPage<SysConfig>> list(@RequestBody @Validated SysConfigPageParam param) {
		return CommonResult.success("查询参数配置成功",sysConfigService.list(param));
	}
	// START###################
	// ###################END
}
