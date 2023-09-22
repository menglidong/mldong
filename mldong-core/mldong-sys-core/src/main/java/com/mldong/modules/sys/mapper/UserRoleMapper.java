package com.mldong.modules.sys.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mldong.modules.sys.entity.UserRole;
import com.mldong.modules.sys.vo.UserRoleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * r_用户角色关系 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<UserRoleVO> selectCustom(IPage<UserRoleVO> page, @Param(Constants.WRAPPER) Wrapper<UserRole> wrapper);
    UserRoleVO findById(@Param("id") Long id);
    default List<Long> selectRoleIdByUserId(Long userId) {
        return selectList(Wrappers.lambdaQuery(UserRole.class).eq(UserRole::getUserId, userId)).stream().map(item->{
            return item.getRoleId();
        }).collect(Collectors.toList());
    }
}
