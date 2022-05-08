package com.mldong.modules.wf.controller;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.IdParam;
import com.mldong.common.base.IdsParam;
import com.mldong.common.validator.Groups;
import com.mldong.modules.snakerflow.dto.WfModelDesignerDefineParam;
import com.mldong.modules.snakerflow.dto.WfModelDesignerPageParam;
import com.mldong.modules.snakerflow.dto.WfModelDesignerParam;
import com.mldong.modules.snakerflow.entity.WfModelDesigner;
import com.mldong.modules.snakerflow.service.WfModelDesignerService;
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
@RequestMapping("/wf/modelDesigner")
@Api(tags="wf-模型设计管理",authorizations={
    @Authorization(value="wf|工作流",scopes={
    	@AuthorizationScope(description="模型设计管理",scope="wf:modelDesigner:index")
    })
})
public class WfModelDesignerController {
	@Autowired
	private WfModelDesignerService wfModelDesignerService;

	@PostMapping("save")
	@ApiOperation(value="添加模型基本信息", notes="wf:modelDesigner:save")
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) WfModelDesignerParam param) {
		int count = wfModelDesignerService.save(param);
		if(count>0) {
			return CommonResult.success("添加模型基本信息成功", null);
		} else {
			return CommonResult.fail("添加模型基本信息失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改模型基本信息", notes="wf:modelDesigner:update")
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) WfModelDesignerParam param) {
		int count = wfModelDesignerService.update(param);
		if(count>0) {
			return CommonResult.success("修改模型基本信息成功", null);
		} else {
			return CommonResult.fail("修改模型基本信息失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除模型设计", notes="wf:modelDesigner:remove")
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = wfModelDesignerService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除模型设计成功", null);
		} else {
			return CommonResult.fail("删除模型设计失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取模型设计", notes="wf:modelDesigner:get")
	public CommonResult<WfModelDesigner> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取模型设计成功",wfModelDesignerService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询模型设计", notes="wf:modelDesigner:list")
	public CommonResult<CommonPage<WfModelDesigner>> list(@RequestBody @Validated WfModelDesignerPageParam param) {
		return CommonResult.success("查询模型设计成功",wfModelDesignerService.list(param));
	}
	@PostMapping("saveDefine")
	@ApiOperation(value="保存模型设计定义", notes="wf:modelDesigner:saveDefine")
	public CommonResult<?> saveDefine(@RequestBody @Validated WfModelDesignerDefineParam param) {
		int count = wfModelDesignerService.saveDefine(param);
		if(count>0) {
			return CommonResult.success("保存模型设计定义成功", null);
		} else {
			return CommonResult.fail("保存模型设计定义失败", null);
		}
	}
}
