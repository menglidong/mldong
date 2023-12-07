package com.mldong.modules.wf.engine.core;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.mldong.exception.ServiceException;
import com.mldong.modules.wf.engine.FlowEngine;
import com.mldong.modules.wf.engine.cfg.Configuration;
import com.mldong.modules.wf.engine.ex.JFlowException;
import com.mldong.modules.wf.engine.model.*;
import com.mldong.modules.wf.engine.parser.ModelParser;
import com.mldong.modules.wf.engine.util.FlowUtil;
import com.mldong.modules.wf.entity.ProcessDefine;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.enums.FlowConst;
import com.mldong.modules.wf.enums.ProcessInstanceStateEnum;
import com.mldong.modules.wf.enums.ProcessTaskStateEnum;
import com.mldong.modules.wf.enums.err.WfErrEnum;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.service.ProcessInstanceService;
import com.mldong.modules.wf.service.ProcessTaskService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * 工作流引擎实现
 * @author mldong
 * @date 2023/5/29
 */
public class FlowEngineImpl implements FlowEngine {
    protected Configuration configuration;
    private ProcessDefineService processDefineService;
    private ProcessInstanceService processInstanceService;
    private ProcessTaskService processTaskService;
    @Override
    public FlowEngine configure(Configuration config) {
        this.configuration = config;
        processDefineService = ServiceContext.find(ProcessDefineService.class);
        processInstanceService = ServiceContext.find(ProcessInstanceService.class);
        processTaskService = ServiceContext.find(ProcessTaskService.class);
        return this;
    }

    @Override
    public ProcessDefineService processDefineService() {
        return processDefineService;
    }

    @Override
    public ProcessInstanceService processInstanceService() {
        return processInstanceService;
    }

