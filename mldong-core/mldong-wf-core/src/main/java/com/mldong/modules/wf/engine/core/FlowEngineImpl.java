package com.mldong.modules.wf.engine.core;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.mldong.modules.wf.engine.FlowEngine;
import com.mldong.modules.wf.engine.cfg.Configuration;
import com.mldong.modules.wf.enums.err.WfErrEnum;
import com.mldong.modules.wf.enums.ProcessTaskStateEnum;
import com.mldong.modules.wf.engine.ex.JFlowException;
import com.mldong.modules.wf.engine.model.NodeModel;
import com.mldong.modules.wf.engine.model.ProcessModel;
import com.mldong.modules.wf.engine.parser.ModelParser;
import com.mldong.modules.wf.entity.ProcessDefine;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.service.ProcessInstanceService;
import com.mldong.modules.wf.service.ProcessTaskService;

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
    public ProcessInstance startProcessInstanceById(Long id, String operator, Dict args) {
        // 1. 根据流程定义ID查询流程定义文件
        ProcessDefine processDefine = processDefineService.getById(id);
        if(processDefine==null) {
            throw new JFlowException(WfErrEnum.NOT_FOUND_PROCESS_DEFINE);
        }
        Date now = new Date();
        // 2. 将流程定义文件转成流程模型
        ProcessModel processModel = processDefineService.processDefineToModel(processDefine);
        // 3. 根据流程定义对象创建流程实例
        ProcessInstance processInstance = processInstanceService.createProcessInstance(processDefine,operator, args);
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
    public List<ProcessTask> executeProcessTask(Long processTaskId, String operator, Dict args) {
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
        processTaskService.finishProcessTask(processTaskId,operator);
        processTask.setTaskState(ProcessTaskStateEnum.FINISHED.getCode());
        // 6. 根据流程定义、实例、任务构建执行参数对象
        Execution execution = new Execution();
        execution.setProcessModel(processModel);
        execution.setProcessInstance(processInstance);
        execution.setProcessInstanceId(processInstance.getId());
        execution.setProcessTask(processTask);
        execution.setProcessTaskId(processTaskId);
        execution.setEngine(this);
        Dict processInstanceVariable = JSONUtil.toBean(processInstance.getVariable(),Dict.class);
        Dict newArgs = Dict.create();
        newArgs.putAll(processInstanceVariable);
        newArgs.putAll(args);
        execution.setArgs(newArgs);
        // 7. 根据流程任务名称获取对应的任务节点模型
        NodeModel nodeModel = processModel.getNode(processTask.getTaskName());
        // 8. 调用节点模型执行方法
        nodeModel.execute(execution);
        return execution.getProcessTaskList();
    }



}
