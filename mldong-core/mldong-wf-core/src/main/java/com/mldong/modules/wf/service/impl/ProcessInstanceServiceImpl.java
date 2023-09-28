package com.mldong.modules.wf.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessInstancePageParam;
import com.mldong.modules.wf.dto.ProcessInstanceParam;
import com.mldong.modules.wf.engine.FlowEngine;
import com.mldong.modules.wf.enums.FlowConst;
import com.mldong.modules.wf.enums.ProcessInstanceStateEnum;
import com.mldong.modules.wf.enums.ProcessTaskStateEnum;
import com.mldong.modules.wf.entity.ProcessDefine;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.mapper.ProcessInstanceMapper;
import com.mldong.modules.wf.mapper.ProcessTaskMapper;
import com.mldong.modules.wf.service.ProcessDefineService;
import com.mldong.modules.wf.service.ProcessInstanceService;
import com.mldong.modules.wf.vo.ProcessInstanceVO;
import com.mldong.web.LoginUserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
            flowEngine.executeProcessTask(processTask.getId(),FlowConst.AUTO_ID,args);
        });
    }
}
