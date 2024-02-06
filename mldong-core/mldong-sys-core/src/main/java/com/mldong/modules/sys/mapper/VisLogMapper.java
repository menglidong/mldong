package com.mldong.modules.sys.mapper;

import com.mldong.modules.sys.entity.VisLog;
import com.mldong.modules.sys.vo.VisLogVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 系统访问日志表 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2024-02-06
 */
@Mapper
public interface VisLogMapper extends BaseMapper<VisLog> {
    List<VisLogVO> selectCustom(IPage<VisLogVO> page, @Param(Constants.WRAPPER) Wrapper<VisLog> wrapper);
    VisLogVO findById(@Param("id") Long id);
}
