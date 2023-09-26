package com.mldong.modules.wf.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessDefinePageParam;
import com.mldong.modules.wf.dto.ProcessDefineParam;
import com.mldong.modules.wf.vo.ProcessDefineVO;
import com.mldong.modules.wf.entity.ProcessDefine;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
