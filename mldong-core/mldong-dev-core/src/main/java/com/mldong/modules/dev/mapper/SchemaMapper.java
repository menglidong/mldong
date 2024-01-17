package com.mldong.modules.dev.mapper;

import com.mldong.modules.dev.entity.Schema;
import com.mldong.modules.dev.vo.SchemaVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 数据模型 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Mapper
public interface SchemaMapper extends BaseMapper<Schema> {
    List<SchemaVO> selectCustom(IPage<SchemaVO> page, @Param(Constants.WRAPPER) Wrapper<Schema> wrapper);
    SchemaVO findById(@Param("id") Long id);
}
