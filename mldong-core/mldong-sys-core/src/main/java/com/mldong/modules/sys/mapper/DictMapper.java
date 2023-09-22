package com.mldong.modules.sys.mapper;

import com.mldong.modules.sys.entity.Dict;
import com.mldong.modules.sys.vo.DictVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 字典 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {
    List<DictVO> selectCustom(IPage<DictVO> page, @Param(Constants.WRAPPER) Wrapper<Dict> wrapper);
    DictVO findById(@Param("id") Long id);
}
