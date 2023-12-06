package com.mldong.modules.wf.mapper;

import com.mldong.modules.wf.entity.ProcessSurrogate;
import com.mldong.modules.wf.vo.ProcessSurrogateVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 流程委托代理 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-12-06
 */
@Mapper
public interface ProcessSurrogateMapper extends BaseMapper<ProcessSurrogate> {
    List<ProcessSurrogateVO> selectCustom(IPage<ProcessSurrogateVO> page, @Param(Constants.WRAPPER) Wrapper<ProcessSurrogate> wrapper);
    ProcessSurrogateVO findById(@Param("id") Long id);
}
