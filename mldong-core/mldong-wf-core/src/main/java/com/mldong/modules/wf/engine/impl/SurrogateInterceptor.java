package com.mldong.modules.wf.engine.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.mldong.modules.wf.engine.FlowInterceptor;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.service.ProcessSurrogateService;
import com.mldong.modules.wf.service.ProcessTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 任务拦截器，处理代理人
 * @author mldong
 * @date 2023/12/6
 */
@Component
@RequiredArgsConstructor
public class SurrogateInterceptor implements FlowInterceptor {
    private final ProcessSurrogateService processSurrogateService;
    private final ProcessTaskService processTaskService;
    @Override
    public void intercept(Execution execution) {
        execution.getProcessTaskList().forEach(processTask -> {
            List<String> actorList = processTaskService.getTaskActors(processTask.getId());
            actorList.forEach(actor->{
                String agent = processSurrogateService.getSurrogate(actor,execution.getProcessModel().getName());
                if(StrUtil.isNotEmpty(agent)) {
                    processTaskService.addTaskActor(processTask.getId(), CollectionUtil.newArrayList(agent));
                }
            });
        });
    }
}
