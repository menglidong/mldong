package com.mldong.modules.sys.mapper;

import com.mldong.modules.sys.entity.FileInfo;
import com.mldong.modules.sys.vo.FileInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 文件信息 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2024-02-23
 */
@Mapper
public interface FileInfoMapper extends BaseMapper<FileInfo> {
    List<FileInfoVO> selectCustom(IPage<FileInfoVO> page, @Param(Constants.WRAPPER) Wrapper<FileInfo> wrapper);
    FileInfoVO findById(@Param("id") Long id);
}
