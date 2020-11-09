package com.mldong.modules.cms.controller;

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
import com.mldong.modules.cms.dto.CmsCategoryParam;
import com.mldong.modules.cms.dto.CmsCategoryPageParam;
import com.mldong.modules.cms.entity.CmsCategory;
import com.mldong.modules.cms.service.CmsCategoryService;

@RestController
@RequestMapping("/cms/category")
@Api(tags="cms-栏目管理",authorizations={
    @Authorization(value="cms|内容管理",scopes={
    	@AuthorizationScope(description="栏目管理",scope="cms:category:index")
    })
})
public class CmsCategoryController {
	@Autowired
	private CmsCategoryService cmsCategoryService;

	@PostMapping("save")
	@ApiOperation(value="添加栏目", notes="cms:category:save")
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) CmsCategoryParam param) {
		int count = cmsCategoryService.save(param);
		if(count>0) {
			return CommonResult.success("添加栏目成功", null);
		} else {
			return CommonResult.fail("添加栏目失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改栏目", notes="cms:category:update")
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) CmsCategoryParam param) {
		int count = cmsCategoryService.update(param);
		if(count>0) {
			return CommonResult.success("修改栏目成功", null);
		} else {
			return CommonResult.fail("修改栏目失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除栏目", notes="cms:category:remove")
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = cmsCategoryService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除栏目成功", null);
		} else {
			return CommonResult.fail("删除栏目失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取栏目", notes = "cms:category:get")
	public CommonResult<CmsCategory> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取栏目成功",cmsCategoryService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询栏目", notes="cms:category:list")
	public CommonResult<CommonPage<CmsCategory>> list(@RequestBody @Validated CmsCategoryPageParam param) {
		return CommonResult.success("查询栏目成功",cmsCategoryService.list(param));
	}
}
