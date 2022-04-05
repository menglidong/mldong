package com.mldong.modules.wf.controller;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.tool.StringTool;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.wf.dto.WfIdParam;
import com.mldong.modules.wf.dto.WfTaskPageParam;
import com.mldong.modules.wf.dto.WfTaskParam;
import com.mldong.modules.wf.enums.WfOrderStateEnum;
import com.mldong.modules.wf.service.WfTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wf/task")
@Api(tags="wf-流程任务",authorizations={
        @Authorization(value="wf|工作流",scopes={
                @AuthorizationScope(description="流程任务",scope="wf:task:index")
        })
})
public class WfTaskController {
    @Autowired
    private WfTaskService taskService;
    @PostMapping("todolist")
    @ApiOperation(value="我的待办列表", notes = "wf:task:todolist")
    public CommonResult<CommonPage<WorkItem>> todolist(@RequestBody WfTaskPageParam param) {
        return CommonResult.success(taskService.todolist(param));
    }
    @PostMapping("donelist")
    @ApiOperation(value="我的已办列表", notes = "wf:task:donelist")
    public CommonResult<CommonPage<WorkItem>> donelist(@RequestBody WfTaskPageParam param) {
        return CommonResult.success(taskService.donelist(param));
    }
    @PostMapping("get")
    @ApiOperation(value="任务详情", notes = "wf:task:get")
    public CommonResult<WorkItem> get(@RequestBody @Validated WfIdParam param) {
        return CommonResult.success(taskService.get(param.getId()));
    }
    @PostMapping("execute")
    @ApiOperation(value="执行任务", notes = "wf:task:execute")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<?> execute(@RequestBody @Validated WfTaskParam param) {
        taskService.execute(param);
        return CommonResult.success();
    }
    @PostMapping("listHisByOrderId")
    @ApiOperation(value="通过流程实例ID获取任务列表", notes = "wf:task:listHisByOrderId")
    public CommonResult<List<WorkItem>> listHisByOrderId(@RequestBody @Validated WfIdParam param) {
        return CommonResult.success(taskService.listHisByOrderId(param.getId()));
    }
}
