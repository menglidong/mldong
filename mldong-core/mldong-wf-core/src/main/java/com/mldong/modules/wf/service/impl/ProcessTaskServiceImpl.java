package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mldong.base.CommonPage;
import com.mldong.base.LabelValueVO;
import com.mldong.exception.ServiceException;
import com.mldong.modules.sys.api.UserApi;
import com.mldong.modules.wf.dto.ProcessTaskPageParam;
import com.mldong.modules.wf.dto.ProcessTaskParam;
import com.mldong.modules.wf.engine.AssignmentHandler;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.event.ProcessEvent;
import com.mldong.modules.wf.engine.event.ProcessPublisher;
import com.mldong.modules.wf.engine.model.CustomModel;
import com.mldong.modules.wf.engine.model.NodeModel;
import com.mldong.modules.wf.engine.model.ProcessModel;
import com.mldong.modules.wf.engine.model.TaskModel;
import com.mldong.modules.wf.engine.util.FlowUtil;
import com.mldong.modules.wf.entity.Candidate;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.entity.ProcessTaskActor;
import com.mldong.modules.wf.enums.*;
import com.mldong.modules.wf.mapper.ProcessInstanceMapper;
import com.mldong.modules.wf.mapper.ProcessTaskActorMapper;
import com.mldong.modules.wf.mapper.ProcessTaskMapper;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.service.ProcessInstanceService;
import com.mldong.modules.wf.service.ProcessTaskService;
import com.mldong.modules.wf.vo.ProcessTaskVO;
import com.mldong.web.LoginUserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    private final ProcessInstanceMapper processInstanceMapper;
    private final UserApi userApi;
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
        ProcessTaskVO vo = baseMapper.findById(id);
        if(vo!=null) {

        }
        return vo;
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
    public List<ProcessTask> getDoneTaskList(Long processInstanceId, String[] taskNames) {
        LambdaQueryWrapper<ProcessTask> queryWrapper = Wrappers.lambdaQuery(ProcessTask.class)
                .eq(ProcessTask::getProcessInstanceId,processInstanceId)
                .ne(ProcessTask::getTaskState, ProcessTaskStateEnum.DOING.getCode());
        if(ObjectUtil.isNotEmpty(taskNames)) {
            queryWrapper.in(ProcessTask::getTaskName,taskNames);
        }
        List<ProcessTask> processTaskList = baseMapper.selectList(queryWrapper);
        return processTaskList;
    }

    @Override
    public void finishProcessTask(Long processTaskId,String operator,Dict args) {
        ProcessTask his = baseMapper.selectById(processTaskId);
        ProcessTask processTask = new ProcessTask();
        processTask.setId(processTaskId);
        processTask.setTaskState(ProcessTaskStateEnum.FINISHED.getCode());
        processTask.setUpdateTime(new Date());
        processTask.setUpdateUser(operator);
        processTask.setOperator(operator);
        processTask.setFinishTime(new Date());
        Dict newArgs = Dict.create();
        newArgs.putAll(FlowUtil.variableToDict(his.getVariable()));
        newArgs.putAll(args);
        if(FlowConst.AUTO_ID.equalsIgnoreCase(operator)) {
            FlowUtil.addUserInfoToArgs(LoginUserHolder.getUserId().toString(), newArgs);
        } else {
            FlowUtil.addUserInfoToArgs(operator, newArgs);
        }
        processTask.setVariable(JSONUtil.toJsonStr(newArgs));
        baseMapper.updateById(processTask);
        // 发布流程任务结束事件
        ProcessPublisher.notify(ProcessEvent.builder().eventType(ProcessEventTypeEnum.PROCESS_TASK_END).sourceId(processTaskId).build());
    }

    @Override
    public void abandonProcessTask(Long processTaskId, String operator, Dict args) {
        ProcessTask his = baseMapper.selectById(processTaskId);
        ProcessTask processTask = new ProcessTask();
        processTask.setId(processTaskId);
        processTask.setTaskState(ProcessTaskStateEnum.ABANDON.getCode());
        processTask.setUpdateTime(new Date());
        processTask.setUpdateUser(operator);
        processTask.setOperator(operator);
        processTask.setFinishTime(new Date());
        Dict newArgs = Dict.create();
        newArgs.putAll(FlowUtil.variableToDict(his.getVariable()));
        newArgs.putAll(args);
        if(FlowConst.AUTO_ID.equalsIgnoreCase(operator)) {
            FlowUtil.addUserInfoToArgs(LoginUserHolder.getUserId().toString(), newArgs);
        } else {
            FlowUtil.addUserInfoToArgs(operator, newArgs);
        }
        processTask.setVariable(JSONUtil.toJsonStr(newArgs));
        baseMapper.updateById(processTask);
        // 发布流程任务结束事件
        ProcessPublisher.notify(ProcessEvent.builder().eventType(ProcessEventTypeEnum.PROCESS_TASK_END).sourceId(processTaskId).build());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ProcessTask> createTask(TaskModel taskModel, Execution execution) {
        List<ProcessTask> processTaskList = new ArrayList<>();
        Date now = new Date();
        ProcessTask processTask = new ProcessTask();
        processTask.setPerformType(ProcessTaskPerformTypeEnum.NORMAL.getCode());
        processTask.setTaskName(taskModel.getName());
        processTask.setDisplayName(taskModel.getDisplayName());
        processTask.setTaskState(ProcessTaskStateEnum.DOING.getCode());
        processTask.setTaskType(taskModel.getTaskType().getCode());
        processTask.setProcessInstanceId(execution.getProcessInstanceId());
        execution.getArgs().put(FlowConst.IS_FIRST_TASK_NODE,FlowUtil.isFistTaskName(execution.getProcessModel(),taskModel.getName()));
        // 增加是否为第一个任务节点标识
        processTask.setVariable(JSONUtil.toJsonStr(execution.getArgs()));
        processTask.setCreateTime(now);
        processTask.setUpdateTime(now);
        processTask.setTaskParentId(Convert.toLong(execution.getProcessTaskId(),0L));
        String expireTime = taskModel.getExpireTime();
        if(StrUtil.isNotEmpty(expireTime)) {
            processTask.setExpireTime(FlowUtil.processTime(expireTime,execution.getArgs()));
        }
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
        List<String> dbActors = getTaskActors(processTaskId);
        actors.stream().filter(actor->{
            return !dbActors.contains(actor);
        }).forEach(actor->{
            ProcessTaskActor processTaskActor = new ProcessTaskActor();
            processTaskActor.setProcessTaskId(processTaskId);
            processTaskActor.setActorId(actor);
            processTaskActor.setCreateTime(new Date());
            System.out.println(StrUtil.format("给任务：{}，添加参与者：{}",processTaskId,actor));
            processTaskActorMapper.insert(processTaskActor);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCandidateActor(Long processTaskId, List<String> actors) {
        if(CollectionUtil.isEmpty(actors)) return;
        ProcessTask processTask = baseMapper.selectById(processTaskId);
        String prefix = FlowConst.COUNTERSIGN_VARIABLE_PREFIX +processTask.getTaskName()+"_";
        // 主要调整流程变量中的参与者
        ProcessInstance processInstance = processInstanceMapper.selectById(processTask.getProcessInstanceId());
        if(processInstance!=null) {
            Dict args = JSONUtil.toBean(processInstance.getVariable(),Dict.class);
            // 会签办理人列表
            List<String> operatorList = Convert.toList(String.class,args.get(prefix+FlowConst.COUNTERSIGN_OPERATOR_LIST));
            if(args!=null) {
                operatorList.addAll(actors);
                operatorList = operatorList.stream().distinct().collect(Collectors.toList());
                // 更新列表
                args.put(prefix+FlowConst.COUNTERSIGN_OPERATOR_LIST,actors);
                // 更新实例数
                args.put(prefix+FlowConst.NR_OF_INSTANCES, operatorList.size());
                ProcessInstance up = new ProcessInstance();
                up.setId(processInstance.getId());
                up.setVariable(JSONUtil.toJsonStr(args));
                processInstanceMapper.updateById(up);
            }
        }
        Dict taskArgs = JSONUtil.toBean(processTask.getVariable(),Dict.class);
        String countersignType = taskArgs.getStr(FlowConst.COUNTERSIGN_VARIABLE_PREFIX+FlowConst.COUNTERSIGN_TYPE);
        if(CountersignTypeEnum.PARALLEL.toString().equalsIgnoreCase(countersignType)) {
            // 并行会签，要直接创建会签任务
            actors.forEach(actor->{
                ProcessTask clone = BeanUtil.toBean(processTask,ProcessTask.class);
                clone.setId(null);
                clone.setCreateUser(null);
                clone.setCreateTime(null);
                clone.setUpdateTime(null);
                clone.setUpdateUser(null);
                baseMapper.insert(clone);
                addTaskActor(clone.getId(),CollectionUtil.newArrayList(actor));
            });

        }
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

    @Override
    public CommonPage<ProcessTaskVO> todoList(ProcessTaskPageParam param) {
        if(StrUtil.isEmpty(param.getOrderBy())) {
            // 按id即时间倒序
            param.setOrderBy("t.id desc");
        }
        IPage<ProcessTaskVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.task_state",ProcessTaskStateEnum.DOING);
        queryWrapper.eq("pta.actor_id",LoginUserHolder.getUserId());
        List<ProcessTaskVO> list = baseMapper.selectTodoList(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }

    @Override
    public CommonPage<ProcessTaskVO> doneList(ProcessTaskPageParam param) {
        if(StrUtil.isEmpty(param.getOrderBy())) {
            // 按id即时间倒序
            param.setOrderBy("t.id desc");
        }
        IPage<ProcessTaskVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.notIn("t.task_state",ProcessTaskStateEnum.DOING,ProcessTaskStateEnum.WITHDRAW);
        queryWrapper.eq("pta.actor_id",LoginUserHolder.getUserId());
        queryWrapper.ne("t.operator",FlowConst.AUTO_ID);
        List<ProcessTaskVO> list = baseMapper.selectDoneList(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }

    @Override
    public ProcessTask rejectTask(ProcessModel model, ProcessTask currentTask) {
        Long taskParentId = currentTask.getTaskParentId();
        if(ObjectUtil.isEmpty(taskParentId) || taskParentId.equals(Long.valueOf(0))) {
            ServiceException.throwBiz(99999999,"上一步任务ID为空，无法驳回至上一步处理");
        }
        NodeModel current = model.getNode(currentTask.getTaskName());
        ProcessTask history = baseMapper.selectById(taskParentId);
        NodeModel parent = model.getNode(history.getTaskName());
        if(!NodeModel.canRejected(current, parent)) {
            ServiceException.throwBiz(99999999,"无法驳回至上一步处理，请确认上一步骤并非fork、join、suprocess以及会签任务");
        }
        ProcessTask task = BeanUtil.toBean(history,ProcessTask.class);
        task.setId(null);
        task.setTaskState(ProcessTaskStateEnum.DOING.getCode());
        task.setCreateTime(null);
        task.setCreateUser(null);
        task.setUpdateTime(null);
        task.setUpdateUser(null);
        task.setFinishTime(null);
        String operator = history.getOperator();
        Dict hisVariable = FlowUtil.variableToDict(history.getVariable());
        if(hisVariable.get(FlowConst.IS_FIRST_TASK_NODE,false)){
            // 第一个节点的操作人从任务变量中获取
            operator = hisVariable.getStr(FlowConst.USER_USER_ID);
        }
        task.setVariable(JSONUtil.toJsonStr(hisVariable));
        task.setOperator(operator);
        String expireTime = ((TaskModel)current).getExpireTime();
        if(StrUtil.isNotEmpty(expireTime)) {
            task.setExpireTime(FlowUtil.processTime(expireTime,hisVariable));
        }
        saveProcessTask(task);
        addTaskActor(task.getId(),CollectionUtil.newArrayList(task.getOperator()));
        return task;
    }

    @Override
    public List<LabelValueVO> jumpAbleTaskNameList(Long processInstanceId) {
        List<String> taskNames = new ArrayList<>();
        List<LabelValueVO> res = new ArrayList<>();
        List<ProcessTask> processTaskList = getDoneTaskList(processInstanceId,new String[]{});
        processTaskList.stream().filter(processTask -> {
            // 不能跳转到会签节点
            return !ProcessTaskPerformTypeEnum.COUNTERSIGN.getCode().equals(processTask.getPerformType());
        }).forEach(processTask -> {
            String taskName = processTask.getTaskName();
            if(!taskNames.contains(taskName)) {
                taskNames.add(taskName);
                res.add(LabelValueVO.builder().label(processTask.getDisplayName()).value(taskName).build());
            }
        });
        return res;
    }

    @Override
    public CommonPage<Map<String,Object>> candidatePage(Dict query) {
        Long processTaskId = Convert.toLong(query.get(FlowConst.PROCESS_TASK_ID_KEY,Convert.toLong(query.get("id"))));
        ProcessTask processTask = null;
        ProcessInstance processInstance = null;
        ProcessModel processModel = null;
        if(processTaskId!=null) {
            processTask = baseMapper.selectById(processTaskId);
        }
        if(processTask!=null) {
            processInstance = processInstanceMapper.selectById(processTask.getProcessInstanceId());
        }
        if(processInstance!=null) {
            processModel = SpringUtil.getBean(ProcessDefineService.class).getProcessModel(processInstance.getProcessDefineId());
        }
        List<Candidate> candidateList = null;
        if(processModel!=null) {
            candidateList = processModel.getNextTaskModelCandidates(processTask.getTaskName());
        }
        if(CollectionUtil.isEmpty(candidateList)) {
            Page<Map<String,Object>> page = new Page<>();
            page.setCurrent(Convert.toLong(query.get("pageNum")));
            page.setSize(Convert.toLong(query.get("pageSize")));
            CommonPage<Map<String,Object>> userPage = userApi.page(page, query);
            return userPage;
        }
        CommonPage<Map<String,Object>> commonPage = new CommonPage();
        commonPage.setPageNum(1);
        commonPage.setPageSize(10);
        commonPage.setRows(candidateList.stream().map(item->{
            Map<String,Object> map = userApi.findById(Convert.toLong(item.getUserId()));
            return map;
        }).collect(Collectors.toList()));
        return commonPage;
    }

    /**
     * 根据Task模型的assignee、assignmentHandler属性以及运行时数据，确定参与者
     * @param model 任务模型
     * @param execution 流程执行对象
     * @return
     */
    private List<String> getTaskActors(TaskModel model, Execution execution) {
        Object nextNodeOperator = execution.getArgs().get(FlowConst.NEXT_NODE_OPERATOR);
        if(ObjectUtil.isNotEmpty(nextNodeOperator)) {
            // 指定下一节点处理人的优先级最高
            if(nextNodeOperator instanceof String) {
                return CollectionUtil.newArrayList(((String) nextNodeOperator).split(","));
            } else if(nextNodeOperator instanceof Collection) {
                return CollectionUtil.newArrayList((Collection)nextNodeOperator);
            }
        }
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
    @Override
    public List<ProcessTask> createCountersignTask(TaskModel taskModel, Execution execution) {
        List<ProcessTask> processTaskList = new ArrayList<>();
        List<String> taskActors = getTaskActors(taskModel,execution);
        List<String> createTaskActors = new ArrayList<>();
        if(CountersignTypeEnum.PARALLEL.equals(taskModel.getCountersignType())) {
            // 并行：一个参与者一个任务，同时创建
            createTaskActors.addAll(taskActors);
            // 追加会签类型参数
            execution.getArgs().put(FlowConst.COUNTERSIGN_VARIABLE_PREFIX+FlowConst.COUNTERSIGN_TYPE, CountersignTypeEnum.PARALLEL.toString());
        } else {
            // 串行：一个参与者一个任务，顺序创建，默认只创建第一个，其他等执行完一个后再创建
            String prefix = FlowConst.COUNTERSIGN_VARIABLE_PREFIX +taskModel.getName()+"_";
            // 获取循环计数器，默认值为-1
            int loopCounter = execution.getArgs().get(prefix+FlowConst.LOOP_COUNTER,-1);
            if(loopCounter == -1) {
                createTaskActors.add(taskActors.get(0));
            } else {
                createTaskActors.add(taskActors.get(loopCounter+1));
            }
            // 追加会签类型参数
            execution.getArgs().put(FlowConst.COUNTERSIGN_VARIABLE_PREFIX+FlowConst.COUNTERSIGN_TYPE, CountersignTypeEnum.SEQUENTIAL.toString());
        }
        createTaskActors.forEach(taskActor->{
            Date now = new Date();
            ProcessTask processTask = new ProcessTask();
            processTask.setPerformType(ProcessTaskPerformTypeEnum.COUNTERSIGN.getCode());
            processTask.setTaskName(taskModel.getName());
            processTask.setDisplayName(taskModel.getDisplayName());
            processTask.setTaskState(ProcessTaskStateEnum.DOING.getCode());
            processTask.setTaskType(taskModel.getTaskType().getCode());
            processTask.setProcessInstanceId(execution.getProcessInstanceId());
            execution.getArgs().put(FlowConst.IS_FIRST_TASK_NODE,FlowUtil.isFistTaskName(execution.getProcessModel(),taskModel.getName()));
            processTask.setVariable(JSONUtil.toJsonStr(execution.getArgs()));
            processTask.setCreateTime(now);
            processTask.setUpdateTime(now);
            processTask.setTaskParentId(Convert.toLong(execution.getProcessTaskId(),0L));
            String expireTime = taskModel.getExpireTime();
            if(StrUtil.isNotEmpty(expireTime)) {
                processTask.setExpireTime(FlowUtil.processTime(expireTime,execution.getArgs()));
            }
            baseMapper.insert(processTask);
            execution.setProcessTask(processTask);
            System.out.println("创建会签任务："+processTask.getTaskName()+","+processTask.getDisplayName());
            processTaskList.add(processTask);
            addTaskActor(processTask.getId(),CollectionUtil.newArrayList(taskActor));
        });
        ProcessInstanceService processInstanceService = SpringUtil.getBean(ProcessInstanceService.class);
        // 更新会签变量到流程实例参数中
        processInstanceService.updateCountersignVariable(taskModel,execution, taskActors);
        return processTaskList;
    }

    @Override
    public ProcessTask history(Execution execution, CustomModel model) {
        ProcessTask processTask = new ProcessTask();
        processTask.setProcessInstanceId(execution.getProcessInstanceId());
        Date now = new Date();
        processTask.setCreateTime(now);
        processTask.setFinishTime(now);
        processTask.setDisplayName(model.getDisplayName());
        processTask.setTaskName(model.getName());
        processTask.setTaskState(ProcessTaskStateEnum.FINISHED.getCode());
        processTask.setTaskType(ProcessTaskTypeEnum.RECORD.getCode());
        processTask.setTaskParentId(Convert.toLong(execution.getProcessTaskId(),0L));
        processTask.setVariable(JSONUtil.toJsonStr(execution.getArgs()));
        baseMapper.insert(processTask);
        return processTask;
    }
}
