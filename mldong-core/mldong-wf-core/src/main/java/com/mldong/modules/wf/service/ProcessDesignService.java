package com.mldong.modules.wf.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessDesignPageParam;
import com.mldong.modules.wf.dto.ProcessDesignParam;
import com.mldong.modules.wf.entity.ProcessDesign;
import com.mldong.modules.wf.vo.ProcessDesignVO;
/**
 * <p>
 * 流程设计 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-25
 */
public interface ProcessDesignService extends IService<ProcessDesign> {
    /**
    * 添加流程设计
    * @param param
    * @return
    */
    boolean save(ProcessDesignParam param);

    /**
    * 更新流程设计
    * @param param
    * @return
    */
    boolean update(ProcessDesignParam param);

    /**
    * 自定义分页查询流程设计
    * @param param
    * @return
    */
    CommonPage<ProcessDesignVO> page(ProcessDesignPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    ProcessDesignVO findById(Long id);

    /**
     * 更新流程定义信息
     * @param jsonObject
     * @return
     */
    boolean updateDefine(JSONObject jsonObject);

    /**
     * 流程定义部署
     * @param processDesignId
     */
    void deploy(Long processDesignId);
}
