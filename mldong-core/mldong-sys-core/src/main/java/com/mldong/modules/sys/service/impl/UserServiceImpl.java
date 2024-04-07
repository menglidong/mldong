package com.mldong.modules.sys.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.TokenSign;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mldong.auth.LoginUser;
import com.mldong.base.CommonPage;
import com.mldong.base.LabelValueVO;
import com.mldong.base.YesNoEnum;
import com.mldong.consts.CommonConstant;
import com.mldong.context.constant.ConstantContextHolder;
import com.mldong.exception.ServiceException;
import com.mldong.modules.sys.dto.UserPageParam;
import com.mldong.modules.sys.dto.UserParam;
import com.mldong.modules.sys.entity.Dept;
import com.mldong.modules.sys.entity.User;
import com.mldong.modules.sys.entity.UserRole;
import com.mldong.modules.sys.enums.AdminTypeEnum;
import com.mldong.modules.sys.enums.SexEnum;
import com.mldong.modules.sys.enums.err.SysErrEnum;
import com.mldong.modules.sys.mapper.UserMapper;
import com.mldong.modules.sys.service.DeptService;
import com.mldong.modules.sys.service.PostService;
import com.mldong.modules.sys.service.UserRoleService;
import com.mldong.modules.sys.service.UserService;
import com.mldong.modules.sys.vo.*;
import com.mldong.tree.IRecursionTree;
import com.mldong.tree.TreeTool;
import com.mldong.util.LowCodeServiceUtil;
import com.mldong.web.LoginUserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final DeptService deptService;
    private final PostService postService;
    private final UserRoleService userRoleService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(UserParam param) {
        param.setId(null);
        LowCodeServiceUtil.checkUnique(baseMapper,"userName",param.getUserName(),null,"用户名已存在，请检查userName参数");
        LowCodeServiceUtil.checkUnique(baseMapper,"mobilePhone",param.getMobilePhone(),null,"手机号已存在，请检查mobilePhone参数");
        User user = new User();
        BeanUtils.copyProperties(param,user);
        user.setSalt(RandomUtil.randomString(8));
        String md5Password = SecureUtil.md5(param.getPassword()+user.getSalt());
        user.setPassword(md5Password);
        user.setAdminType(AdminTypeEnum.COMMON_ADMIN.getCode());
        boolean success = super.save(user);
        if(success) {
            param.setId(user.getId());
        }
        return success;
    }

    @Override
    public boolean update(UserParam param) {
        LowCodeServiceUtil.checkUnique(baseMapper,"userName",param.getUserName(),param.getId(),"用户名已存在，请检查userName参数");
        LowCodeServiceUtil.checkUnique(baseMapper,"mobilePhone",param.getMobilePhone(),param.getId(),"手机号已存在，请检查mobilePhone参数");
        User user = new User();
        BeanUtils.copyProperties(param,user,"password","salt");
        boolean success = super.updateById(user);
        if(success && YesNoEnum.YES.getCode().equals(param.getIsLocked())) {
            // 如果用户锁定，则将用户T下线
            StpUtil.kickout(param.getId());
        }
        return success;
    }

    @Override
    public CommonPage<UserVO> page(UserPageParam param) {
        IPage<UserVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        // 超级管理员不可见
        queryWrapper.ne("t.admin_type",AdminTypeEnum.SUPER_ADMIN);
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<UserVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public UserVO findById(Long id) {
        UserVO vo = baseMapper.findById(id);
        if(vo!=null) {
            if(vo.getDeptId()!=null) {
                DeptVO deptVO = deptService.getInCache(vo.getDeptId());
                if (deptVO!=null){
                    vo.setDeptName(deptVO.getName());
                }
            }
            if(vo.getPostId()!=null) {
                PostVO postVO = postService.getInCache(vo.getPostId());
                if (postVO!=null){
                    vo.setPostName(postVO.getName());
                }
            }
        }
        return vo;
    }

    /**
     * 重置用户密码
     * @param ids
     * @return
     */
    @Override
    public boolean resetPwd(List<Long> ids) {
        List<User> userList = baseMapper.selectList(Wrappers.lambdaQuery(User.class).in(User::getId,ids));
        userList.stream().filter(item->{
            // 超级管理员不允许重置密码
            return !AdminTypeEnum.SUPER_ADMIN.equals(item.getAdminType());
        }).forEach(item->{
            User user = new User();
            user.setSalt(RandomUtil.randomString(8));
            String md5Password = SecureUtil.md5(ConstantContextHolder.getDefaultPassword() +user.getSalt());
            user.setPassword(md5Password);
            user.setId(item.getId());
            super.updateById(user);
        });
        return true;
    }

    /**
     * 用户下拉列表
     * @param param
     * @return
     */
    @Override
    public List<LabelValueVO> select(UserPageParam param) {
        return LowCodeServiceUtil.select(baseMapper, param, "realName", "id", new LowCodeServiceUtil.MConsumer<QueryWrapper<User>>() {
            @Override
            public boolean has() {
                return ObjectUtil.equal(param.getIncludeType(),1) || StrUtil.isNotEmpty(param.getKeywords());
            }

            @Override
            public void accept(QueryWrapper<User> userQueryWrapper) {
                // 超级管理员不可见
                if(ObjectUtil.equal(param.getIncludeType(),1)) {
                    userQueryWrapper.ne("admin_type", AdminTypeEnum.SUPER_ADMIN);
                }
                if(StrUtil.isNotEmpty(param.getKeywords())){
                    userQueryWrapper.like("real_name",param.getKeywords());
                }
            }
        });
    }

    /**
     * 修改个人信息
     * @param param
     * @return
     */
    @Override
    public boolean updateInfo(UserParam param) {
        param.setPassword(null);
        param.setSalt(null);
        checkPhoneParam(param, true);
        User user = new User();
        BeanUtil.copyProperties(param, user);
        user.setId(Convert.toLong(StpUtil.getLoginId()));
        return this.updateById(user);
    }

    /**
     * 获取用户个人信息
     * @return
     */
    @Override
    public LoginUser info() {
        LoginUser loginUser = LoginUserHolder.me();
        return loginUser;
    }

    @Override
    public boolean updatePwd(UserParam param) {
        // 新密码与原密码相同
        if(ObjectUtil.equals(param.getPassword(),param.getNewPassword())) {
            ServiceException.throwBiz(SysErrEnum.USER_PWD_REPEAT);
        }
        User user = baseMapper.selectById(param.getId());
        if(ObjectUtil.isNull(user)) {
            ServiceException.throwBiz(SysErrEnum.USER_NOT_EXIST);
        }
        // 旧密码正常，才能修改
        if(!user.getPassword().equals(SecureUtil.md5(param.getPassword()+user.getSalt()))) {
            ServiceException.throwBiz(SysErrEnum.PWS_ERROR);
        }
        User up = new User();
        up.setId(user.getId());
        up.setSalt(RandomUtil.randomString(8));
        String md5Password = SecureUtil.md5(param.getNewPassword()+user.getSalt());
        user.setPassword(md5Password);
        return super.updateById(user);
    }

    @Override
    public boolean locked(List<Long> ids) {
        List<User> userList = baseMapper.selectList(Wrappers.lambdaQuery(User.class).in(User::getId,ids));
        userList.stream().filter(item->{
            // 超级管理员不允许锁定
            return !AdminTypeEnum.SUPER_ADMIN.equals(item.getAdminType());
        }).forEach(item->{
            User user = new User();
            user.setIsLocked(YesNoEnum.YES.getCode());
            user.setId(item.getId());
            super.updateById(user);
        });
        return true;
    }

    @Override
    public boolean unLocked(List<Long> ids) {
        List<User> userList = baseMapper.selectList(Wrappers.lambdaQuery(User.class).in(User::getId,ids));
        userList.stream().filter(item->{
            // 超级管理员不允许锁定
            return !AdminTypeEnum.SUPER_ADMIN.equals(item.getAdminType());
        }).forEach(item->{
            User user = new User();
            user.setIsLocked(YesNoEnum.NO.getCode());
            user.setId(item.getId());
            super.updateById(user);
        });
        return true;
    }

    @Override
    public User getByPhone(String phone) {
        List<User> userList = baseMapper.selectList(
                Wrappers.lambdaQuery(User.class)
                        .eq(User::getMobilePhone,phone)
                        .eq(User::getIsLocked, YesNoEnum.NO)
        );
        if(CollectionUtil.isNotEmpty(userList)) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public User createUserByPhone(String phone) {
        UserParam param = new UserParam();
        param.setMobilePhone(phone);
        param.setUserName(phone);
        param.setPassword(ConstantContextHolder.getDefaultPassword());
        param.setRealName(phone);
        param.setSex(SexEnum.UN_KNOW.getCode());
        save(param);
        return baseMapper.selectById(param.getId());
    }

    @Override
    public List<DeptUserTreeVO> getDeptUserTree() {
        List<Dept> deptList = deptService.list();
        List<Dept> deptTree = TreeTool.listToTree(deptList,0L,Dept.class);
        List<DeptUserTreeVO> res = new ArrayList<>();
        TreeTool.recursion(deptTree, new IRecursionTree<Dept, List<DeptUserTreeVO>>() {
            @Override
            public void rowHandleBefore(Dept dept, List<DeptUserTreeVO> res) {
                    DeptUserTreeVO vo = new DeptUserTreeVO();
                    vo.setLabel(dept.getName());
                    vo.setValue(dept.getId());
                    vo.setNodeType("1");
                    // 部门不可选择
                    vo.setDisabled(true);
                    vo.setExt(Dict.create());
                    // 追加部门用户
                    vo.setChildren(listByDeptId(dept.getId()).stream().map(user->{
                        DeptUserTreeVO uVo = new DeptUserTreeVO();
                        uVo.setLabel(user.getRealName());
                        uVo.setValue(user.getId());
                        uVo.setNodeType("2");
                        // 超级管理员不可选择
                        // uVo.setDisabled(AdminTypeEnum.SUPER_ADMIN.getCode().equals(user.getAdminType()));
                        uVo.setExt(Dict.create());
                        return uVo;
                    }).collect(Collectors.toList()));
                    res.add(vo);

            }

            @Override
            public void rowHandleAfter(Dept dept, List<DeptUserTreeVO> res) {

            }
        },res);
        return res;
    }

    @Override
    public List<User> listByDeptId(Long deptId) {
        return baseMapper.selectList(Wrappers.lambdaQuery(User.class).eq(User::getDeptId,deptId));
    }

    @Override
    public void grantRole(Long userId, List<Long> roleIds) {
        // 删除用户所有角色关系
        userRoleService.remove(Wrappers.lambdaQuery(UserRole.class).eq(UserRole::getUserId,userId));
        if(CollectionUtil.isEmpty(roleIds)) return;
        List<UserRole> userRoleList = roleIds.stream().map(roleId->{
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            return userRole;
        }).collect(Collectors.toList());
        // 重新插入关系
        userRoleService.saveBatch(userRoleList);
    }

    @Override
    public List<OnlineUserVO> onlineUserList(UserPageParam param) {
        List<OnlineUserVO> res = new ArrayList<>();
        // 分页查询数据
        List<String> sessionIdList = StpUtil.searchSessionId(Convert.toStr(param.getKeywords(),""), 0, -1, false);
        for (String sessionId: sessionIdList) {
            SaSession session = StpUtil.getSessionBySessionId(sessionId);
            OnlineUserVO item = new OnlineUserVO();
            item.setId(Convert.toLong(session.getLoginId()));
            item.setTimeout(session.getTimeout());
            item.setIsCurrentUser(ObjectUtil.equals(session.getLoginId(), StpUtil.getLoginId()));
            item.setLoginTime(DateUtil.date(session.getCreateTime()));
            item.setExpireTime(DateUtil.date().offset(DateField.SECOND,Convert.toInt(session.getTimeout())));
            List<TokenSign> tokenSignList = session.getTokenSignList();
            CollectionUtil.reverse(tokenSignList);
            // 创建集合
            List<OnlineUserVO> loginUserList = new ArrayList<>();
            tokenSignList.forEach(tokenSign -> {
                Object jsonObj = StpUtil.getExtra(tokenSign.getValue(), CommonConstant.LOGIN_USER_KEY);
                OnlineUserVO onlineUser = BeanUtil.toBean(jsonObj,OnlineUserVO.class);
                item.setUserName(onlineUser.getUserName());
                item.setRealName(onlineUser.getRealName());
                item.setSuperAdmin(onlineUser.isSuperAdmin());
                if(onlineUser.getLoginTimestamp()!=null) {
                    onlineUser.setLoginTime(new Date(onlineUser.getLoginTimestamp()));
                }
                if(!onlineUser.isSuperAdmin()) {
                    // 超级管理员不能暴露token值
                    onlineUser.setTokenValue(tokenSign.getValue());
                } else {
                    onlineUser.setTokenValue("-");
                }
                onlineUser.setExpireTime(DateUtil.date().offset(DateField.SECOND,Convert.toInt(session.getTimeout())));
                onlineUser.setDevice(tokenSign.getDevice());
                onlineUser.setIsCurrentUser(tokenSign.getValue().equals(StpUtil.getTokenValue()));
                loginUserList.add(onlineUser);
            });
            item.setTokenList(loginUserList);
            res.add(item);
        }
        return res;
    }

    @Override
    public void logoutByTokenValue(String tokenValue) {
        StpUtil.logoutByTokenValue(tokenValue);
    }

    @Override
    public void kickoutByTokenValue(String tokenValue) {
        StpUtil.kickoutByTokenValue(tokenValue);
    }

    @Override
    public void logoutByLoginId(Long loginId) {
        StpUtil.logout(loginId);
    }

    @Override
    public void kickoutByLoginId(Long loginId) {
        StpUtil.kickout(loginId);
    }

    /**
     * 校验参数，检查是否存在相同的账号
     */
    private void checkPhoneParam(UserParam param, boolean isExcludeSelf) {
        Long id = param.getId();
        String phone = param.getMobilePhone();
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
        queryWrapper.eq(User::getMobilePhone, phone)
                .ne(User::getIsDeleted, YesNoEnum.NO.getCode());
        //是否排除自己，如果是则查询条件排除自己id
        if (isExcludeSelf) {
            queryWrapper.ne(User::getId, id);
        }
        long countByPhone = baseMapper.selectCount(queryWrapper);
        //大于等于1个则表示重复
        if (countByPhone >= 1) {
            throw new ServiceException(SysErrEnum.USER_PHONE_REPEAT);
        }
    }
}
