package com.mldong.modules.sys.mapper;

import com.mldong.modules.sys.entity.RoleMenu;
import com.mldong.modules.sys.vo.RoleMenuVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * r_角色菜单关系 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    List<RoleMenuVO> selectCustom(IPage<RoleMenuVO> page, @Param(Constants.WRAPPER) Wrapper<RoleMenu> wrapper);
    RoleMenuVO findById(@Param("id") Long id);
}
