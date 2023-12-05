package com.mldong.modules.wf.engine.event;

import com.mldong.modules.wf.engine.core.ServiceContext;

import java.util.List;

/**
 * 流程事件发布者类
 * @author mldong
 * @date 2023/12/5
 */
public class ProcessPublisher {
    // 事件通知方法
    public static void notify(ProcessEvent event) {
        // 这里直接从上下文中获取所有的监听器，并调用监听器的onEvent方法==>通知
        List<ProcessEventListener> processEventListenerList = ServiceContext.findList(ProcessEventListener.class);
        processEventListenerList.forEach(processEventListener -> {
            processEventListener.onEvent(event);
        });
    }
}
