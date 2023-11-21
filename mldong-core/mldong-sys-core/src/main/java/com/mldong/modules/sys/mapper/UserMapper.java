package com.mldong.modules.sys.mapper;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mldong.modules.sys.entity.User;
import com.mldong.modules.sys.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<UserVO> selectCustom(IPage<UserVO> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
    UserVO findById(@Param("id") Long id);
    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    default User selectByUserName(String userName) {
        List<User> userList =  selectList(Wrappers.lambdaQuery(User.class).eq(User::getUserName,userName));
        return CollectionUtil.isEmpty(userList) ? null : userList.get(0);
    }
    /**
     * 通过手机号查询
     * @param mobilePhone
     * @return
     */
    default User selectByMobilePhone(String mobilePhone) {
        List<User> userList =  selectList(Wrappers.lambdaQuery(User.class).eq(User::getMobilePhone,mobilePhone));
        return CollectionUtil.isEmpty(userList) ? null : userList.get(0);
    }

    /**
     * 根据角色id查询用户列表
     * @param roleCode
     * @return
     */
    List<User> selectUserListByRoleCode(String roleCode);
}
