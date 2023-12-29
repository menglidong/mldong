package com.mldong.modules.biz.mapper;

import com.mldong.modules.biz.entity.Demo;
import com.mldong.modules.biz.vo.DemoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 演示 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-12-28
 */
@Mapper
public interface DemoMapper extends BaseMapper<Demo> {
    List<DemoVO> selectCustom(IPage<DemoVO> page, @Param(Constants.WRAPPER) Wrapper<Demo> wrapper);
    DemoVO findById(@Param("id") Long id);
}
