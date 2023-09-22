package com.mldong.modules.sys.mapper;

import com.mldong.modules.sys.entity.DictItem;
import com.mldong.modules.sys.vo.DictItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 字典项 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Mapper
public interface DictItemMapper extends BaseMapper<DictItem> {
    List<DictItemVO> selectCustom(IPage<DictItemVO> page, @Param(Constants.WRAPPER) Wrapper<DictItem> wrapper);
    DictItemVO findById(@Param("id") Long id);
}
