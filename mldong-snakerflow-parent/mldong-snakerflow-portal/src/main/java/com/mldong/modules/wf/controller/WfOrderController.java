package com.mldong.modules.wf.controller;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.tool.StringTool;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.wf.dto.WfIdParam;
import com.mldong.modules.wf.dto.WfOrderPageParam;
import com.mldong.modules.wf.dto.WfOrderParam;
import com.mldong.modules.wf.enums.WfOrderStateEnum;
import com.mldong.modules.wf.vo.WfHighlihtDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.snaker.engine.Expression;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;
import org.snaker.engine.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
        @Autowired
        private Expression expression;
        @PostMapping("startAndExecute")
        @ApiOperation(value="启动并执行第一个任务节点", notes = "wf:order:startAndExecute")
        @Transactional(rollbackFor = Exception.class)
        public CommonResult<?> startAndExecute(@RequestBody @Validated({WfOrderParam.ProcessId.class}) WfOrderParam param){
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
        @PostMapping("startAndExecuteByName")
        @ApiOperation(value="启动并执行第一个任务节点(通过名称)", notes = "wf:order:startAndExecuteByName")
        @Transactional(rollbackFor = Exception.class)
        public CommonResult<?> startAndExecuteByName(@RequestBody @Validated({WfOrderParam.ProcessName.class}) WfOrderParam param){
                // 设置业务ID
                param.getArgs().put(SnakerEngine.ID, UUID.randomUUID().toString().replaceAll("-",""));
                // 创建流程实例用户名
                param.getArgs().put("operator.userName", RequestHolder.getUsername());
                // 创建流程实例姓名
                param.getArgs().put("operator.realName", RequestHolder.getUserExt().get("realName"));
                Order order = snakerEngine.startInstanceByName(param.getProcessName(), null, RequestHolder.getUserId().toString(), param.getArgs());
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
                param.buildPage();
                Page<HistoryOrder> page = new Page<>();
                page.setPageNo(param.getPageNum());
                page.setPageSize(param.getPageSize());
                QueryFilter queryFilter = new QueryFilter();
                if(param.getOrderState()!=null) {
                        queryFilter.setState(param.getOrderState());
                }
                if(StringTool.isNotEmpty(param.getDisplayName())) {
                        queryFilter.setDisplayName(param.getDisplayName());
                }
                if(StringTool.isNotEmpty(param.getName())) {
                        queryFilter.setNames(new String[]{param.getName()});
                }
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
        @PostMapping("cascadeRemove")
        @ApiOperation(value="级联删除", notes = "wf:order:cascadeRemove")
        public CommonResult<?> cascadeRemove(@RequestBody @Validated WfIdParam param) {
                snakerEngine.order().cascadeRemove(param.getId());
                return CommonResult.success();
        }
        @PostMapping("highLightData")
        @ApiOperation(value="获取流程实例高亮数据", notes = "wf:order:highLightData")
        public CommonResult<WfHighlihtDataVO> highLightData(@RequestBody WfIdParam param) {
                WfHighlihtDataVO vo = new WfHighlihtDataVO();
                HistoryOrder historyOrder = snakerEngine.query().getHistOrder(param.getId());
                if(historyOrder == null) {
                        return CommonResult.error(GlobalErrEnum.GL99990003);
                }
                ProcessModel processModel = snakerEngine.process().getProcessById(historyOrder.getProcessId()).getModel();
                QueryFilter queryFilter = new QueryFilter();
                queryFilter.setOrderId(historyOrder.getId());
                List<HistoryTask> historyTasks = snakerEngine.query().getHistoryTasks(queryFilter);
                // 进行中节点
                List<Task> tasks = snakerEngine.query().getActiveTasks(queryFilter);
                tasks.forEach(task -> {
                        if(!vo.getActiveNodeNames().contains(task.getTaskName())) {
                                vo.getActiveNodeNames().add(task.getTaskName());
                                recursionModel(processModel.getStart(), historyOrder, historyTasks,task.getTaskName(),  vo);
                        }
                });
                List<EndModel> endModels = processModel.getModels(EndModel.class);
                if(endModels!=null) {
                        endModels.forEach(endModel -> {
                                recursionModel(processModel.getStart(), historyOrder, historyTasks, endModel.getName(),  vo);
                        });
                }
                return CommonResult.success(vo);
        }

        /**
         * 递归模型处理历史节点与历史边
         * @param nodeModel 下一个节点
         * @param historyOrder 历史实例
         * @param historyTasks 历史任务
         * @param taskName 当前任务节点名称（递归停止标识）
         * @param vo 结果
         */
        private void recursionModel(NodeModel nodeModel,HistoryOrder historyOrder, List<HistoryTask> historyTasks,String taskName, WfHighlihtDataVO vo) {
                if(nodeModel.getName().equals(taskName)) {
                        if(nodeModel instanceof EndModel) {
                                vo.getHistoryNodeNames().add(nodeModel.getName());
                        }
                        return;
                }
                if(WfOrderStateEnum.TERMINATE.getValue()==historyOrder.getOrderState()
                && nodeModel.getName().equals(historyTasks.get(0).getTaskName())) {
                        vo.getHistoryNodeNames().add(nodeModel.getName());
                        return;
                }
                if(!vo.getHistoryNodeNames().contains(nodeModel.getName())) {
                        vo.getHistoryNodeNames().add(nodeModel.getName());
                        nodeModel.getOutputs().stream().filter(output->{
                                // 默认取决策节点前面第一个节点为任务节点-待优化
                                NodeModel defaultDecisionInputModel = null;
                                HistoryTask historyTask = null;
                                if(nodeModel instanceof DecisionModel) {
                                        defaultDecisionInputModel = nodeModel.getInputs().get(0).getSource();
                                        NodeModel finalDefaultDecisionInputModel = defaultDecisionInputModel;
                                        historyTask = historyTasks.stream().filter(hisTask -> {
                                                return finalDefaultDecisionInputModel.getName().equals(hisTask.getTaskName());
                                        }).findAny().orElse(null);
                                }
                                Map<String,Object> args = new HashMap<>();
                                args.putAll(historyOrder.getVariableMap());
                                if(historyTask!=null) {
                                        args.putAll(historyTask.getVariableMap());
                                }
                                if(StringTool.isNotEmpty(output.getExpr())
                                && nodeModel instanceof DecisionModel
                                && defaultDecisionInputModel!=null) {
                                        return this.expression.eval(Boolean.class, output.getExpr(), args);
                                }
                                if(nodeModel instanceof DecisionModel) {
                                        String expr = ((DecisionModel) nodeModel).getExpr();
                                        if(StringTool.isNotEmpty(expr)) {
                                                String nextNodeName = this.expression.eval(String.class, expr, historyTask.getVariableMap());
                                                return output.getTo().equals(nextNodeName);
                                        }
                                }
                                return true;
                        }).forEach(transitionModel -> {
                                if(!vo.getHistoryEdgeNames().contains(transitionModel.getName())) {
                                        vo.getHistoryEdgeNames().add(transitionModel.getName());
                                        recursionModel(transitionModel.getTarget(), historyOrder, historyTasks, taskName, vo);
                                }
                        });
                }
        }
}
