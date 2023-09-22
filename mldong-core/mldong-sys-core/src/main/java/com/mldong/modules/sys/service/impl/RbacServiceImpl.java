package com.mldong.modules.sys.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.mldong.auth.RolePermCode;
import com.mldong.auth.RolePermCodeCache;
import com.mldong.base.CommonPage;
import com.mldong.modules.sys.dto.RoleMenuPageParam;
import com.mldong.modules.sys.dto.RoleMenuParam;
import com.mldong.modules.sys.dto.UserRolePageParam;
import com.mldong.modules.sys.dto.UserRoleParam;
import com.mldong.modules.sys.entity.*;
import com.mldong.modules.sys.enums.AdminTypeEnum;
import com.mldong.modules.sys.enums.MenuAppCodeEnum;
import com.mldong.modules.sys.mapper.*;
import com.mldong.modules.sys.service.RbacService;
import com.mldong.modules.sys.vo.MenuVO;
import com.mldong.modules.sys.vo.UserVO;
import com.mldong.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RbacServiceImpl implements RbacService {

    private final RoleMenuMapper roleMenuMapper;
    private final MenuMapper menuMapper;
    private final UserRoleMapper userRoleMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RolePermCodeCache rolePermCodeCache;
    /**
     * 添加r_角色菜单关系
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleMenu(List<RoleMenuParam> param) {
        if(CollectionUtil.isEmpty(param)) return true;
        Long roleId = param.get(0).getRoleId();
        // 删除缓存
        Arrays.stream(MenuAppCodeEnum.values()).forEach(item->{
            rolePermCodeCache.remove(roleId,item.toString().toLowerCase());
        });
        //删除所拥有菜单
        roleMenuMapper.delete(Wrappers.lambdaQuery(RoleMenu.class).eq(RoleMenu::getRoleId, roleId));
        param.stream().filter(item->{
            return ObjectUtil.isNotNull(item.getMenuId()) && ObjectUtil.isNotNull(item.getRoleId());
        }).forEach(item->{
            item.setId(null);
            RoleMenu roleMenu = new RoleMenu();
            BeanUtils.copyProperties(item,roleMenu);
            roleMenuMapper.insert(roleMenu);
        });
        // 异步再延时删除
        ThreadUtil.execAsync(()->{
            ThreadUtil.safeSleep(DateUnit.SECOND.getMillis()*5);
            Arrays.stream(MenuAppCodeEnum.values()).forEach(item->{
                rolePermCodeCache.remove(roleId,item.toString().toLowerCase());
            });
        });
        return true;
    }

    /**
     * 角色ID获取菜单ID集合
     * @param id
     * @return
     */
    @Override
    public List<Long> roleMenuIds(Long id) {
        List<RoleMenu> MenuIds =roleMenuMapper.selectList(Wrappers.lambdaQuery(RoleMenu.class).eq(RoleMenu::getRoleId,id));
        return MenuIds.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
    }

    /**
     * 获取角色菜单列表
     * @param param
     * @return
     */
    @Override
    public List<MenuVO> roleMenuList(RoleMenuPageParam param) {
        List<Long> MenuIds =roleMenuIds(param.getRoleId());
        List<MenuVO> vos = new ArrayList<>();
        if(ObjectUtil.isNotEmpty(MenuIds)) {
            List<Menu> menuList = menuMapper.selectList(Wrappers.lambdaQuery(Menu.class).in(Menu::getId, MenuIds));
            menuList.forEach(item -> {
                MenuVO vo = new MenuVO();
                BeanUtils.copyProperties(item, vo);
                vos.add(vo);
            });
        }
        return vos;
    }

    /**
     * 添加r_用户角色关系
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUserRole(List<UserRoleParam> param) {
        List<Long> userIds = new ArrayList<>();
        param.forEach(item->{
            item.setId(null);
            UserRole userRole = new UserRole();
            BeanUtils.copyProperties(item,userRole);
            long count = userRoleMapper.selectCount(
                    Wrappers.lambdaQuery(UserRole.class)
                    .eq(UserRole::getRoleId,item.getRoleId())
                    .eq(UserRole::getUserId,item.getUserId())
            );
            // 不存在则插入
            if(!SqlHelper.retBool(count)) {
                userRoleMapper.insert(userRole);
                userIds.add(item.getUserId());
            }
        });
        // TODO:刷新在线用户权限
        return true;
    }

    /**
     * 删除r_用户角色关系
     * @param param
     * @return
     */
    @Override
    public boolean removeUserRole(List<UserRoleParam> param) {
        if(CollectionUtil.isEmpty(param)) return true;
        List<Long> userIds = new ArrayList<>();
        Role role = null;
        for (int i = 0; i < param.size(); i++) {
            UserRoleParam item = param.get(i);
            userIds.add(item.getUserId());
            if(role == null || ObjectUtil.notEqual(item.getRoleId(),role.getId())) {
                role = roleMapper.selectById(item.getRoleId());
            }
            if(role!=null) {
                int count = userRoleMapper.delete(Wrappers.lambdaQuery(UserRole.class)
                        .eq(UserRole::getRoleId, item.getRoleId()).eq(UserRole::getUserId, item.getUserId()));
                if(count>0) {
                    // TODO: 触发删除用户角色事件
                }
            }
        }
        return true;
    }

    /**
     * 通过角色ID获取用户列表
     * @param param
     * @return
     */
    @Override
    public CommonPage<UserVO> userListByRoleId(UserRolePageParam param) {
        IPage<User> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        List<Long> UserRoleIds = userRoleMapper.selectList(Wrappers.lambdaQuery(UserRole.class).eq(UserRole::getRoleId, param.getRoleId())).stream().map(item->{
            return item.getUserId();
        }).collect(Collectors.toList());
        if(ObjectUtil.isNotEmpty(UserRoleIds)) {
            if(StrUtil.isNotEmpty(param.getKeywords())) {
                queryWrapper.like("real_name", param.getKeywords());
            }
            queryWrapper.in("id", UserRoleIds);
            queryWrapper.ne("admin_type", AdminTypeEnum.SUPER_ADMIN);
            userMapper.selectPage(page, queryWrapper);
        }
        return CommonPage.toPage(page,UserVO.class);
    }

    /**
     * 获取用户列表-排除指定角色
     * @param param
     * @return
     */
    @Override
    public CommonPage<UserVO> userListExcludeRoleId(UserRolePageParam param) {
        IPage<User> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        List<Long> UserRoleIds = userRoleMapper.selectList(Wrappers.lambdaQuery(UserRole.class).eq(UserRole::getRoleId, param.getRoleId())).stream().map(item->{
            return item.getUserId();
        }).collect(Collectors.toList());
        if(ObjectUtil.isNotEmpty(UserRoleIds)) {
            queryWrapper.notIn("id", UserRoleIds);
        }
        queryWrapper.ne("admin_type", AdminTypeEnum.SUPER_ADMIN);
        if(StrUtil.isNotEmpty(param.getKeywords())) {
            String keyWord = "%"+param.getKeywords()+"%";
            String applySql = "(real_name like {0} or user_name like {0})";
            queryWrapper.apply(applySql, keyWord);
        }
        userMapper.selectPage(page,queryWrapper);
        return CommonPage.toPage(page,UserVO.class);
    }

    @Override
    public RolePermCode getInCache(Long roleId, String appCode) {
        String key = appCode +"_"+ roleId;
        RolePermCode rolePermCode = rolePermCodeCache.get(key.toUpperCase());
        if(rolePermCode == null) {
            rolePermCode = new RolePermCode();
            rolePermCode.setRoleId(roleId);
            rolePermCode.setAppCode(appCode);
            rolePermCode.setPerms(new ArrayList<>());
            List<RoleMenu> roleMenuList = roleMenuMapper.selectList(Wrappers.lambdaQuery(RoleMenu.class).eq(RoleMenu::getRoleId,roleId));
            if (!roleMenuList.isEmpty()) {
                List<Menu> menuList = menuMapper.selectList(Wrappers.lambdaQuery(Menu.class)
                        .eq(Menu::getAppCode,  appCode)
                        .in(Menu::getId,roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList())));
                if(!menuList.isEmpty()) {
                    rolePermCode.getPerms().addAll(menuList.stream().map(item -> item.getCode()).collect(Collectors.toList()));
                }
            }
            rolePermCodeCache.put(key,rolePermCode);
        }
        return rolePermCode;
    }

    @Override
    public List<String> getPermInCache(List<Long> roleIds, String appCode) {
        List<String> perms = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(roleIds)) {
            roleIds.forEach(roleId->{
                RolePermCode rolePermCode = getInCache(roleId,appCode);
                perms.addAll(rolePermCode.getPerms());
            });
        };
        return perms;
    }
}
