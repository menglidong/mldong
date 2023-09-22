package com.mldong.modules.sys.service.impl;

import com.mldong.modules.sys.entity.UserRole;
import com.mldong.modules.sys.mapper.UserRoleMapper;
import com.mldong.modules.sys.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * r_用户角色关系 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
