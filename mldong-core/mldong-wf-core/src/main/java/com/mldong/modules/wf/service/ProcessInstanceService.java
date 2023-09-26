package com.mldong.modules.wf.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessInstancePageParam;
import com.mldong.modules.wf.dto.ProcessInstanceParam;
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
}
