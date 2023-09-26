package com.mldong.modules.wf.mapper;

import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.vo.ProcessInstanceVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 流程实例 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Mapper
public interface ProcessInstanceMapper extends BaseMapper<ProcessInstance> {
    List<ProcessInstanceVO> selectCustom(IPage<ProcessInstanceVO> page, @Param(Constants.WRAPPER) Wrapper<ProcessInstance> wrapper);
    ProcessInstanceVO findById(@Param("id") Long id);
}
