package com.mldong.modules.sys.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.sys.dto.RolePageParam;
import com.mldong.modules.sys.dto.RoleParam;
import com.mldong.modules.sys.vo.RoleVO;
import com.mldong.modules.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
public interface RoleService extends IService<Role> {
  /**
  * 添加角色
  * @param param
  * @return
  */
  boolean save(RoleParam param);

  /**
  * 更新角色
  * @param param
  * @return
  */
  boolean update(RoleParam param);

  /**
  * 自定义分页查询角色
  * @param param
  * @return
  */
  CommonPage<RoleVO> page(RolePageParam param);
  /**
  * 通过id查询
  * @param id
  * @return
  */
  RoleVO findById(Long id);
}