    @Override
    public ProcessTaskService processTaskService() {
        return processTaskService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessInstance startProcessInstanceById(Long id, String operator, Dict args) {
        return startProcessInstanceById(id, operator, args, null, null);
    }

    @Override
    public ProcessInstance startProcessInstanceById(Long id, String operator, Dict args, Long parentId, String parentNodeName) {
        // 1. 根据流程定义ID查询流程定义文件
        ProcessDefine processDefine = processDefineService.getById(id);
        if(processDefine==null) {
            throw new JFlowException(WfErrEnum.NOT_FOUND_PROCESS_DEFINE);
        }
        // 2. 将流程定义文件转成流程模型
        ProcessModel processModel = processDefineService.processDefineToModel(processDefine);
        // 3. 根据流程定义对象创建流程实例
        ProcessInstance processInstance = processInstanceService.createProcessInstance(processDefine,operator, args, parentId, parentNodeName);
        // 4. 构建执行参数对象
        Execution execution = new Execution();
        execution.setProcessModel(processModel);
        execution.setProcessInstance(processInstance);
        execution.setProcessInstanceId(processInstance.getId());
        execution.setEngine(this);
        execution.setArgs(args);
        // 5. 拿到开始节点模型，调用其execute方法
        processModel.getStart().execute(execution);
        return processInstance;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProcessTask> executeProcessTask(Long processTaskId, String operator, Dict args) {
        Execution execution = execute(processTaskId,operator,args);
        if(execution == null) return Collections.emptyList();
        ProcessModel processModel = execution.getProcessModel();
        // 7. 根据流程任务名称获取对应的任务节点模型
        NodeModel nodeModel = processModel.getNode(execution.getProcessTask().getTaskName());
        // 8. 调用节点模型执行方法
        nodeModel.execute(execution);
        return execution.getProcessTaskList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProcessTask> executeAndJumpTask(Long processTaskId, String operator, Dict args, String nodeName) {
        Execution execution = execute(processTaskId, operator, args);
        if(execution == null) return Collections.emptyList();
        ProcessModel model = execution.getProcessModel();
        if(StrUtil.isEmpty(nodeName)) {
            ProcessTask newTask = processTaskService.rejectTask(model, execution.getProcessTask());
            execution.addTask(newTask);
        } else {
            NodeModel nodeModel = model.getNode(nodeName);
            if(nodeModel == null) {
                ServiceException.throwBiz(99999999, "根据节点名称[" + nodeName + "]无法找到节点模型");
            }
            // 判断是否为第一个任务节点
            if(nodeModel instanceof TaskModel) {
                TaskModel taskModel = (TaskModel) nodeModel;
                if(FlowUtil.isFistTaskName(model,taskModel.getName())) {
                    // 第一个任务节点为申请节点，处理人等于流程发起人
                    taskModel.setAssignee(execution.getProcessInstance().getOperator());
                }
            }
            //动态创建转移对象，由转移对象执行execution实例
            TransitionModel tm = new TransitionModel();
            tm.setTarget(nodeModel);
            tm.setEnabled(true);
            tm.execute(execution);
        }
        return execution.getProcessTaskList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProcessTask> executeAndJumpToEnd(Long processTaskId, String operator, Dict args) {
        Execution execution = execute(processTaskId, operator, args);
        if(execution == null) return Collections.emptyList();
        ProcessModel model = execution.getProcessModel();
        List<EndModel> endModelList = model.getModels(EndModel.class);
        endModelList.forEach(endModel -> {
            TransitionModel tm = new TransitionModel();
            tm.setTarget(endModel);
            tm.setEnabled(true);
            tm.execute(execution);
        });
        // 将流程状态修改为已拒绝
        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setId(execution.getProcessInstanceId());
        processInstance.setState(ProcessInstanceStateEnum.REJECT.getCode());
        processInstanceService.updateById(processInstance);
        return execution.getProcessTaskList();
    }

    @Override
    public List<ProcessTask> executeAndJumpToFirstTaskNode(Long processTaskId, String operator, Dict args) {
        Execution execution = execute(processTaskId, operator, args);
        if(execution == null) return Collections.emptyList();
        ProcessModel model = execution.getProcessModel();
        StartModel startModel = model.getStart();
        startModel.getOutputs().forEach(transitionModel -> {
            transitionModel.setEnabled(true);
            // 调整参与者为流程发起人
            if(transitionModel.getTarget() instanceof TaskModel) {
                TaskModel taskModel = (TaskModel) transitionModel.getTarget();
                taskModel.setAssignee(execution.getProcessInstance().getOperator());
            }
            transitionModel.execute(execution);
        });
        return execution.getProcessTaskList();
    }

    /**
     * 生成执行对象
     * @param processTaskId
     * @param operator
     * @param args
     * @return
     */
    private Execution execute(Long processTaskId, String operator, Dict args) {
        // 1.1 根据id查询正在进行中的流程任务
        ProcessTask processTask = processTaskService.getById(processTaskId);
        if(processTask == null || !ProcessTaskStateEnum.DOING.getCode().equals(processTask.getTaskState())) {
            throw new JFlowException(WfErrEnum.NOT_FOUND_DOING_PROCESS_TASK);
        }
        // 1.2 判断是否可以执行任务
        if(!processTaskService.isAllowed(processTask,operator)) {
            // 当前参与者不能执行该流程任务
            throw new JFlowException(WfErrEnum.NOT_ALLOWED_EXECUTE);
        }
        // 2. 根据流程任务查询流程实例
        ProcessInstance processInstance = processInstanceService.getById(processTask.getProcessInstanceId());
        // 3. 根据流程实例查询流程定义
        ProcessDefine processDefine = processDefineService.getById(processInstance.getProcessDefineId());
        // 4. 将流程定义文件转成流程模型
        ProcessModel processModel = ModelParser.parse(processDefine.getContent());
        // 5. 将流程任务状态修改为已完成
        processTaskService.finishProcessTask(processTaskId,operator,args);
        processTask.setTaskState(ProcessTaskStateEnum.FINISHED.getCode());
        // 6. 根据流程定义、实例、任务构建执行参数对象
        Execution execution = new Execution();
        execution.setProcessModel(processModel);
        execution.setProcessInstance(processInstance);
        execution.setProcessInstanceId(processInstance.getId());
        execution.setProcessTask(processTask);
        execution.setProcessTaskId(processTaskId);
        execution.setOperator(operator);
        execution.setEngine(this);
        Dict processInstanceVariable = JSONUtil.toBean(processInstance.getVariable(),Dict.class);
        Dict newArgs = Dict.create();
        newArgs.putAll(processInstanceVariable);
        newArgs.putAll(args);
        execution.setArgs(newArgs);
        // 如果提交参数中存在f_前辍参数，则更新到流程实例变量中
        Dict addArgs = Dict.create();
        args.forEach((key,value)->{
            if(key.startsWith(FlowConst.FORM_DATA_PREFIX)) {
                addArgs.put(key,value);
            }
        });
        if(ObjectUtil.isNotEmpty(addArgs)) {
            processInstanceService.addVariable(processInstance.getId(), addArgs);
        }
        return execution;
    }


}
