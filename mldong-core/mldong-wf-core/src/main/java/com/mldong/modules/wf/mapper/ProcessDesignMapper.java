package com.mldong.modules.wf.mapper;

import com.mldong.modules.wf.entity.ProcessDesign;
import com.mldong.modules.wf.vo.ProcessDesignVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 流程设计 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-25
 */
@Mapper
public interface ProcessDesignMapper extends BaseMapper<ProcessDesign> {
    List<ProcessDesignVO> selectCustom(IPage<ProcessDesignVO> page, @Param(Constants.WRAPPER) Wrapper<ProcessDesign> wrapper);
    ProcessDesignVO findById(@Param("id") Long id);
}
