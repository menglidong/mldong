package com.mldong.modules.wf.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessDefinePageParam;
import com.mldong.modules.wf.dto.ProcessDefineParam;
import com.mldong.modules.wf.engine.model.ProcessModel;
import com.mldong.modules.wf.vo.ProcessDefineVO;
import com.mldong.modules.wf.entity.ProcessDefine;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;

/**
 * <p>
 * 流程定义 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
public interface ProcessDefineService extends IService<ProcessDefine> {
    /**
    * 添加流程定义
    * @param param
    * @return
    */
    boolean save(ProcessDefineParam param);

    /**
    * 更新流程定义
    * @param param
    * @return
    */
    boolean update(ProcessDefineParam param);

    /**
    * 自定义分页查询流程定义
    * @param param
    * @return
    */
    CommonPage<ProcessDefineVO> page(ProcessDefinePageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    ProcessDefineVO findById(Long id);
    /**
     * 部署流程定义文件，同name存在多个版本
     * @param inputStream
     * @see #deploy(byte[])
     * @return Long
     * @see Long deploy(byte[] bytes);
     */
    Long deploy(InputStream inputStream);
    /**
     * 部署流程定义文件，同name存在多个版本
     * @param bytes
     * @return Long
     */
    Long deploy(byte[] bytes);
    /**
     * 部署流程定义文件，同name存在多个版本
     * @param defineJsonStr
     * @see #deploy(byte[])
     * @return Long
     * @see Long deploy(byte[] bytes);
     */
    Long deploy(String defineJsonStr);
    /**
     * 重新部署定义文件，按id更新json
     * @param processDefineId
     * @param inputStream
     * @return Long
     */
    void redeploy(Long processDefineId,InputStream inputStream);
    /**
     * 重新部署定义文件，按id更新json
     * @param processDefineId
     * @param bytes
     * @return Long
     */
    void redeploy(Long processDefineId,byte[] bytes);
    /**
     * 重新部署定义文件，按id更新json
     * @param defineJsonStr
     * @return Long
     */
    void redeploy(Long processDefineId,String defineJsonStr);

    /**
     * 卸载流程
     * @param processDefineId
     */
    public void unDeploy(Long processDefineId);

    /**
     * 只更新type
     * @param processDefineId
     * @param type
     */
    void updateType(Long processDefineId, String type);

    /**
     * 根据ID获取流程定义对象
     * @param processDefineId
     * @return
     */
    com.mldong.modules.wf.entity.ProcessDefine getById(Long processDefineId);

    /**
     * 根据id获取流程模型
     * @param processDefineId
     * @return
     */
    ProcessModel getProcessModel(Long processDefineId);

    /**
     * 流程定义转成流程模型
     * @param processDefine
     * @return
     */
    ProcessModel processDefineToModel(ProcessDefine processDefine);

    /**
     * 根据id获取定义json字符串
     * @param processDefineId
     * @return
     */
    String getDefineJsonStr(Long processDefineId);
}
