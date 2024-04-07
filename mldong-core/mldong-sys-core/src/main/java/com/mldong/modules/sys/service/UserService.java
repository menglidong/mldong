package com.mldong.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mldong.auth.LoginUser;
import com.mldong.base.CommonPage;
import com.mldong.base.LabelValueVO;
import com.mldong.modules.sys.dto.UserPageParam;
import com.mldong.modules.sys.dto.UserParam;
import com.mldong.modules.sys.entity.User;
import com.mldong.modules.sys.vo.DeptUserTreeVO;
import com.mldong.modules.sys.vo.OnlineUserVO;
import com.mldong.modules.sys.vo.UserVO;

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
   * 锁定用户
   * @param ids
   * @return
   */
  boolean locked(List<Long> ids);
  /**
   * 取消锁定用户
   * @param ids
   * @return
   */
  boolean unLocked(List<Long> ids);
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

  /**
   * 获取部门用户树
   * @return
   */
  List<DeptUserTreeVO> getDeptUserTree();

  /**
   * 获取某个部门的用户
   * @param deptId
   * @return
   */
  List<User> listByDeptId(Long deptId);

  /**
   * 给用户授权角色
   * @param userId
   * @param roleIds
   */
  void grantRole(Long userId,List<Long> roleIds);
  /**
   * 获取在线用户列表
   * @param param
   * @return
   */
  List<OnlineUserVO> onlineUserList(UserPageParam param);

  /**
   * 根据token值强制注销
   * @param tokenValue
   */
  void logoutByTokenValue(String tokenValue);
  /**
   * 根据登录id踢下线
   * @param tokenValue
   */
  void kickoutByTokenValue(String tokenValue);
  /**
   * 根据token值强制注销
   * @param loginId
   */
  void logoutByLoginId(Long loginId);
  /**
   * 根据登录id踢下线
   * @param loginId
   */
  void kickoutByLoginId(Long loginId);
}
