package com.mldong.modules.wf.engine;

import cn.hutool.core.lang.Dict;
import com.mldong.modules.wf.engine.cfg.Configuration;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.service.ProcessInstanceService;
import com.mldong.modules.wf.service.ProcessTaskService;

import java.util.List;

/**
 *
 * 流程引擎接口
 * @author mldong
 * @date 2023/5/29
 */
public interface FlowEngine {
    /**
     * 根据Configuration对象配置实现类
     * @param config 全局配置对象
     * @return FlowEngine 流程引擎
     */
    FlowEngine configure(Configuration config);

    /**
     * 获取流程定义服务
     * @return ProcessDefineService
     */
    ProcessDefineService processDefineService();

    /**
     * 获取流程实例服务
     * @return ProcessInstanceService
     */
    ProcessInstanceService processInstanceService();

    /**
     * 获取流程任务服务
     * @return ProcessTaskService
     */
    ProcessTaskService processTaskService();
    /**
     * 根据流程定义ID、操作人ID、启动流程参数启动流程实例
     * @param id 流程定义ID
     * @param operator 操作人ID
     * @param args 启动流程参数
     * @return ProcessInstance 流程实例
     */
    ProcessInstance startProcessInstanceById(Long id, String operator, Dict args);
    /**
     * 根据流程定义ID、操作人ID、启动流程参数启动流程实例
     * @param id 流程定义ID
     * @param operator 操作人ID
     * @param args 启动流程参数
     * @param parentId
     * @param parentNodeName
     * @return ProcessInstance 流程实例
     */
    ProcessInstance startProcessInstanceById(Long id, String operator, Dict args, Long parentId,String parentNodeName);
    /**
     * 执行流程任务
     * @param processTaskId
     * @param operator
     * @param args
     * @return
     */
    List<ProcessTask> executeProcessTask(Long processTaskId, String operator, Dict args);

    /**
     * 执行流程任务并跳转
     * @param processTaskId
     * @param operator
     * @param args
     * @param nodeName
     * @return
     */
    List<ProcessTask> executeAndJumpTask(Long processTaskId, String operator, Dict args, String nodeName);

    /**
     * 执行流程任务并跳转到结束节点
     * @param processTaskId
     * @param operator
     * @param args
     * @return
     */
    List<ProcessTask> executeAndJumpToEnd(Long processTaskId, String operator, Dict args);
    /**
     * 执行流程任务并跳转到第一个任务节点
     * @param processTaskId
     * @param operator
     * @param args
     * @return
     */
    List<ProcessTask> executeAndJumpToFirstTaskNode(Long processTaskId, String operator, Dict args);

}
