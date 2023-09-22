package com.mldong.modules.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.consts.CommonConstant;
import com.mldong.modules.sys.dto.MenuPageParam;
import com.mldong.modules.sys.dto.MenuParam;
import com.mldong.modules.sys.dto.SyncRouteParam;
import com.mldong.modules.sys.entity.Menu;
import com.mldong.modules.sys.entity.RoleMenu;
import com.mldong.modules.sys.enums.MenuAppCodeEnum;
import com.mldong.modules.sys.enums.MenuOpenTypeEnum;
import com.mldong.modules.sys.mapper.MenuMapper;
import com.mldong.modules.sys.mapper.RoleMenuMapper;
import com.mldong.modules.sys.service.MenuService;
import com.mldong.modules.sys.vo.MenuVO;
import com.mldong.tree.TreeTool;
import com.mldong.web.LoginUserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    private final RoleMenuMapper roleMenuMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(MenuParam param) {
        param.setId(null);
        Menu menu = new Menu();
        BeanUtil.copyProperties(param, menu);
        return super.save(menu);
    }

    @Override
    public boolean update(MenuParam param) {
        Menu menu = new Menu();
        BeanUtil.copyProperties(param, menu);
        return super.updateById(menu);
    }

    @Override
    public CommonPage<MenuVO> page(MenuPageParam param) {
        IPage<MenuVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<MenuVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public MenuVO findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public List<MenuVO> tree(MenuPageParam param) {
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.orderByAsc("sort");
        List<Menu> list = this.list(queryWrapper);
        List<MenuVO> treeData = TreeTool.listToTree(BeanUtil.copyToList(list,MenuVO.class),0L,MenuVO.class);
        return treeData;
    }

    /**
     * 同步前端路由
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean syncRoute(List<SyncRouteParam> param) {
        List<Long> menuIds = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(param)) {
            // 一级菜单
            param.forEach(route -> {
                if (YesNoEnum.YES.getCode().equals(route.getIsSync())) {
                    Long menuId = saveOrUpdateRoute(route, 0L);
                    menuIds.add(menuId);
                    if (CollectionUtil.isNotEmpty(route.getChildren())) {
                        // 二级菜单
                        route.getChildren().forEach(route2 -> {
                            if(YesNoEnum.YES.getCode().equals(route2.getIsSync())) {
                                Long menuId2 = saveOrUpdateRoute(route2, menuId);
                                menuIds.add(menuId2);
                                // 三级菜单
                                if (CollectionUtil.isNotEmpty(route2.getChildren())) {
                                    route2.getChildren().forEach(route3 -> {
                                        if(YesNoEnum.YES.getCode().equals(route3.getIsSync())) {
                                            Long menuId3 = saveOrUpdateRoute(route3, menuId2);
                                            menuIds.add(menuId3);
                                            // 四级菜单--最多四级
                                            if (CollectionUtil.isNotEmpty(route3.getChildren())) {
                                                route3.getChildren().forEach(route4 -> {
                                                    if(YesNoEnum.YES.getCode().equals(route4.getIsSync())) {
                                                        menuIds.add(saveOrUpdateRoute(route4, menuId3));
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        }
        // 删除前端同步菜单、当前应用、不在当前同步菜单内的路由菜单
        LambdaQueryWrapper<Menu> lambdaQueryWrapper = Wrappers.lambdaQuery(Menu.class)
                .select(Menu::getId)
                .eq(Menu::getIsSync,1)
                .eq(Menu::getAppCode, LoginUserHolder.me().getAppCode());
        if(CollectionUtil.isNotEmpty(menuIds)) {
            lambdaQueryWrapper.notIn(Menu::getId, menuIds);
        }
        List<Menu> sysMenus  = baseMapper.selectList(lambdaQueryWrapper);
        if(!sysMenus.isEmpty()) {
            List<Long> deleteMenuIds = sysMenus.stream().map(item->{return item.getId();}).collect(Collectors.toList());
            // 删除冗余菜单
            baseMapper.deleteBatchIds(deleteMenuIds);
            // 清除角色菜单关联表
            roleMenuMapper.delete(Wrappers.lambdaQuery(RoleMenu.class).in(RoleMenu::getMenuId, deleteMenuIds));
        }
        return true;
    }

    @Override
    public List<Dict> appList() {
        List<Dict> dictList = new ArrayList<>();
        Arrays.stream(MenuAppCodeEnum.values()).forEach(item->{
            Dict dict = new Dict();
            dict.put(CommonConstant.VALUE, item.toString().toLowerCase());
            dict.put(CommonConstant.LABEL, item.getMessage());
            dictList.add(dict);
        });
        return dictList;
    }

    /**
     * 插入或更新路由
     * @param route
     * @param pid
     */
    private Long saveOrUpdateRoute(SyncRouteParam route, Long pid) {
        Menu menu = BeanUtil.copyProperties(route, Menu.class,"children");
        menu.setCode(route.getCode());
        menu.setAppCode(LoginUserHolder.me().getAppCode());
        menu.setParentId(pid);
        if(menu.getOpenType() == null) {
            menu.setOpenType(MenuOpenTypeEnum.NONE.getCode());
        }
        // 设置新的pid
        String newPids = createNewPids(pid);
        menu.setPids(newPids);
        Map<String,Object> map = new HashMap<>();
        map.put("variable",route.getVariable());
        menu.setVariable(JSONUtil.toJsonStr(map));
        Menu oldMenu = getMenu(route.getCode(), pid);
        if(oldMenu!=null) {
            // 存在则更新
            menu.setId(oldMenu.getId());
            baseMapper.updateById(menu);
        } else {
            // 不存在则插入
            baseMapper.insert(menu);
        }
        return menu.getId();
    }

    /**
     * 创建pids的值
     * <p>
     * 如果pid是0顶级节点，pids就是 [0],
     * <p>
     * 如果pid不是顶级节点，pids就是 pid菜单的pids + [pid] + ,
     *
     */
    private String createNewPids(Long pid) {
        if (pid.equals(0L)) {
            return "[" + 0 + "]" +",";
        } else {
            //获取父菜单
            Menu parentMenu = this.getById(pid);
            return parentMenu.getPids() + "[" + pid + "]" + ",";
        }
    }

    /**
     * 通过唯一编码和应用名称获取菜单
     * @param code 唯一编码
     * @param pid
     * @return
     */
    private Menu getMenu(String code, Long pid) {
        return baseMapper.selectOne(Wrappers.lambdaQuery(Menu.class)
                .eq(Menu::getCode, code)
                .eq(Menu::getAppCode, LoginUserHolder.me().getAppCode())
                .eq(Menu::getParentId, pid));
    }
}
