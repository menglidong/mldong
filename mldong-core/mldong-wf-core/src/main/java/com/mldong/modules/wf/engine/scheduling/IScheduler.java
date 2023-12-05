package com.mldong.modules.wf.engine.scheduling;

import cn.hutool.core.lang.Dict;

/**
 * 任务调度接口，增加、删除任务
 * @author mldong
 * @date 2023/12/4
 */
public interface IScheduler {
    String SOURCE_ID_KEY = "sourceId";
    String SOURCE_TYPE_KEY = "sourceType";
    /**
     * 添加作业到调度器
     * @param args
     */
    void addJob(String jobId,Dict args);

    /**
     * 从调度器中删除作业
     * @param jobId
     */
    void removeJob(String jobId);
}
