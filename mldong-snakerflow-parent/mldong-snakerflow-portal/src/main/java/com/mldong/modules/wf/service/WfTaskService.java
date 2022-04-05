package com.mldong.modules.wf.service;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.wf.dto.WfTaskPageParam;
import com.mldong.modules.wf.dto.WfTaskParam;
import org.snaker.engine.entity.WorkItem;

import java.util.List;

public interface WfTaskService {
    /**
     * 我的待办列表
     * @param param
     * @return
     */
    public CommonPage<WorkItem> todolist(WfTaskPageParam param);
    /**
     * 我的已办列表
     * @param param
     * @return
     */
    public CommonPage<WorkItem> donelist(WfTaskPageParam param);

    /**
     * 获取任务详情
     * @param id
     * @return
     */
    public WorkItem get(String id);

    /**
     * 执行任务
     * @param param
     */
    public void execute(WfTaskParam param);

    /**
     * 通过流程实例ID获取任务列表
     * @param orderId
     * @return
     */
    public List<WorkItem> listHisByOrderId(String orderId);
}
