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
    private SnakerEngine snakerEngine;
    @PostMapping("todolist")
    @ApiOperation(value="我的待办列表", notes = "wf:task:todolist")
    public CommonResult<CommonPage<WorkItem>> todolist(@RequestBody WfTaskPageParam param) {
        param.buildPage();
        Page<WorkItem> page = new Page<>();
        page.setPageNo(param.getPageNum());
        page.setPageSize(param.getPageSize());
        QueryFilter queryFilter = new QueryFilter();
        if(StringTool.isNotEmpty(param.getDisplayName())) {
            queryFilter.setDisplayName(param.getDisplayName());
        }
        queryFilter.setOperators(new String[]{RequestHolder.getUserId().toString()});
        List<WorkItem> workItems = snakerEngine.query().getWorkItems(page, queryFilter);
        workItems.forEach(workItem -> {
            workItem.setTaskState(1);
        });
        CommonPage<WorkItem> commonPage = new CommonPage<>();
        commonPage.setPageNum(param.getPageNum());
        commonPage.setPageSize(param.getPageSize());
        commonPage.setRows(workItems);
        commonPage.setTotalPage(Long.valueOf(page.getTotalCount()).intValue());
        return CommonResult.success(commonPage);
    }
    @PostMapping("donelist")
    @ApiOperation(value="我的已办列表", notes = "wf:task:donelist")
    public CommonResult<CommonPage<WorkItem>> donelist(@RequestBody WfTaskPageParam param) {
        param.buildPage();
        Page<WorkItem> page = new Page<>();
        page.setPageNo(param.getPageNum());
        page.setPageSize(param.getPageSize());
        QueryFilter queryFilter = new QueryFilter();
        if(StringTool.isNotEmpty(param.getDisplayName())) {
            queryFilter.setDisplayName(param.getDisplayName());
        }
        queryFilter.setOperators(new String[]{RequestHolder.getUserId().toString()});
        List<WorkItem> historyWorkItems = snakerEngine.query().getHistoryWorkItems(page, queryFilter);
        historyWorkItems.forEach(workItem -> {
            workItem.setTaskState(0);
        });
        CommonPage<WorkItem> commonPage = new CommonPage<>();
        commonPage.setPageNum(param.getPageNum());
        commonPage.setPageSize(param.getPageSize());
        commonPage.setRows(historyWorkItems);
        commonPage.setTotalPage(Long.valueOf(page.getTotalCount()).intValue());
        return CommonResult.success(commonPage);
    }
    @PostMapping("get")
    @ApiOperation(value="任务详情", notes = "wf:task:get")
    public CommonResult<WorkItem> get(@RequestBody @Validated WfIdParam param) {
        String sql = "select distinct o.process_Id, t.order_Id, t.id as id, t.id as task_Id, p.display_Name as process_Name, p.instance_Url, o.parent_Id, o.creator,  o.create_Time as order_Create_Time, o.expire_Time as order_Expire_Time, o.order_No, o.variable as order_Variable,  t.display_Name as task_Name, t.task_Name as task_Key, t.task_Type, t.perform_Type, t.operator, t.action_Url,  t.create_Time as task_Create_Time, t.finish_Time as task_End_Time, t.expire_Time as task_Expire_Time, t.variable as task_Variable  from wf_task t  left join wf_order o on t.order_id = o.id  left join wf_task_actor ta on ta.task_id=t.id  left join wf_process p on p.id = o.process_id  where t.id=?";
        WorkItem workItem = snakerEngine.query().nativeQueryObject(WorkItem.class, sql, param.getId());
        if(workItem==null) {
            sql = "select distinct o.process_Id, t.order_Id, t.id as id, t.id as task_Id, p.display_Name as process_Name, p.instance_Url, o.parent_Id, o.creator,  o.create_Time as order_Create_Time, o.expire_Time as order_Expire_Time, o.order_No, o.variable as order_Variable,  t.display_Name as task_Name, t.task_Name as task_Key, t.task_Type, t.perform_Type,t.operator, t.action_Url,  t.create_Time as task_Create_Time, t.finish_Time as task_End_Time, t.expire_Time as task_Expire_Time, t.variable as task_Variable  from wf_hist_task t  left join wf_hist_order o on t.order_id = o.id  left join wf_hist_task_actor ta on ta.task_id=t.id  left join wf_process p on p.id = o.process_id  where t.id=?";
            workItem = snakerEngine.query().nativeQueryObject(WorkItem.class, sql, param.getId());
        }
        if(workItem == null) {
            return CommonResult.error(GlobalErrEnum.GL99990003);
        }
        return CommonResult.success(workItem);
    }
    @PostMapping("execute")
    @ApiOperation(value="执行任务", notes = "wf:task:execute")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<?> execute(@RequestBody @Validated WfTaskParam param) {
        Task task = snakerEngine.query().getTask(param.getTaskId());
        if(task == null) {
            return CommonResult.error(GlobalErrEnum.GL99990003);
        }
        // 任务执行人用户名
        param.getArgs().put("operator.userName", RequestHolder.getUsername());
        // 任务执行人姓名
        param.getArgs().put("operator.realName", RequestHolder.getUserExt().get("realName"));
        String operator = RequestHolder.getUserId().toString();
        if(Integer.valueOf(1).equals(param.getArgs().get("approvalType"))
        || "1".equals(param.getArgs().get("approvalType"))){
            // 同意
            snakerEngine.executeTask(param.getTaskId(), operator, param.getArgs());
        } else {
            // 1.不同意
            // 1.1 手动完成任务
            snakerEngine.task().complete(param.getTaskId(), operator, param.getArgs());
            // 1.2 给流程实例追加额外参数
            Map<String,Object> addArgs = new HashMap<>();
            addArgs.putAll(param.getArgs());
            addArgs.put("orderStatus", WfOrderStateEnum.TERMINATE.getValue());
            snakerEngine.order().addVariable(task.getOrderId(), addArgs);
            // 1.3 中止流程
            snakerEngine.order().terminate(task.getOrderId(), operator);
        }
        return CommonResult.success();
    }
    @PostMapping("listHisByOrderId")
    @ApiOperation(value="通过流程实例ID获取任务列表", notes = "wf:task:listHisByOrderId")
    public CommonResult<List<WorkItem>> listHisByOrderId(@RequestBody @Validated WfIdParam param) {
        Page<WorkItem> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(1000);
        QueryFilter queryFilter = new QueryFilter();
        queryFilter.setOrder(QueryFilter.ASC);
        queryFilter.setOrderBy("t.finish_Time");
        queryFilter.setOrderId(param.getId());
        List<WorkItem> historyWorkItems = snakerEngine.query().getHistoryWorkItems(page, queryFilter);
        historyWorkItems.forEach(workItem -> {
            workItem.setTaskState(0);
        });
        return CommonResult.success(historyWorkItems);
    }
}
