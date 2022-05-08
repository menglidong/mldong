package com.mldong.modules.wf.controller;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.IdParam;
import com.mldong.common.base.IdsParam;
import com.mldong.common.validator.Groups;
import com.mldong.modules.snakerflow.dto.WfModelGroupPageParam;
import com.mldong.modules.snakerflow.dto.WfModelGroupParam;
import com.mldong.modules.snakerflow.entity.WfModelGroup;
import com.mldong.modules.snakerflow.service.WfModelGroupService;
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

@RestController
@RequestMapping("/wf/modelGroup")
@Api(tags="wf-模型分组管理",authorizations={
    @Authorization(value="wf|工作流",scopes={
    	@AuthorizationScope(description="模型分组管理",scope="wf:modelGroup:index")
    })
})
public class WfModelGroupController {
	@Autowired
	private WfModelGroupService wfModelGroupService;

	@PostMapping("save")
	@ApiOperation(value="添加模型分组", notes="wf:modelGroup:save")
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) WfModelGroupParam param) {
		int count = wfModelGroupService.save(param);
		if(count>0) {
			return CommonResult.success("添加模型分组成功", null);
		} else {
			return CommonResult.fail("添加模型分组失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改模型分组", notes="wf:modelGroup:update")
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) WfModelGroupParam param) {
		int count = wfModelGroupService.update(param);
		if(count>0) {
			return CommonResult.success("修改模型分组成功", null);
		} else {
			return CommonResult.fail("修改模型分组失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除模型分组", notes="wf:modelGroup:remove")
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = wfModelGroupService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除模型分组成功", null);
		} else {
			return CommonResult.fail("删除模型分组失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取模型分组", notes="wf:modelGroup:get")
	public CommonResult<WfModelGroup> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取模型分组成功",wfModelGroupService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询模型分组", notes="wf:modelGroup:list")
	public CommonResult<CommonPage<WfModelGroup>> list(@RequestBody @Validated WfModelGroupPageParam param) {
		return CommonResult.success("查询模型分组成功",wfModelGroupService.list(param));
	}

}
