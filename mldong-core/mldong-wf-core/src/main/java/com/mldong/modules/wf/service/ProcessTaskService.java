package com.mldong.modules.wf.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessTaskPageParam;
import com.mldong.modules.wf.dto.ProcessTaskParam;
import com.mldong.modules.wf.vo.ProcessTaskVO;
import com.mldong.modules.wf.entity.ProcessTask;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 流程任务 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
public interface ProcessTaskService extends IService<ProcessTask> {
    /**
    * 添加流程任务
    * @param param
    * @return
    */
    boolean save(ProcessTaskParam param);

    /**
    * 更新流程任务
    * @param param
    * @return
    */
    boolean update(ProcessTaskParam param);

    /**
    * 自定义分页查询流程任务
    * @param param
    * @return
    */
    CommonPage<ProcessTaskVO> page(ProcessTaskPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    ProcessTaskVO findById(Long id);
}
