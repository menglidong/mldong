package com.mldong.modules.cms.controller;

import com.mldong.common.annotation.AuthIgnore;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.IdParam;
import com.mldong.modules.cms.dto.CmsArticlePageParam;
import com.mldong.modules.cms.entity.CmsArticle;
import com.mldong.modules.cms.service.CmsArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cms/article")
@Api(tags="cms-文章管理")
public class CmsArticleController {
    @Resource
    private CmsArticleService cmsArticleService;
    @PostMapping("get")
    @ApiOperation(value="通过id获取文章", notes="通过id获取文章")
    @AuthIgnore
    public CommonResult<CmsArticle> get(@RequestBody @Validated IdParam param) {
        return CommonResult.success("获取文章成功",cmsArticleService.get(param.getId()));
    }

    @PostMapping("list")
    @ApiOperation(value="分页查询文章", notes="分页查询文章")
    @AuthIgnore
    public CommonResult<CommonPage<CmsArticle>> list(@RequestBody @Validated CmsArticlePageParam param) {
        return CommonResult.success("查询文章成功",cmsArticleService.list(param));
    }
}
