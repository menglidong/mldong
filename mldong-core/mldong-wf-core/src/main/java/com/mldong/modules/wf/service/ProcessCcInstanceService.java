package com.mldong.modules.wf.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessCcInstancePageParam;
import com.mldong.modules.wf.dto.ProcessCcInstanceParam;
import com.mldong.modules.wf.vo.ProcessCcInstanceVO;
import com.mldong.modules.wf.entity.ProcessCcInstance;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 流程实例抄送 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-12-05
 */
public interface ProcessCcInstanceService extends IService<ProcessCcInstance> {
    /**
    * 添加流程实例抄送
    * @param param
    * @return
    */
    boolean save(ProcessCcInstanceParam param);

    /**
    * 更新流程实例抄送
    * @param param
    * @return
    */
    boolean update(ProcessCcInstanceParam param);

    /**
    * 自定义分页查询流程实例抄送
    * @param param
    * @return
    */
    CommonPage<ProcessCcInstanceVO> page(ProcessCcInstancePageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    ProcessCcInstanceVO findById(Long id);
}
