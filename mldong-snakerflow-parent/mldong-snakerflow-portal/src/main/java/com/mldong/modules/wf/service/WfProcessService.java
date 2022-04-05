package com.mldong.modules.wf.service;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.wf.dto.WfProcessPageParam;
import org.snaker.engine.entity.Process;


public interface WfProcessService {
    /**
     * 部署流程定义文件
     * @param xml
     */
    public void deploy(String xml);

    /**
     * 重新部署流程定义文件
     * @param id
     * @param xml
     */
    public void redeploy(String id,String xml);

    /**
     * 卸载流程定义文件
     * @param id
     */
    public void undeploy(String id);

    /**
     * 分页查询流程定义列表
     * @param param
     * @return
     */
    public CommonPage<Process> list(WfProcessPageParam param);

    /**
     * 获取流程定义详情
     * @param id
     * @return
     */
    public Process get(String id);

    /**
     * 获取流程定义xml
     * @param id
     * @return
     */
    public String getXml(String id);

    /**
     * 级联删除-同时会删除流程实例、流程任务信息
     * @param id
     */
    public void cascadeRemove(String id);
}
