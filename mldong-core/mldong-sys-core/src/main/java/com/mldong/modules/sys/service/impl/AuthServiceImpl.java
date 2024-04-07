package com.mldong.modules.sys.service.impl;


import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mldong.auth.LoginToken;
import com.mldong.auth.LoginUser;
import com.mldong.base.YesNoEnum;
import com.mldong.consts.CommonConstant;
import com.mldong.context.constant.ConstantContextHolder;
import com.mldong.exception.ServiceException;
import com.mldong.modules.sys.dto.LoginParam;
import com.mldong.modules.sys.entity.*;
import com.mldong.modules.sys.enums.AdminTypeEnum;
import com.mldong.modules.sys.enums.MenuAppCodeEnum;
import com.mldong.modules.sys.enums.VisTypeEnum;
import com.mldong.modules.sys.enums.err.SysErrEnum;
import com.mldong.modules.sys.mapper.*;
import com.mldong.modules.sys.service.AuthService;
import com.mldong.modules.sys.service.RbacService;
import com.mldong.modules.sys.service.VisLogService;
import com.mldong.util.HttpServletUtil;
import com.mldong.web.LoginUserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限服务实现
 * @author mldong
 * @date 2023/9/20
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, StpInterface {
    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;
    private final DeptMapper deptMapper;
    private final PostMapper postMapper;
    private final VisLogService visLogService;
    @Override
    public LoginToken login(LoginParam param) {
        String appCode = HttpServletUtil.getRequest().getHeader(CommonConstant.APP_CODE_KEY);
        String grantType = param.getGrantType();
        // 非白名单账号下，校验授权类型
        if(!ConstantContextHolder.getGrantTypeWhiteListAccount().contains(param.getUserName())) {
            MenuAppCodeEnum.checkGrantType(appCode, grantType);
        }
        User user = null;
        // 默认的用户名/手机号+密码登录
        if(StrUtil.isNotEmpty(param.getUserName()) && StrUtil.isNotEmpty(param.getPassword())) {
            // 先查用户名
            user = userMapper.selectByUserName(param.getUserName());
            if(user == null) {
                // 用户名不存在，查手机号
                user = userMapper.selectByMobilePhone(param.getUserName());
            }
            if(user == null) {
                // 用户不存在
                visLogService.saveVisLog(VisTypeEnum.LOGIN, param.getUserName(), "N", "登录失败，用户名不存在");
                throw new ServiceException(SysErrEnum.USER_NOT_EXIST);
            }
            if(ObjectUtil.equals(user.getIsLocked(), YesNoEnum.YES.getCode())) {
                visLogService.saveVisLog(VisTypeEnum.LOGIN, param.getUserName(), "N", "登录失败，用户名已被锁定");
                throw new ServiceException(SysErrEnum.USER_IS_LOCKED);
            }
            String md5Password  = SecureUtil.md5(param.getPassword() + user.getSalt());
            if(!md5Password.equals(user.getPassword())) {
                visLogService.saveVisLog(VisTypeEnum.LOGIN, param.getUserName(), "N", "登录失败，密码错误");
                // 密码不正确
                throw new ServiceException(SysErrEnum.USER_NOT_EXIST);
            }
        }
        // sa-token登录
        SaLoginModel loginModel = new SaLoginModel();
        loginModel.setExtra(CommonConstant.LOGIN_USER_KEY,toLoginUser(user));
        StpUtil.login(user.getId(), loginModel);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        visLogService.saveVisLog(VisTypeEnum.LOGIN, param.getUserName(), "Y", "登录成功");
        return LoginToken.builder().userId(user.getId()).token(tokenInfo.getTokenValue()).build();
    }

    @Override
    public void logout(String token) {
        visLogService.saveVisLog(VisTypeEnum.LOGOUT, LoginUserHolder.getUserName(), "Y", "登出成功");
        StpUtil.logoutByTokenValue(token);
    }

    @Override
    public LoginUser toLoginUser(User user) {
        LoginUser loginUser = new LoginUser();
        // 设置登录时间戳
        loginUser.setLoginTimestamp(System.currentTimeMillis());
        // 设置登录ip
        loginUser.setLoginIp(ServletUtil.getClientIP(HttpServletUtil.getRequest()));
        // 设置登录浏览器信息
        loginUser.setLoginBrowser(HttpServletUtil.getRequest().getHeader(CommonConstant.USER_AGENT));
        // 设置用户基本信息
        BeanUtil.copyProperties(user, loginUser);
        // 设置用户角色信息
        loginUser.setRoleIds(userRoleMapper.selectRoleIdByUserId(user.getId()));
        if(!loginUser.getRoleIds().isEmpty()) {
            List<Role> roleList = roleMapper.selectBatchIds(loginUser.getRoleIds());
            loginUser.setRoleNames(roleList.stream().map(item->{return item.getName();}).collect(Collectors.toList()));
            loginUser.setRoleCodes(roleList.stream().map(item->{return item.getName();}).collect(Collectors.toList()));
        }
        // 设置用户机构信息
        Long deptId = loginUser.getDeptId();
        if(ObjectUtil.isNotEmpty(deptId)) {
            loginUser.setDeptId(deptId);
            Dept dept = deptMapper.selectById(deptId);
            if(dept!=null) {
                loginUser.setDeptName(dept.getName());
            }
        }
        // 设置用户岗位信息
        Long postId = loginUser.getPostId();
        if(ObjectUtil.isNotEmpty(postId)) {
            loginUser.setPostId(postId);
            Post post = postMapper.selectById(postId);
            if(post!=null) {
                loginUser.setPostName(post.getName());
            }
        }
        //设置扩展信息-当前应用编码
        String appCode = HttpServletUtil.getRequest().getHeader(CommonConstant.APP_CODE_KEY);
        if(StrUtil.isEmpty(appCode)) {
            appCode = MenuAppCodeEnum.PLATFORM.toString().toLowerCase();
        }
        loginUser.setAppCode(appCode);
        loginUser.setSuperAdmin(AdminTypeEnum.SUPER_ADMIN.getCode().equals(user.getAdminType()));
        return loginUser;
    }

    @Override
    public LoginToken playUser(Long userId) {
        User user = userMapper.selectById(userId);
        if(user == null) {
            ServiceException.throwBiz(SysErrEnum.USER_NOT_EXIST);
        }
        // 当前登录用户
        LoginUser currentUser = LoginUserHolder.me();
        // sa-token登录
        SaLoginModel loginModel = new SaLoginModel();
        // 被扮演的用户
        LoginUser loginUser = toLoginUser(user);
        if(loginUser.isSuperAdmin()) {
            if(!LoginUserHolder.me().isSuperAdmin()) {
                throw new ServiceException(9999, "非超级管理员不能扮演超级管理员");
            }
        }
        // 追加扮演用户信息
        loginUser.getExt().put(CommonConstant.USER_EXT_PLAYER_TOKEN,StpUtil.getTokenValue());
        loginUser.getExt().put(CommonConstant.USER_EXT_PLAYER_USER_ID,currentUser.getId());
        loginUser.getExt().put(CommonConstant.USER_EXT_PLAYER_ACCOUNT,currentUser.getUserName());
        loginUser.getExt().put(CommonConstant.USER_EXT_IS_PLAYER,true);
        loginModel.setExtra(CommonConstant.LOGIN_USER_KEY,loginUser);
        StpUtil.login(user.getId(), loginModel);
        StpUtil.switchTo(userId);
        LoginToken loginToken = LoginToken.builder().userId(userId).token(StpUtil.getTokenValue()).build();
        StpUtil.endSwitch();
        return loginToken;
    }

    @Override
    public LoginToken unPlayUser() {
        // 当前登录用户
        LoginUser currentUser = LoginUserHolder.me();
        // 获取扮演用户的userId/token
        Long userId = currentUser.getExt().getLong(CommonConstant.USER_EXT_PLAYER_USER_ID);
        String token = currentUser.getExt().getStr(CommonConstant.USER_EXT_PLAYER_TOKEN);
        // 退出当前账号
        StpUtil.logout();
        return LoginToken.builder().userId(userId).token(token).build();
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        LoginUser loginUser = LoginUserHolder.me();
        List<String> perms = new ArrayList<>();
        // 设置用户权限信息
        if(AdminTypeEnum.SUPER_ADMIN.getCode().equals(loginUser.getAdminType())) {
            // 超级管理员只需要返回admin标识
            perms.add(CommonConstant.SUPER_ADMIN_PERM_FLAG);
        } else {
            RbacService rbacService = SpringUtil.getBean(RbacService.class);
            perms.addAll(rbacService.getPermInCache(loginUser.getRoleIds(),loginUser.getAppCode()));
        }
        return perms;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        LoginUser loginUser = LoginUserHolder.me();
        if(CollectionUtil.isEmpty(loginUser.getRoleIds())) return new ArrayList<>();
        return loginUser.getRoleIds().stream().map(roleId->Convert.toStr(roleId)).collect(Collectors.toList());
    }
}
