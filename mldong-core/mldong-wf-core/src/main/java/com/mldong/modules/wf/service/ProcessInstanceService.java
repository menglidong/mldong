package com.mldong.modules.wf.service;

import cn.hutool.core.lang.Dict;
import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessInstancePageParam;
import com.mldong.modules.wf.dto.ProcessInstanceParam;
import com.mldong.modules.wf.entity.ProcessDefine;
import com.mldong.modules.wf.vo.ProcessInstanceVO;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 流程实例 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
public interface ProcessInstanceService extends IService<ProcessInstance> {
    /**
    * 添加流程实例
    * @param param
    * @return
    */
    boolean save(ProcessInstanceParam param);

    /**
    * 更新流程实例
    * @param param
    * @return
    */
    boolean update(ProcessInstanceParam param);

    /**
    * 自定义分页查询流程实例
    * @param param
    * @return
    */
    CommonPage<ProcessInstanceVO> page(ProcessInstancePageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    ProcessInstanceVO findById(Long id);
    /**
     * 将流程实例修改为已完成
     * @param processInstanceId
     */
    void finishProcessInstance(Long processInstanceId);
    /**
     * 根据流程、操作人员、父流程实例ID创建流程实例
     * @param processDefine 流程定义对象
     * @param operator 操作人员ID
     * @param args 参数列表
     * @return ProcessInstance 活动流程实例对象
     */
    ProcessInstance createProcessInstance(ProcessDefine processDefine, String operator, Dict args);

    /**
     * 向指定实例id添加全局变量数据
     * @param processDefineId 实例id
     * @param args 变量数据
     */
    void addVariable(Long processDefineId, Dict args);

    /**
     * 移除指定实例id中的全局变量
     * @param processDefineId 实例id
     * @param keys 移除变量keys
     */
    void removeVariable(Long processDefineId, String... keys);


    /**
     * 保存流程实例
     * @param processInstance 流程实例对象
     */
    void saveProcessInstance(ProcessInstance processInstance);

    /**
     * 流程实例强制终止与唤醒相对应
     * @param processInstanceId 流程实例id
     * @param operator 处理人员
     */
    void interrupt(Long processInstanceId, String operator);

    /**
     * 唤醒历史流程实例与终止相对应
     * @param processInstanceId 流程实例id
     * @param operator
     */
    void resume(Long processInstanceId,String operator);

    /**
     * 挂起流程与激活相对应
     * @param processInstanceId 流程实例id
     * @param operator 处理人员
     */
    void pending(Long processInstanceId, String operator);

    /**
     * 激活流程与挂起相对应
     * @param processInstanceId
     * @param operator
     */
    void activate(Long processInstanceId, String operator);

    /**
     * 更新流程实例
     * @param processInstance 流程实例对象
     */
    void updateProcessInstance(ProcessInstance processInstance);

    /**
     * 根据ID获取流程实例
     * @param id
     * @return
     */
    ProcessInstance getById(Long id);
}
