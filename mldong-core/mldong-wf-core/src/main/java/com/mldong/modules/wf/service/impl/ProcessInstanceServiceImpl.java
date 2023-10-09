package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.expression.ExpressionUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessInstancePageParam;
import com.mldong.modules.wf.dto.ProcessInstanceParam;
import com.mldong.modules.wf.engine.FlowEngine;
import com.mldong.modules.wf.engine.model.DecisionModel;
import com.mldong.modules.wf.engine.model.EndModel;
import com.mldong.modules.wf.engine.model.NodeModel;
import com.mldong.modules.wf.engine.model.ProcessModel;
import com.mldong.modules.wf.engine.util.FlowUtil;
import com.mldong.modules.wf.entity.ProcessDefine;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.enums.FlowConst;
import com.mldong.modules.wf.enums.ProcessInstanceStateEnum;
import com.mldong.modules.wf.enums.ProcessSubmitTypeEnum;
import com.mldong.modules.wf.enums.ProcessTaskStateEnum;
import com.mldong.modules.wf.mapper.ProcessInstanceMapper;
import com.mldong.modules.wf.mapper.ProcessTaskMapper;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.service.ProcessInstanceService;
import com.mldong.modules.wf.vo.HighLightVO;
import com.mldong.modules.wf.vo.ProcessInstanceVO;
import com.mldong.modules.wf.vo.ProcessTaskVO;
import com.mldong.web.LoginUserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 流程实例 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Service
@RequiredArgsConstructor
public class ProcessInstanceServiceImpl extends ServiceImpl<ProcessInstanceMapper, ProcessInstance> implements ProcessInstanceService {
    private final ProcessTaskMapper processTaskMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProcessInstanceParam param) {
        param.setId(null);
        ProcessInstance processInstance = new ProcessInstance();
        BeanUtil.copyProperties(param, processInstance);
        return super.save(processInstance);
    }

    @Override
    public boolean update(ProcessInstanceParam param) {
        ProcessInstance processInstance = new ProcessInstance();
        BeanUtil.copyProperties(param, processInstance);
        return super.updateById(processInstance);
    }

    @Override
    public CommonPage<ProcessInstanceVO> page(ProcessInstancePageParam param) {
        if(StrUtil.isEmpty(param.getOrderBy())) {
            // 按id即时间倒序
            param.setOrderBy("t.id desc");
        }
        IPage<ProcessInstanceVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        // 当前用户发起的流程
        queryWrapper.eq("t.operator",LoginUserHolder.getUserId());
        List<ProcessInstanceVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public ProcessInstanceVO findById(Long id) {
        ProcessInstanceVO vo = baseMapper.findById(id);
        if(vo!=null) {
            vo.setJsonObject(SpringUtil.getBean(ProcessDefineService.class).getDefineJsonObject(vo.getProcessDefineId()));
        }
        return vo;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void finishProcessInstance(Long processInstanceId) {
        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setId(processInstanceId);
        processInstance.setState(ProcessInstanceStateEnum.FINISHED.getCode());
        baseMapper.updateById(processInstance);
    }

    @Override
    public ProcessInstance createProcessInstance(ProcessDefine processDefine, String operator, Dict args) {
        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setProcessDefineId(processDefine.getId());
        processInstance.setOperator(operator);
        processInstance.setState(ProcessInstanceStateEnum.DOING.getCode());
        // 业务流水号从流程变量中获取
        processInstance.setBusinessNo(args.getStr(FlowConst.BUSINESS_NO));
        // 追加用户信息到参数
        FlowUtil.addUserInfoToArgs(operator,args);
        // 追加自动构造标题
        FlowUtil.addAutoGenTitle(processDefine.getDisplayName(),args);
        processInstance.setVariable(JSONUtil.toJsonStr(args));
        saveProcessInstance(processInstance);
        return processInstance;
    }

    @Override
    public void addVariable(Long processDefineId, Dict args) {
        ProcessInstance processInstance = baseMapper.selectById(processDefineId);
        Dict newDict = Dict.create();
        newDict.putAll(JSONUtil.toBean(processInstance.getVariable(),Dict.class));
        newDict.putAll(args);
        ProcessInstance up = new ProcessInstance();
        up.setId(processDefineId);
        up.setVariable(JSONUtil.toJsonStr(newDict));
        baseMapper.updateById(up);
    }

    @Override
    public void removeVariable(Long processDefineId, String... keys) {
        ProcessInstance processInstance = baseMapper.selectById(processDefineId);
        Dict oldDict = JSONUtil.toBean(processInstance.getVariable(),Dict.class);
        for (int i = 0; i < keys.length; i++) {
            oldDict.remove(keys[i]);
        }
        ProcessInstance up = new ProcessInstance();
        up.setId(processDefineId);
        up.setVariable(JSONUtil.toJsonStr(oldDict));
        baseMapper.updateById(up);
    }

    @Override
    public void saveProcessInstance(ProcessInstance processInstance) {
        Date now = new Date();
        processInstance.setCreateTime(now);
        processInstance.setUpdateTime(now);
        baseMapper.insert(processInstance);
    }


    @Override
    public void interrupt(Long processInstanceId, String operator) {
        Date now = new Date();
        // 1. 将该流程实例产生的任务状态修改为终止
        ProcessTask processTask = new ProcessTask();
        processTask.setTaskState(ProcessTaskStateEnum.INTERRUPT.getCode());
        processTask.setUpdateTime(now);
        processTask.setUpdateUser(operator);
        processTaskMapper.update(processTask,
                Wrappers.lambdaUpdate(ProcessTask.class)
                        .eq(ProcessTask::getProcessInstanceId,processInstanceId)
                        .eq(ProcessTask::getTaskState, ProcessTaskStateEnum.DOING.getCode())
        );
        // 2. 将该流程实例状态修改为终止
        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setState(ProcessInstanceStateEnum.INTERRUPT.getCode());
        processInstance.setUpdateTime(now);
        processInstance.setUpdateUser(operator);
        baseMapper.update(processInstance,
                Wrappers.lambdaUpdate(ProcessInstance.class)
                        .eq(ProcessInstance::getId,processInstanceId)
                        .eq(ProcessInstance::getState,ProcessInstanceStateEnum.DOING.getCode())
        );
    }

    @Override
    public void resume(Long processInstanceId, String operator) {
        Date now = new Date();
        ProcessInstance processInstance = baseMapper.selectById(processInstanceId);
        // 1. 更新流程实例状态为进行中
        processInstance.setUpdateTime(now);
        processInstance.setUpdateUser(operator);
        processInstance.setState(ProcessInstanceStateEnum.DOING.getCode());
        baseMapper.updateById(processInstance);
        // 2.被终止的任务状态修改为进行中
        ProcessTask processTask = new ProcessTask();
        processTask.setUpdateUser(operator);
        processTask.setUpdateTime(now);
        processTask.setTaskState(ProcessTaskStateEnum.DOING.getCode());
        processTaskMapper.update(processTask,
                Wrappers.lambdaQuery(ProcessTask.class)
                        .eq(ProcessTask::getProcessInstanceId,processInstance)
                        .eq(ProcessTask::getTaskState, ProcessTaskStateEnum.INTERRUPT.getCode()));
    }

    @Override
    public void pending(Long processInstanceId, String operator) {
        Date now = new Date();
        // 1. 将该流程实例产生的任务状态修改为挂起
        ProcessTask processTask = new ProcessTask();
        processTask.setTaskState(ProcessTaskStateEnum.PENDING.getCode());
        processTask.setUpdateTime(now);
        processTask.setUpdateUser(operator);
        processTaskMapper.update(processTask,
                Wrappers.lambdaUpdate(ProcessTask.class)
                        .eq(ProcessTask::getProcessInstanceId,processInstanceId)
                        .eq(ProcessTask::getTaskState, ProcessTaskStateEnum.DOING.getCode())
        );
        // 2. 将该流程实例状态修改为挂起
        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setState(ProcessInstanceStateEnum.PENDING.getCode());
        processInstance.setUpdateTime(now);
        processInstance.setUpdateUser(operator);
        baseMapper.update(processInstance,
                Wrappers.lambdaUpdate(ProcessInstance.class)
                        .eq(ProcessInstance::getId,processInstanceId)
                        .eq(ProcessInstance::getState,ProcessInstanceStateEnum.DOING.getCode())
        );
    }

    @Override
    public void activate(Long processInstanceId, String operator) {
        Date now = new Date();
        ProcessInstance processInstance = baseMapper.selectById(processInstanceId);
        // 1. 更新流程实例状态为进行中
        processInstance.setUpdateTime(now);
        processInstance.setUpdateUser(operator);
        processInstance.setState(ProcessInstanceStateEnum.DOING.getCode());
        baseMapper.updateById(processInstance);
        // 2.被终止的任务状态修改为进行中
        ProcessTask processTask = new ProcessTask();
        processTask.setUpdateUser(operator);
        processTask.setUpdateTime(now);
        processTask.setTaskState(ProcessTaskStateEnum.DOING.getCode());
        processTaskMapper.update(processTask,
                Wrappers.lambdaQuery(ProcessTask.class)
                        .eq(ProcessTask::getProcessInstanceId,processInstance)
                        .eq(ProcessTask::getTaskState, ProcessTaskStateEnum.INTERRUPT.getCode()));
    }


    @Override
    public void updateProcessInstance(ProcessInstance processInstance) {
        processInstance.setUpdateTime(new Date());
        baseMapper.updateById(processInstance);
    }

    @Override
    public ProcessInstance getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startAndExecute(Long processDefineId, Dict args) {
        String operator = LoginUserHolder.getUserId().toString();
        FlowEngine flowEngine = SpringUtil.getBean(FlowEngine.class);
        ProcessInstance processInstance = flowEngine.startProcessInstanceById(processDefineId,operator,args);
        List<ProcessTask> processTaskList = flowEngine.processTaskService().getDoingTaskList(processInstance.getId(),new String[]{});
        // 取任务自动执行
        processTaskList.forEach(processTask -> {
            args.put(FlowConst.SUBMIT_TYPE, ProcessSubmitTypeEnum.APPLY.getCode());
            flowEngine.executeProcessTask(processTask.getId(),FlowConst.AUTO_ID,args);
        });
    }

    @Override
    public HighLightVO highLight(Long processInstanceId) {
        HighLightVO vo = new HighLightVO();
        ProcessInstanceVO processInstance = findById(processInstanceId);
        FlowEngine flowEngine = SpringUtil.getBean(FlowEngine.class);
        if(processInstance!=null) {
            ProcessModel processModel = flowEngine.processDefineService().getProcessModel(processInstance.getProcessDefineId());
            // 拿到正在进行中的任务==>活跃节点
            List<ProcessTask> processTaskList = flowEngine.processTaskService().getDoingTaskList(processInstanceId,new String[]{});
            processTaskList.forEach(task -> {
                if (!vo.getActiveNodeNames().contains(task.getTaskName())) {
                    vo.getActiveNodeNames().add(task.getTaskName());
                    recursionModel(processModel.getStart(), processInstance, processTaskList, task.getTaskName(), vo);
                }
            });
            // 拿到非正常结束的流程实例状态值
            List<Integer> orderStatusList = Arrays.stream(ProcessInstanceStateEnum.values()).filter(item -> {
                return !item.equals(ProcessInstanceStateEnum.DOING) && !item.equals(ProcessInstanceStateEnum.FINISHED);
            }).map(item -> {
                return item.getCode();
            }).collect(Collectors.toList());
            // 非正常结束，需要进行特殊处理
            if (orderStatusList.contains(processInstance.getState())) {
                List<ProcessTask> hisProcessTaskList = flowEngine.processTaskService().getDoneTaskList(processInstanceId,new String[]{});
                if(CollectionUtil.isNotEmpty(hisProcessTaskList)) {
                    ProcessTask lastProcessTask = hisProcessTaskList.get(hisProcessTaskList.size()-1);
                    NodeModel nodeModel = processModel.getNode(lastProcessTask.getTaskName());
                    recursionModel(processModel.getStart(), processInstance, hisProcessTaskList, nodeModel.getOutputs().get(0).getTo(), vo);
                }
            } else {
                List<EndModel> endModels = processModel.getModels(EndModel.class);
                if (endModels != null) {
                    endModels.forEach(endModel -> {
                        recursionModel(processModel.getStart(), processInstance, processTaskList, endModel.getName(), vo);
                    });
                }
            }
        }
        return vo;
    }

    @Override
    public List<ProcessTaskVO> approvalRecord(Long processInstanceId) {
        List<ProcessTask> processTaskList = processTaskMapper.selectList(
                Wrappers.lambdaQuery(ProcessTask.class)
                .eq(ProcessTask::getProcessInstanceId,processInstanceId)
                .notIn(ProcessTask::getTaskState,ProcessTaskStateEnum.DOING,ProcessTaskStateEnum.WITHDRAW)
                .orderByAsc(ProcessTask::getUpdateTime)
        );
        List<ProcessTaskVO> res = new ArrayList<>();
        processTaskList.forEach(processTask -> {
            ProcessTaskVO vo = BeanUtil.toBean(processTask,ProcessTaskVO.class);
            res.add(vo);
        });
        return res;
    }

    @Override
    public void withdraw(Long processInstanceId, String operator) {
        Date now = new Date();
        // 1. 将该流程实例状态修改为撤回
        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setState(ProcessInstanceStateEnum.WITHDRAW.getCode());
        processInstance.setUpdateTime(now);
        processInstance.setUpdateUser(operator);
        int count = baseMapper.update(processInstance,
                Wrappers.lambdaUpdate(ProcessInstance.class)
                        .eq(ProcessInstance::getId,processInstanceId)
                        .eq(ProcessInstance::getState,ProcessInstanceStateEnum.DOING.getCode())
        );
        if(SqlHelper.retBool(count)) {
            // 2. 将该流程实例产生的任务状态修改为撤回
            ProcessTask processTask = new ProcessTask();
            processTask.setTaskState(ProcessTaskStateEnum.WITHDRAW.getCode());
            processTask.setUpdateTime(now);
            processTask.setUpdateUser(operator);
            processTaskMapper.update(processTask,
                    Wrappers.lambdaUpdate(ProcessTask.class)
                            .eq(ProcessTask::getProcessInstanceId, processInstanceId)
                            .eq(ProcessTask::getTaskState,ProcessTaskStateEnum.DOING)
            );
        }
    }

    /**
     * 递归模型处理历史节点与历史边
     *
     * @param nodeModel    下一个节点
     * @param processInstance 历史实例
     * @param processTaskList 历史任务
     * @param taskName     当前任务节点名称（递归停止标识）
     * @param vo           结果
     */
    private void recursionModel(NodeModel nodeModel, ProcessInstance processInstance, List<ProcessTask> processTaskList, String taskName, HighLightVO vo) {
        if (nodeModel.getName().equals(taskName)) {
            if (nodeModel instanceof EndModel) {
                vo.getHistoryNodeNames().add(nodeModel.getName());
            }
            return;
        }
        if (!vo.getHistoryNodeNames().contains(nodeModel.getName())) {
            vo.getHistoryNodeNames().add(nodeModel.getName());
            nodeModel.getOutputs().stream().filter(output -> {
                // 默认取决策节点前面第一个节点为任务节点-待优化
                NodeModel defaultDecisionInputModel = null;
                ProcessTask historyTask = null;
                if (nodeModel instanceof DecisionModel) {
                    defaultDecisionInputModel = nodeModel.getInputs().get(0).getSource();
                    NodeModel finalDefaultDecisionInputModel = defaultDecisionInputModel;
                    historyTask = processTaskList.stream().filter(hisTask -> {
                        return finalDefaultDecisionInputModel.getName().equals(hisTask.getTaskName());
                    }).findAny().orElse(null);
                }
                Map<String, Object> args = new HashMap<>();
                args.putAll(JSONUtil.parseObj(processInstance.getVariable()));
                if (historyTask != null) {
                    args.putAll(JSONUtil.parseObj(historyTask.getVariable()));
                }
                if (StrUtil.isNotEmpty(output.getExpr())
                        && nodeModel instanceof DecisionModel
                        && defaultDecisionInputModel != null) {
                    return Convert.toBool(ExpressionUtil.eval(output.getExpr(),args));
                }
                if (nodeModel instanceof DecisionModel) {
                    String expr = ((DecisionModel) nodeModel).getExpr();
                    if (StrUtil.isNotEmpty(expr)) {
                        String nextNodeName = Convert.toStr(ExpressionUtil.eval(expr,JSONUtil.parseObj(historyTask.getVariable())));
                        return output.getTo().equals(nextNodeName);
                    }
                }
                return true;
            }).forEach(transitionModel -> {
                if (!vo.getHistoryEdgeNames().contains(transitionModel.getName())) {
                    vo.getHistoryEdgeNames().add(transitionModel.getName());
                    recursionModel(transitionModel.getTarget(), processInstance, processTaskList, taskName, vo);
                }
            });
        }
    }
}
