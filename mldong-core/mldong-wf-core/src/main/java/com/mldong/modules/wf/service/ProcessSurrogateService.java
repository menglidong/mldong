package com.mldong.modules.wf.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.wf.dto.ProcessSurrogatePageParam;
import com.mldong.modules.wf.dto.ProcessSurrogateParam;
import com.mldong.modules.wf.vo.ProcessSurrogateVO;
import com.mldong.modules.wf.entity.ProcessSurrogate;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 流程委托代理 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-12-06
 */
public interface ProcessSurrogateService extends IService<ProcessSurrogate> {
    /**
    * 添加流程委托代理
    * @param param
    * @return
    */
    boolean save(ProcessSurrogateParam param);

    /**
    * 更新流程委托代理
    * @param param
    * @return
    */
    boolean update(ProcessSurrogateParam param);

    /**
    * 自定义分页查询流程委托代理
    * @param param
    * @return
    */
    CommonPage<ProcessSurrogateVO> page(ProcessSurrogatePageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    ProcessSurrogateVO findById(Long id);
    /**
     * 获取代理人
     * @param operator
     * @param processName
     * @return
     */
    String getSurrogate(String operator,String processName);
}
