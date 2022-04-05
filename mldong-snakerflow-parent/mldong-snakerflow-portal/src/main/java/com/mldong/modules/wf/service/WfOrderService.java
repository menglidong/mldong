package com.mldong.modules.wf.service;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.wf.dto.WfOrderPageParam;
import com.mldong.modules.wf.dto.WfOrderParam;
import com.mldong.modules.wf.vo.WfHighlihtDataVO;
import org.snaker.engine.entity.HistoryOrder;

public interface WfOrderService {
    /**
     * 启用流程
     * @param param
     */
    public void startAndExecute(WfOrderParam param);

    /**
     * 通过流程定义名称启动流程
     * @param param
     */
    public void startAndExecuteByName(WfOrderParam param);
    /**
     * 分页查询流程实例列表
     * @param param
     * @return
     */
    public CommonPage<HistoryOrder> list(WfOrderPageParam param);

    /**
     * 获取流程实例详情
     * @param id
     * @return
     */
    public HistoryOrder get(String id);

    /**
     * 级联删除-流程任务信息
     * @param id
     */
    public void cascadeRemove(String id);
    /**
     * 获取节点、边高亮数据
     * @param id
     * @return
     */
    public WfHighlihtDataVO highLightData(String id);
}
