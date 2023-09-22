package com.mldong.modules.sys.mapper;

import com.mldong.modules.sys.entity.Config;
import com.mldong.modules.sys.vo.ConfigVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 配置 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {
    List<ConfigVO> selectCustom(IPage<ConfigVO> page, @Param(Constants.WRAPPER) Wrapper<Config> wrapper);
    ConfigVO findById(@Param("id") Long id);
}
