package com.mldong.modules.wf.engine.scheduling;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Dict;
import com.mldong.modules.wf.engine.core.ServiceContext;
import com.mldong.modules.wf.engine.event.ProcessEvent;
import com.mldong.modules.wf.engine.event.ProcessEventListener;
import com.mldong.modules.wf.enums.ProcessEventTypeEnum;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mldong
 * @date 2023/12/5
 */
@Component
public class SchedulerProcessEventListener implements ProcessEventListener {
    @Override
    public void onEvent(ProcessEvent event) {
        List<IScheduler> schedulerList = ServiceContext.findList(IScheduler.class);
        if(CollectionUtil.newArrayList(
                ProcessEventTypeEnum.PROCESS_INSTANCE_START,
                ProcessEventTypeEnum.PROCESS_TASK_START
        ).contains(event.getEventType())) {
            // 流程实例开始事件、流程任务开始事件，添加作业到调度器
            schedulerList.forEach(scheduler->{
                scheduler.addJob(event.getEventType().name()+"_"+event.getSourceId(),
                        Dict.of(
                                IScheduler.SOURCE_ID_KEY,event.getSourceId(),
                                IScheduler.SOURCE_TYPE_KEY,event.getEventType().getCode()
                        ));
            });
        } else if(CollectionUtil.newArrayList(
                ProcessEventTypeEnum.PROCESS_INSTANCE_END,
                ProcessEventTypeEnum.PROCESS_TASK_END
        ).contains(event.getEventType())) {
            // 流程实例结束事件、流程任务结束事件，从调度器移除作业
            schedulerList.forEach(scheduler->{
                scheduler.removeJob(event.getEventType().name()+"_"+event.getSourceId());
            });
        }
    }
}
