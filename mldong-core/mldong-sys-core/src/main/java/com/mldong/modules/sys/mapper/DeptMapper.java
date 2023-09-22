package com.mldong.modules.sys.mapper;

import com.mldong.modules.sys.entity.Dept;
import com.mldong.modules.sys.vo.DeptVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 部门 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    List<DeptVO> selectCustom(IPage<DeptVO> page, @Param(Constants.WRAPPER) Wrapper<Dept> wrapper);
    DeptVO findById(@Param("id") Long id);
}
