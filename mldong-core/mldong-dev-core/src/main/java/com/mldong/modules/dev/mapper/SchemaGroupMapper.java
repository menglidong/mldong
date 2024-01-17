package com.mldong.modules.dev.mapper;

import com.mldong.modules.dev.entity.SchemaGroup;
import com.mldong.modules.dev.vo.SchemaGroupVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 模型分组 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2024-01-17
 */
@Mapper
public interface SchemaGroupMapper extends BaseMapper<SchemaGroup> {
    List<SchemaGroupVO> selectCustom(IPage<SchemaGroupVO> page, @Param(Constants.WRAPPER) Wrapper<SchemaGroup> wrapper);
    SchemaGroupVO findById(@Param("id") Long id);
}
