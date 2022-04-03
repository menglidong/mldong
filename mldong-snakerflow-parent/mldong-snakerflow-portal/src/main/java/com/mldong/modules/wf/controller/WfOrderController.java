package com.mldong.modules.wf.controller;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.wf.dto.WfIdParam;
import com.mldong.modules.wf.dto.WfOrderPageParam;
import com.mldong.modules.wf.dto.WfOrderParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wf/order")
@Api(tags="wf-流程实例",authorizations={
        @Authorization(value="wf|工作流",scopes={
                @AuthorizationScope(description="流程实例",scope="wf:order:index")
        })
})
public class WfOrderController {
        @Autowired
        private SnakerEngine snakerEngine;
        @PostMapping("startAndExecute")
        @ApiOperation(value="启动并执行第一个节点", notes = "wf:order:startAndExecute")
        @Transactional(rollbackFor = Exception.class)
        public CommonResult<?> startAndExecute(@RequestBody @Validated WfOrderParam param){
                // 设置业务ID
                param.getArgs().put(SnakerEngine.ID, UUID.randomUUID().toString().replaceAll("-",""));
                // 创建流程实例用户名
                param.getArgs().put("operator.userName", RequestHolder.getUsername());
                // 创建流程实例姓名
                param.getArgs().put("operator.realName", RequestHolder.getUserExt().get("realName"));
                Order order = snakerEngine.startInstanceById(param.getProcessId(), RequestHolder.getUserId().toString(), param.getArgs());
                List<Task> tasks = snakerEngine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
                List<Task> newTasks = new ArrayList<Task>();
                if (tasks != null && tasks.size() > 0) {
                        Task task = tasks.get(0);
                        newTasks.addAll(snakerEngine.executeTask(task.getId(), SnakerEngine.AUTO, param.getArgs()));
                }
                return CommonResult.success();
        }
        @PostMapping("list")
        @ApiOperation(value="我发起的流程实例", notes = "wf:order:list")
        public CommonResult<CommonPage<HistoryOrder>> list(@RequestBody WfOrderPageParam param) {
                Page<HistoryOrder> page = new Page<>();
                page.setPageNo(param.getPageNum());
                page.setPageSize(param.getPageSize());
                QueryFilter queryFilter = new QueryFilter();
                queryFilter.setOperators(new String[]{RequestHolder.getUserId().toString()});
                List<HistoryOrder> historyOrders = snakerEngine.query().getHistoryOrders(page, queryFilter);
                CommonPage<HistoryOrder> commonPage = new CommonPage<>();
                commonPage.setPageNum(param.getPageNum());
                commonPage.setPageSize(param.getPageSize());
                commonPage.setRows(historyOrders);
                commonPage.setTotalPage(Long.valueOf(page.getTotalCount()).intValue());
                return CommonResult.success(commonPage);
        }
        @PostMapping("get")
        @ApiOperation(value="获取流程实例详情", notes = "wf:order:get")
        public CommonResult<HistoryOrder> list(@RequestBody WfIdParam param) {
                return CommonResult.success(snakerEngine.query().getHistOrder(param.getId()));
        }
}
