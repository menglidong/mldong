package com.mldong.modules.dev.mapper;

import com.mldong.modules.dev.entity.SchemaField;
import com.mldong.modules.dev.vo.SchemaFieldVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 模型字段 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Mapper
public interface SchemaFieldMapper extends BaseMapper<SchemaField> {
    List<SchemaFieldVO> selectCustom(IPage<SchemaFieldVO> page, @Param(Constants.WRAPPER) Wrapper<SchemaField> wrapper);
    SchemaFieldVO findById(@Param("id") Long id);
}
