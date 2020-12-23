package com.mldong.modules.cms.controller;

import com.mldong.modules.cms.dto.CmsArticleWithExt;
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
import com.mldong.modules.cms.dto.CmsArticleParam;
import com.mldong.modules.cms.dto.CmsArticlePageParam;
import com.mldong.modules.cms.entity.CmsArticle;
import com.mldong.modules.cms.service.CmsArticleService;

@RestController
@RequestMapping("/cms/article")
@Api(tags="cms-文章管理",authorizations={
    @Authorization(value="cms|内容管理",scopes={
    	@AuthorizationScope(description="文章管理",scope="cms:article:index")
    })
})
public class CmsArticleController {
	@Autowired
	private CmsArticleService cmsArticleService;

	@PostMapping("save")
	@ApiOperation(value="添加文章", notes = "cms:article:save")
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) CmsArticleParam param) {
		int count = cmsArticleService.save(param);
		if(count>0) {
			return CommonResult.success("添加文章成功", null);
		} else {
			return CommonResult.fail("添加文章失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改文章", notes = "cms:article:update")
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) CmsArticleParam param) {
		int count = cmsArticleService.update(param);
		if(count>0) {
			return CommonResult.success("修改文章成功", null);
		} else {
			return CommonResult.fail("修改文章失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除文章", notes = "cms:article:remove")
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = cmsArticleService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除文章成功", null);
		} else {
			return CommonResult.fail("删除文章失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取文章", notes = "cms:article:get")
	public CommonResult<CmsArticle> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取文章成功",cmsArticleService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询文章", notes = "cms:article:list")
	public CommonResult<CommonPage<CmsArticle>> list(@RequestBody @Validated CmsArticlePageParam param) {
		return CommonResult.success("查询文章成功",cmsArticleService.list(param));
	}
	@PostMapping("listWithExt")
	@ApiOperation(value="分页查询文章-包含扩展信息", notes = "cms:article:listWithExt")
	public CommonResult<CommonPage<CmsArticleWithExt>> listWithExt(@RequestBody @Validated CmsArticlePageParam param) {
		return CommonResult.success("查询文章成功",cmsArticleService.listWithExt(param));
	}
	@PostMapping("getWithExt")
	@ApiOperation(value="通过id获取文章-包含扩展信息", notes = "cms:article:getWithExt")
	public CommonResult<CmsArticleWithExt> getWithExt(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取文章成功",cmsArticleService.getWithExt(param.getId()));
	}
	@PostMapping("listOnDataScope")
	@ApiOperation(value="分页查询文章-数据权限1", notes = "cms:article:listOnDataScope")
	public CommonResult<CommonPage<CmsArticleWithExt>> listOnDataScope(@RequestBody @Validated CmsArticlePageParam param) {
		return CommonResult.success("查询文章成功",cmsArticleService.listOnDataScope(param));
	}
	@PostMapping("listOnDataScope2")
	@ApiOperation(value="分页查询文章-数据权限2", notes = "cms:article:listOnDataScope2")
	public CommonResult<CommonPage<CmsArticleWithExt>> listOnDataScope2(@RequestBody @Validated CmsArticlePageParam param) {
		return CommonResult.success("查询文章成功",cmsArticleService.listOnDataScope2(param));
	}
}
