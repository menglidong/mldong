package com.mldong.modules.wf.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessTaskActorPageParam;
import com.mldong.modules.wf.dto.ProcessTaskActorParam;
import com.mldong.modules.wf.vo.ProcessTaskActorVO;
import com.mldong.modules.wf.entity.ProcessTaskActor;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 流程任务和参与人关系 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
public interface ProcessTaskActorService extends IService<ProcessTaskActor> {
    /**
    * 添加流程任务和参与人关系
    * @param param
    * @return
    */
    boolean save(ProcessTaskActorParam param);

    /**
    * 更新流程任务和参与人关系
    * @param param
    * @return
    */
    boolean update(ProcessTaskActorParam param);

    /**
    * 自定义分页查询流程任务和参与人关系
    * @param param
    * @return
    */
    CommonPage<ProcessTaskActorVO> page(ProcessTaskActorPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    ProcessTaskActorVO findById(Long id);
}
