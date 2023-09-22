package com.mldong.modules.sys.service;

import com.mldong.auth.LoginUser;
import com.mldong.base.CommonPage;
import com.mldong.base.LabelValueVO;
import com.mldong.modules.sys.dto.UserPageParam;
import com.mldong.modules.sys.dto.UserParam;
import com.mldong.modules.sys.vo.UserVO;
import com.mldong.modules.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
public interface UserService extends IService<User> {
  /**
  * 添加用户
  * @param param
  * @return
  */
  boolean save(UserParam param);

  /**
  * 更新用户
  * @param param
  * @return
  */
  boolean update(UserParam param);

  /**
  * 自定义分页查询用户
  * @param param
  * @return
  */
  CommonPage<UserVO> page(UserPageParam param);
  /**
  * 通过id查询
  * @param id
  * @return
  */
  UserVO findById(Long id);
  /**
   * 重置用户密码
   * @param ids
   * @return
   */
  boolean resetPwd(List<Long> ids);

  /**
   * 用户下拉列表
   * @param param
   * @return
   */
  List<LabelValueVO> select(UserPageParam param);

  /**
   * 修改个人信息
   * @param param
   * @return
   */
  boolean updateInfo(UserParam param);

  /**
   * 获取用户个人信息
   * @return
   */
  LoginUser info();

  /**
   * 修改密码
   * @param param
   * @return
   */
  boolean updatePwd(UserParam param);

  /**
   * 通过手机号获取
   * @param phone
   * @return
   */
  User getByPhone(String phone);

  /**
   * 根据手机号创建用户
   * @param phone
   * @return
   */
  User createUserByPhone(String phone);
}
