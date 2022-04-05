package com.mldong.modules.wf.service.impl;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.tool.AssertTool;
import com.mldong.common.tool.StringTool;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.wf.dto.WfOrderPageParam;
import com.mldong.modules.wf.dto.WfOrderParam;
import com.mldong.modules.wf.enums.WfOrderStateEnum;
import com.mldong.modules.wf.service.WfOrderService;
import com.mldong.modules.wf.vo.WfHighlihtDataVO;
import org.snaker.engine.Expression;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.model.DecisionModel;
import org.snaker.engine.model.EndModel;
import org.snaker.engine.model.NodeModel;
import org.snaker.engine.model.ProcessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WfOrderServiceImpl implements WfOrderService {
    @Autowired
    private SnakerEngine snakerEngine;
    @Autowired
    private Expression expression;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startAndExecute(WfOrderParam param) {
        Process process = snakerEngine.process().getProcessById(param.getProcessId());
        if(process == null) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        param.getArgs().put("instanceUrl", process.getInstanceUrl());
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
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startAndExecuteByName(WfOrderParam param) {
        Process process = snakerEngine.process().getProcessByName(param.getProcessName());
        if(process == null) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        param.getArgs().put("instanceUrl", process.getInstanceUrl());
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
    }

    @Override
    public CommonPage<HistoryOrder> list(WfOrderPageParam param) {
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
        historyOrders.forEach(historyOrder -> {
            handleOrderStatus(historyOrder);
        });
        CommonPage<HistoryOrder> commonPage = new CommonPage<>();
        commonPage.setPageNum(param.getPageNum());
        commonPage.setPageSize(param.getPageSize());
        commonPage.setRows(historyOrders);
        commonPage.setTotalPage(Long.valueOf(page.getTotalCount()).intValue());
        return commonPage;
    }

    /**
     * 处理流程实例状态-优先使用变量中的状态
     * @param historyOrder
     * @return
     */
    private HistoryOrder handleOrderStatus(HistoryOrder historyOrder) {
        if(historyOrder!=null && historyOrder.getVariableMap().get("orderStatus")!=null) {
            historyOrder.setOrderState(Integer.valueOf(historyOrder.getVariableMap().get("orderStatus").toString()));
        }
        return historyOrder;
    }
    @Override
    public HistoryOrder get(String id) {
        HistoryOrder historyOrder = snakerEngine.query().getHistOrder(id);
        handleOrderStatus(historyOrder);
        return historyOrder;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cascadeRemove(String id) {
        snakerEngine.order().cascadeRemove(id);
    }

    @Override
    public WfHighlihtDataVO highLightData(String id) {
        WfHighlihtDataVO vo = new WfHighlihtDataVO();
        HistoryOrder historyOrder = snakerEngine.query().getHistOrder(id);
        if(historyOrder == null) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
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
        return vo;
    }
    /**
     * 递归模型处理历史节点与历史边
     * @param nodeModel 下一个节点
     * @param historyOrder 历史实例
     * @param historyTasks 历史任务
     * @param taskName 当前任务节点名称（递归停止标识）
     * @param vo 结果
     */
    private void recursionModel(NodeModel nodeModel, HistoryOrder historyOrder, List<HistoryTask> historyTasks, String taskName, WfHighlihtDataVO vo) {
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
