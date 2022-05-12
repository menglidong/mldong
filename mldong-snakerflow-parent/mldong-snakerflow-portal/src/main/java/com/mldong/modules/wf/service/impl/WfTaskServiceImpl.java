package com.mldong.modules.wf.service.impl;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.tool.AssertTool;
import com.mldong.common.tool.StringTool;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.wf.dto.WfTaskPageParam;
import com.mldong.modules.wf.dto.WfTaskParam;
import com.mldong.modules.wf.enums.WfConstants;
import com.mldong.modules.wf.enums.WfOrderStateEnum;
import com.mldong.modules.wf.service.WfTaskService;
import com.mldong.modules.wf.vo.WfSelectBackNodeVO;
import com.mldong.modules.wf.vo.WfTaskModelVO;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.model.EndModel;
import org.snaker.engine.model.NodeModel;
import org.snaker.engine.model.ProcessModel;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WfTaskServiceImpl implements WfTaskService {
    @Autowired
    private SnakerEngine snakerEngine;
    @Override
    public CommonPage<WorkItem> todolist(WfTaskPageParam param) {
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
        commonPage.setRecordCount(Long.valueOf(page.getTotalCount()).intValue());
        commonPage.setTotalPage(Long.valueOf(page.getTotalPages()).intValue());
        return commonPage;
    }

    @Override
    public CommonPage<WorkItem> donelist(WfTaskPageParam param) {
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
        commonPage.setRecordCount(Long.valueOf(page.getTotalCount()).intValue());
        commonPage.setTotalPage(Long.valueOf(page.getTotalPages()).intValue());
        return commonPage;
    }

    @Override
    public WorkItem get(String id) {
        String sql = "select distinct o.process_Id, t.order_Id, t.id as id, t.id as task_Id, p.display_Name as process_Name, p.instance_Url, o.parent_Id, o.creator,  o.create_Time as order_Create_Time, o.expire_Time as order_Expire_Time, o.order_No, o.variable as order_Variable,  t.display_Name as task_Name, t.task_Name as task_Key, t.task_Type, t.perform_Type, t.operator, t.action_Url,  t.create_Time as task_Create_Time, t.finish_Time as task_End_Time, t.expire_Time as task_Expire_Time, t.variable as task_Variable  from wf_task t  left join wf_order o on t.order_id = o.id  left join wf_task_actor ta on ta.task_id=t.id  left join wf_process p on p.id = o.process_id  where t.id=?";
        WorkItem workItem = snakerEngine.query().nativeQueryObject(WorkItem.class, sql, id);
        if(workItem==null) {
            sql = "select distinct o.process_Id, t.order_Id, t.id as id, t.id as task_Id, p.display_Name as process_Name, p.instance_Url, o.parent_Id, o.creator,  o.create_Time as order_Create_Time, o.expire_Time as order_Expire_Time, o.order_No, o.variable as order_Variable,  t.display_Name as task_Name, t.task_Name as task_Key, t.task_Type, t.perform_Type,t.operator, t.action_Url,  t.create_Time as task_Create_Time, t.finish_Time as task_End_Time, t.expire_Time as task_Expire_Time, t.variable as task_Variable  from wf_hist_task t  left join wf_hist_order o on t.order_id = o.id  left join wf_hist_task_actor ta on ta.task_id=t.id  left join wf_process p on p.id = o.process_id  where t.id=?";
            workItem = snakerEngine.query().nativeQueryObject(WorkItem.class, sql, id);
        }
        if(workItem == null) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        return workItem;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(WfTaskParam param) {
        Task task = snakerEngine.query().getTask(param.getTaskId());
        if(task == null) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        Order order = snakerEngine.query().getOrder(task.getOrderId());
        if(order == null) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        // 任务执行人用户名
        param.getArgs().put(WfConstants.ORDER_USER_NAME_KEY, RequestHolder.getUsername());
        // 任务执行人姓名
        param.getArgs().put(WfConstants.ORDER_USER_REAL_NAME_KEY, RequestHolder.getUserExt().get("realName"));
        String operator = RequestHolder.getUserId().toString();
        if(Integer.valueOf(1).equals(param.getArgs().get(WfConstants.APPROVAL_TYPE))
                || "1".equals(param.getArgs().get(WfConstants.APPROVAL_TYPE))){
            // 同意
            snakerEngine.executeTask(param.getTaskId(), operator, param.getArgs());
        } else {
            // 1.不同意
            // 1.1 给流程实例追加额外参数
            Map<String,Object> addArgs = new HashMap<>();
            addArgs.putAll(param.getArgs());
            addArgs.put(WfConstants.ORDER_STATE_KEY, WfOrderStateEnum.DISAGREE.getValue());
            snakerEngine.order().addVariable(task.getOrderId(), addArgs);
            // 1.2 直接跳到结束节点
            ProcessModel processModel = snakerEngine.process().getProcessById(order.getProcessId()).getModel();
            snakerEngine.executeAndJumpTask(param.getTaskId(), operator, param.getArgs(), processModel.getModels(EndModel.class).get(0).getName());
        }
    }

    @Override
    public List<WorkItem> listHisByOrderId(String orderId) {
        Page<WorkItem> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(1000);
        QueryFilter queryFilter = new QueryFilter();
        queryFilter.setOrder(QueryFilter.ASC);
        queryFilter.setOrderBy("t.finish_Time");
        queryFilter.setOrderId(orderId);
        List<WorkItem> historyWorkItems = snakerEngine.query().getHistoryWorkItems(page, queryFilter);
        historyWorkItems.forEach(workItem -> {
            workItem.setTaskState(0);
        });
        return historyWorkItems;
    }

    @Override
    public List<WfSelectBackNodeVO> listSelectBackNodeByTaskId(String taskId) {
        List<WfSelectBackNodeVO> res = new ArrayList<>();
        Task task = snakerEngine.query().getTask(taskId);
        if(task == null) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        Page<WorkItem> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(1000);
        QueryFilter queryFilter = new QueryFilter();
        queryFilter.setOrder(QueryFilter.ASC);
        queryFilter.setOrderBy("t.finish_Time");
        queryFilter.setOrderId(task.getOrderId());
        List<WorkItem> historyWorkItems = snakerEngine.query().getHistoryWorkItems(page, queryFilter);
        historyWorkItems.forEach(item->{
            long count = res.stream().filter(itemm->{
                return item.getTaskKey().equals(itemm.getTaskKey());
            }).count();
            if(count==0) {
                WfSelectBackNodeVO vo = new WfSelectBackNodeVO();
                vo.setTaskName(item.getTaskName());
                vo.setTaskKey(item.getTaskKey());
                res.add(vo);
            }
        });
        List<WfSelectBackNodeVO> ress = new ArrayList<>();
        for(int i=0,len=res.size();i<len;i++) {
            WfSelectBackNodeVO vo = res.get(i);
            if(vo.getTaskKey().equals(task.getTaskName())) {
                break;
            }
            ress.add(vo);
        }
        return ress;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void backOff(WfTaskParam param) {
        Task task = snakerEngine.query().getTask(param.getTaskId());
        if(task == null) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        Order order = snakerEngine.query().getOrder(task.getOrderId());
        if(task == null) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        String sourceNodeName = (String) param.getArgs().get(WfConstants.TARGET_NODE_NAME);
        QueryFilter queryFilter = new QueryFilter();
        queryFilter.setOrderId(task.getOrderId());
        List<HistoryTask> historyTasks = snakerEngine.query().getHistoryTasks(queryFilter);

        if(StringTool.isEmpty(sourceNodeName)) {
            // 节点不存在，则使用上一个任务节点
            if(historyTasks.isEmpty()) {
                // 不存在历史任务，则直接驳回
                rejectOrder(order.getProcessId(), order.getId(), task.getId());
            } else if(WfConstants.FIRST_TASK_NAME.equalsIgnoreCase(historyTasks.get(0).getTaskName())) {
                // 如果第一个节点为申请任务节点，也直接驳回流程
                rejectOrder(order.getProcessId(), order.getId(), task.getId());
            }
            return;
        }
        HistoryTask historyTask = historyTasks.stream().filter(item->{
            return item.getTaskName().equalsIgnoreCase(sourceNodeName);
        }).findAny().orElse(null);
        if(historyTask == null) {
            // 找不到对应节点-直接驳回
            rejectOrder(order.getProcessId(), order.getId(), task.getId());
            return;
        }
        if(WfConstants.FIRST_TASK_NAME.equalsIgnoreCase(sourceNodeName)) {
            // 如果第一个节点为申请任务节点，也直接驳回流程
            rejectOrder(order.getProcessId(), order.getId(), task.getId());
            return;
        }
        backOffOrder(task.getId(), sourceNodeName, param.getArgs());
    }

    @Override
    public List<WfTaskModelVO> getNextNodes(String taskId) {
        List<WfTaskModelVO> list = new ArrayList<>();
        try {
            TaskModel taskModel = snakerEngine.task().getTaskModel(taskId);
            recursionGetNextTaskNode(taskModel, list);
        } catch (IllegalArgumentException e) {
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        return list;
    }

    /**
     * 递归找到下一任务节点
     * @param nodeModel
     * @param list
     */
    private void recursionGetNextTaskNode(NodeModel nodeModel, List<WfTaskModelVO> list) {
        if(nodeModel instanceof EndModel) {
            return;
        }
        nodeModel.getOutputs().forEach(transitionModel -> {
            // 下一个节点为任务节点
            if(transitionModel.getTarget() instanceof TaskModel) {
                WfTaskModelVO vo = new WfTaskModelVO();
                BeanUtils.copyProperties(transitionModel.getTarget(), vo);
                list.add(vo);
            } else {
                // 下一个节点为非任务节点
                recursionGetNextTaskNode(transitionModel.getTarget(), list);
            }
        });

    }
    /**
     * 驳回流程
     * @param processId
     * @param orderId
     * @param taskId
     */
    private void rejectOrder(String processId, String orderId, String taskId) {
        // 1.驳回流程
        // 1.1 给流程实例追加额外参数
        Map<String,Object> addArgs = new HashMap<>();
        addArgs.put(WfConstants.ORDER_STATE_KEY, WfOrderStateEnum.REJECT.getValue());
        addArgs.put(WfConstants.REMARK, "【"+ RequestHolder.getRealName()+"】驳回流程");
        snakerEngine.order().addVariable(orderId, addArgs);
        // 1.2 直接跳到结束节点
        ProcessModel processModel = snakerEngine.process().getProcessById(processId).getModel();
        snakerEngine.executeAndJumpTask(taskId, SnakerEngine.ADMIN, addArgs, processModel.getModels(EndModel.class).get(0).getName());
    }

    /**
     * 回退到指定节点
     * @param taskId
     * @param nodeName
     */
    private void backOffOrder(String taskId, String nodeName, Map<String,Object> args) {
        // 1.回退流程
        // 1.1 跳到指定节点
        snakerEngine.executeAndJumpTask(taskId, SnakerEngine.ADMIN, args, nodeName);
    }
}