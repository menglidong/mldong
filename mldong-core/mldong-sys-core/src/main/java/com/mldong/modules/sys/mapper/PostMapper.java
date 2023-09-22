package com.mldong.modules.sys.mapper;

import com.mldong.modules.sys.entity.Post;
import com.mldong.modules.sys.vo.PostVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 岗位 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {
    List<PostVO> selectCustom(IPage<PostVO> page, @Param(Constants.WRAPPER) Wrapper<Post> wrapper);
    PostVO findById(@Param("id") Long id);
}
