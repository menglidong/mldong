package com.mldong.modules.wf.controller;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.wf.dto.WfIdParam;
import com.mldong.modules.wf.dto.WfTaskPageParam;
import com.mldong.modules.wf.dto.WfTaskParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.WorkItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wf/task")
@Api(tags="wf-流程任务",authorizations={
        @Authorization(value="wf|工作流",scopes={
                @AuthorizationScope(description="流程任务",scope="wf:task:index")
        })
})
public class WfTaskController {
    @Autowired
    private SnakerEngine snakerEngine;
    @PostMapping("todolist")
    @ApiOperation(value="我的待办列表", notes = "wf:task:todolist")
    public CommonResult<CommonPage<WorkItem>> todolist(@RequestBody WfTaskPageParam param) {
        Page<WorkItem> page = new Page<>();
        page.setPageNo(param.getPageNum());
        page.setPageSize(param.getPageSize());
        QueryFilter queryFilter = new QueryFilter();
        queryFilter.setOperators(new String[]{RequestHolder.getUserId().toString()});
        List<WorkItem> historyOrders = snakerEngine.query().getWorkItems(page, queryFilter);
        CommonPage<WorkItem> commonPage = new CommonPage<>();
        commonPage.setPageNum(param.getPageNum());
        commonPage.setPageSize(param.getPageSize());
        commonPage.setRows(historyOrders);
        commonPage.setTotalPage(Long.valueOf(page.getTotalCount()).intValue());
        return CommonResult.success(commonPage);
    }
    @PostMapping("donelist")
    @ApiOperation(value="我的已办列表", notes = "wf:task:donelist")
    public CommonResult<CommonPage<WorkItem>> donelist(@RequestBody WfTaskPageParam param) {
        Page<WorkItem> page = new Page<>();
        page.setPageNo(param.getPageNum());
        page.setPageSize(param.getPageSize());
        QueryFilter queryFilter = new QueryFilter();
        queryFilter.setOperators(new String[]{RequestHolder.getUserId().toString()});
        List<WorkItem> historyOrders = snakerEngine.query().getHistoryWorkItems(page, queryFilter);
        CommonPage<WorkItem> commonPage = new CommonPage<>();
        commonPage.setPageNum(param.getPageNum());
        commonPage.setPageSize(param.getPageSize());
        commonPage.setRows(historyOrders);
        commonPage.setTotalPage(Long.valueOf(page.getTotalCount()).intValue());
        return CommonResult.success(commonPage);
    }
    @PostMapping("get")
    @ApiOperation(value="任务详情", notes = "wf:task:get")
    public CommonResult<WorkItem> get(@RequestBody @Validated WfIdParam param) {
        Page<WorkItem> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(1);
        QueryFilter queryFilter = new QueryFilter();
        queryFilter.setTaskId(param.getId());
        List<WorkItem> workItems = snakerEngine.query().getWorkItems(page, queryFilter);
        if(workItems.isEmpty()) {
            workItems = snakerEngine.query().getHistoryWorkItems(page, queryFilter);
        }
        if(workItems.isEmpty()) {
            return CommonResult.error(GlobalErrEnum.GL99990003);
        }
        return CommonResult.success(workItems.get(0));
    }
    @PostMapping("execute")
    @ApiOperation(value="执行任务", notes = "wf:task:execute")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<?> execute(@RequestBody @Validated WfTaskParam param) {
        snakerEngine.executeTask(param.getTaskId(),RequestHolder.getUserId().toString(), param.getArgs());
        return CommonResult.success();
    }
}
