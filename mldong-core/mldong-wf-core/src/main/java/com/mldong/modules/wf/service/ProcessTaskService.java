package com.mldong.modules.wf.service;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mldong.base.CommonPage;
import com.mldong.base.LabelValueVO;
import com.mldong.modules.wf.dto.ProcessTaskPageParam;
import com.mldong.modules.wf.dto.ProcessTaskParam;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.model.CustomModel;
import com.mldong.modules.wf.engine.model.ProcessModel;
import com.mldong.modules.wf.engine.model.TaskModel;
import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.vo.ProcessTaskVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 流程任务 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
public interface ProcessTaskService extends IService<ProcessTask> {
    /**
    * 添加流程任务
    * @param param
    * @return
    */
    boolean save(ProcessTaskParam param);

    /**
    * 更新流程任务
    * @param param
    * @return
    */
    boolean update(ProcessTaskParam param);

    /**
    * 自定义分页查询流程任务
    * @param param
    * @return
    */
    CommonPage<ProcessTaskVO> page(ProcessTaskPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    ProcessTaskVO findById(Long id);
    /**
     * 保存流程任务
     * @param processTask
     */
    void saveProcessTask(ProcessTask processTask);

    /**
     * 更新流程任务
     * @param processTask
     */
    void updateProcessTask(ProcessTask processTask);

    /**
     * 根据流程实例ID获取正在进行的任务
     * @param processInstanceId 流程实例ID
     * @param taskNames 任务名称
     * @return
     */
    List<ProcessTask> getDoingTaskList(Long processInstanceId, String[] taskNames);
    /**
     * 根据流程实例ID获取已完成的任务
     * @param processInstanceId 流程实例ID
     * @param taskNames 任务名称
     * @return
     */
    List<ProcessTask> getDoneTaskList(Long processInstanceId, String[] taskNames);
    /**
     * 将流程任务修改为已完成
     * @param processTaskId
     * @param operator 任务处理人
     * @param args
     */
    void finishProcessTask(Long processTaskId, String operator, Dict args);

    /**
     * 废弃任务
     * @param processTaskId
     * @param operator
     * @param args
     */
    void abandonProcessTask(Long processTaskId, String operator, Dict args);

    /**
     * 根据任务模型和流程执行对象创建任务
     * @param taskModel
     * @param execution
     * @return
     */
    List<ProcessTask> createTask(TaskModel taskModel, Execution execution);

    /**
     * 通过id获取流程任务
     * @param id
     * @return
     */
    ProcessTask getById(Long id);
    /**
     * 向指定的任务id添加参与者
     * @param processTaskId 任务id
     * @param actors 参与者
     */
    void addTaskActor(Long processTaskId, List<String> actors);
    /**
     * 向指定的任务id添加参与者
     * @param processTaskId 流程任务id
     * @param actors 参与者
     */
    void addCandidateActor(Long processTaskId, List<String> actors);

    /**
     * 向指定任务移除参与者
     * @param processTaskId 任务id
     * @param actors 参与者
     */
    void removeTaskActor(Long processTaskId, List<String> actors);
    /**
     * 根据taskId、operator，判断操作人operator是否允许执行任务
     * @param task 任务对象
     * @param operator 操作人
     * @return boolean 是否允许操作
     */
    boolean isAllowed(ProcessTask task, String operator);

    /**
     * 根据任务ID获取参与者ID
     * @param processTaskId
     * @return
     */
    List<String> getTaskActors(Long processTaskId);
    /**
     * 查询待办列表
     * @param param
     * @return
     */
    CommonPage<ProcessTaskVO> todoList(ProcessTaskPageParam param);
    /**
     * 查询已办列表
     * @param param
     * @return
     */
    CommonPage<ProcessTaskVO> doneList(ProcessTaskPageParam param);
    /**
     * 根据当前任务对象驳回至上一步处理
     * @param model 流程定义模型，用以获取上一步模型对象
     * @param currentTask 当前任务对象
     * @return Task 任务对象
     */
    ProcessTask rejectTask(ProcessModel model, ProcessTask currentTask);

    /**
     * 获取可跳转的任务节点名称
     * @param processInstanceId
     * @return
     */
    List<LabelValueVO> jumpAbleTaskNameList(Long processInstanceId);

    /**
     * 分页查询获取候选人
     * @param query
     * @return
     */
    CommonPage<Map<String,Object>> candidatePage(Dict query);
    /**
     * 创建会签任务
     * @param taskModel
     * @param execution
     * @return
     */
    List<ProcessTask> createCountersignTask(TaskModel taskModel, Execution execution);
    /**
     * 根据执行对象、自定义节点模型创建历史任务记录
     * @param execution 执行对象
     * @param model 自定义节点模型
     * @return 历史任务
     */
    ProcessTask history(Execution execution, CustomModel model);
}
