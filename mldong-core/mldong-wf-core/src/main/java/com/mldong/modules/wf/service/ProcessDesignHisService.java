package com.mldong.modules.wf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessDesignHisPageParam;
import com.mldong.modules.wf.dto.ProcessDesignHisParam;
import com.mldong.modules.wf.entity.ProcessDesign;
import com.mldong.modules.wf.entity.ProcessDesignHis;
import com.mldong.modules.wf.vo.ProcessDesignHisVO;
/**
 * <p>
 * 流程设计历史 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-25
 */
public interface ProcessDesignHisService extends IService<ProcessDesignHis> {
    /**
    * 添加流程设计历史
    * @param param
    * @return
    */
    boolean save(ProcessDesignHisParam param);

    /**
    * 更新流程设计历史
    * @param param
    * @return
    */
    boolean update(ProcessDesignHisParam param);

    /**
    * 自定义分页查询流程设计历史
    * @param param
    * @return
    */
    CommonPage<ProcessDesignHisVO> page(ProcessDesignHisPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    ProcessDesignHisVO findById(Long id);

    /**
     * 获取最新的流程设计
     * @param processDesignId
     * @return
     */
    ProcessDesignHis getLatestByProcessDesignId(Long processDesignId);
}
