package com.mldong.modules.wf.mapper;

import com.mldong.modules.wf.entity.ProcessDefine;
import com.mldong.modules.wf.vo.ProcessDefineVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 流程定义 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Mapper
public interface ProcessDefineMapper extends BaseMapper<ProcessDefine> {
    List<ProcessDefineVO> selectCustom(IPage<ProcessDefineVO> page, @Param(Constants.WRAPPER) Wrapper<ProcessDefine> wrapper);
    ProcessDefineVO findById(@Param("id") Long id);

    /**
     * 根据名称获取最新的流程定义
     * @param name
     * @return
     */
    ProcessDefineVO selectLastByName(@Param("name") String name);
}
