package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessTaskPageParam;
import com.mldong.modules.wf.dto.ProcessTaskParam;
import com.mldong.modules.wf.engine.AssignmentHandler;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.enums.FlowConst;
import com.mldong.modules.wf.enums.ProcessTaskStateEnum;
import com.mldong.modules.wf.engine.model.TaskModel;
import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.entity.ProcessTaskActor;
import com.mldong.modules.wf.mapper.ProcessTaskActorMapper;
import com.mldong.modules.wf.mapper.ProcessTaskMapper;
import com.mldong.modules.wf.service.ProcessTaskService;
import com.mldong.modules.wf.vo.ProcessTaskVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
/**
 * <p>
 * 流程任务 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Service
@RequiredArgsConstructor
public class ProcessTaskServiceImpl extends ServiceImpl<ProcessTaskMapper, ProcessTask> implements ProcessTaskService {
    private final ProcessTaskActorMapper processTaskActorMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProcessTaskParam param) {
        param.setId(null);
        ProcessTask processTask = new ProcessTask();
        BeanUtil.copyProperties(param, processTask);
        return super.save(processTask);
    }

    @Override
    public boolean update(ProcessTaskParam param) {
        ProcessTask processTask = new ProcessTask();
        BeanUtil.copyProperties(param, processTask);
        return super.updateById(processTask);
    }

    @Override
    public CommonPage<ProcessTaskVO> page(ProcessTaskPageParam param) {
        IPage<ProcessTaskVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        List<ProcessTaskVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public ProcessTaskVO findById(Long id) {
        return baseMapper.findById(id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveProcessTask(ProcessTask processTask) {
        baseMapper.insert(processTask);
    }

    @Override
    public void updateProcessTask(ProcessTask processTask) {
        baseMapper.updateById(processTask);
    }

    @Override
    public List<ProcessTask> getDoingTaskList(Long processInstanceId, String[] taskNames) {
        LambdaQueryWrapper<ProcessTask> queryWrapper = Wrappers.lambdaQuery(ProcessTask.class)
                .eq(ProcessTask::getProcessInstanceId,processInstanceId)
                .eq(ProcessTask::getTaskState, ProcessTaskStateEnum.DOING.getCode());
        if(ObjectUtil.isNotEmpty(taskNames)) {
            queryWrapper.in(ProcessTask::getTaskName,taskNames);
        }
        List<ProcessTask> processTaskList = baseMapper.selectList(queryWrapper);
        return processTaskList;
    }

    @Override
    public void finishProcessTask(Long processTaskId,String operator) {
        ProcessTask processTask = new ProcessTask();
        processTask.setId(processTaskId);
        processTask.setTaskState(ProcessTaskStateEnum.FINISHED.getCode());
        processTask.setUpdateTime(new Date());
        processTask.setUpdateUser(operator);
        processTask.setOperator(operator);
        baseMapper.updateById(processTask);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ProcessTask> createTask(TaskModel taskModel, Execution execution) {
        List<ProcessTask> processTaskList = new ArrayList<>();
        Date now = new Date();
        ProcessTask processTask = new ProcessTask();
        processTask.setTaskName(taskModel.getName());
        processTask.setDisplayName(taskModel.getDisplayName());
        processTask.setTaskState(ProcessTaskStateEnum.DOING.getCode());
        processTask.setProcessInstanceId(execution.getProcessInstanceId());
        processTask.setVariable(JSONUtil.toJsonStr(execution.getArgs()));
        processTask.setCreateTime(now);
        processTask.setUpdateTime(now);
        processTask.setTaskParentId(Convert.toLong(execution.getProcessTaskId(),0L));
        baseMapper.insert(processTask);
        execution.setProcessTask(processTask);
        System.out.println("创建任务："+processTask.getTaskName()+","+processTask.getDisplayName());
        processTaskList.add(processTask);
        addTaskActor(processTask.getId(),getTaskActors(taskModel,execution));
        return processTaskList;
    }

    @Override
    public ProcessTask getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addTaskActor(Long processTaskId, List<String> actors) {
        if(CollectionUtil.isEmpty(actors)) return;
        actors.forEach(actor->{
            ProcessTaskActor processTaskActor = new ProcessTaskActor();
            processTaskActor.setProcessTaskId(processTaskId);
            processTaskActor.setActorId(actor);
            processTaskActor.setCreateTime(new Date());
            System.out.println(StrUtil.format("给任务：{}，添加参与者：{}",processTaskId,actor));
            processTaskActorMapper.insert(processTaskActor);
        });
    }

    @Override
    public void removeTaskActor(Long processTaskId, List<String> actors) {
        processTaskActorMapper.delete(
                Wrappers.lambdaQuery(ProcessTaskActor.class)
                        .eq(ProcessTaskActor::getProcessTaskId,processTaskId)
                        .in(ProcessTaskActor::getActorId,actors)
        );
    }

    @Override
    public boolean isAllowed(ProcessTask task, String operator) {
        // 执行者为超级管理员或自动执行用户
        if(FlowConst.ADMIN_ID.equalsIgnoreCase(operator) || FlowConst.AUTO_ID.equalsIgnoreCase(operator)) {
            return true;
        }
        // 任务操作者==执行者
        if(ObjectUtil.equals(task.getOperator(),operator)) {
            return true;
        }
        // 任务参考者==执行者
        if(getTaskActors(task.getId()).contains(operator)) {
            return true;
        }
        return false;
    }

    @Override
    public List<String> getTaskActors(Long processTaskId) {
        List<ProcessTaskActor> processTaskActorList = processTaskActorMapper.selectList(
                Wrappers.lambdaQuery(ProcessTaskActor.class)
                        .eq(ProcessTaskActor::getProcessTaskId,processTaskId)
        );
        return processTaskActorList.stream().map(item->{
            return item.getActorId();
        }).collect(Collectors.toList());
    }

    /**
     * 根据Task模型的assignee、assignmentHandler属性以及运行时数据，确定参与者
     * @param model 任务模型
     * @param execution 流程执行对象
     * @return
     */
    private List<String> getTaskActors(TaskModel model, Execution execution) {
        List<String> res = new ArrayList<>();
        Dict args = execution.getArgs();
        String assignee = model.getAssignee();
        // 参与者属性不为空
        if(StrUtil.isNotEmpty(assignee)) {
            // 多个使用英文逗号分割
            String [] assigneeArr = assignee.split(",");
            for (int i = 0; i < assigneeArr.length; i++) {
                // 如果args中存在assigneeArr[i]为key的数据==>参与者设置方式中的动态传递
                // 如果args中不存在assigneeArr[i]为key的数据==>参与者设置方式中的静态配置
                res.add(args.get(assigneeArr[i],assigneeArr[i]));
            }
        } else {
            String assignmentHandler = model.getAssignmentHandler();
            if(StrUtil.isNotEmpty(assignmentHandler)) {
                AssignmentHandler assignmentHandlerObj = ReflectUtil.newInstance(assignmentHandler);
                res.addAll(assignmentHandlerObj.assign(model,execution));
            }
        }
        return res;
    }
}
