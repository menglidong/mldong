package com.mldong.modules.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.Dict;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.sys.dto.MenuPageParam;
import com.mldong.modules.sys.dto.MenuParam;
import com.mldong.modules.sys.dto.SyncRouteParam;
import com.mldong.modules.sys.entity.Dept;
import com.mldong.modules.sys.entity.Menu;
import com.mldong.modules.sys.service.MenuService;
import com.mldong.modules.sys.vo.MenuVO;
import com.mldong.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
* <p>
    * 菜单 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-21
*/
@RestController
@Api(tags = "菜单管理")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    /**
     * 添加菜单
     * @param param
     * @return
     */
    @PostMapping("/sys/menu/save")
    @ApiOperation(value = "添加菜单")
    @SaCheckPermission("sys:menu:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) MenuParam param) {
        menuService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除菜单
     * @param param
     * @return
     */
    @PostMapping("/sys/menu/remove")
    @ApiOperation(value = "删除菜单")
    @SaCheckPermission("sys:menu:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        menuService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改菜单
     * @param param
     * @return
     */
    @PostMapping("/sys/menu/update")
    @ApiOperation(value = "修改菜单")
    @SaCheckPermission("sys:menu:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) MenuParam param) {
        menuService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个菜单
     * @param param
     * @return
     */
    @PostMapping("/sys/menu/detail")
    @ApiOperation(value = "查询单个菜单")
    @SaCheckPermission("sys:menu:detail")
    public CommonResult<MenuVO> detail(@RequestBody IdParam param) {
        MenuVO menu = menuService.findById(param.getId());
        return CommonResult.data(menu);
    }
    /**
     *分页查询菜单列表
     * @param param
     * @return
     */
    @PostMapping("/sys/menu/page")
    @ApiOperation(value = "分页查询菜单列表")
    @SaCheckPermission("sys:menu:page")
    public CommonResult<CommonPage<MenuVO>> page(@RequestBody MenuPageParam param) {
        return CommonResult.data(menuService.page(param));
    }
    /**
     *查询菜单列表
     * @param param
     * @return
     */
    @PostMapping("/sys/menu/list")
    @ApiOperation(value = "查询菜单列表")
    @SaCheckPermission("sys:menu:list")
    public CommonResult<List<Menu>> list(@RequestBody MenuPageParam param) {
        return CommonResult.data(menuService.list(param.buildQueryWrapper()));
    }

    /**
     * 同步前端路由
     * @param param
     * @return
     */
    @PostMapping("/sys/menu/syncRoute")
    @SaCheckPermission("sys:menu:syncRoute")
    public CommonResult<?> syncRoute(@RequestBody @Validated List<SyncRouteParam> param) {
        boolean isSuccess = menuService.syncRoute(param);
        if(isSuccess) {
            return CommonResult.ok("同步前端路由成功");
        } else {
            return CommonResult.fail("同步前端路由失败");
        }
    }
    @PostMapping("/sys/menu/appList")
    @ApiOperation(value="应用列表")
    public CommonResult<List<Dict>> appList() {
        return CommonResult.data(menuService.appList());
    }
    /**
     *查询菜单树
     * @param param
     * @return
     */
    @PostMapping("/sys/menu/tree")
    @ApiOperation(value = "查询菜单树")
    @SaCheckPermission("sys:menu:tree")
    public CommonResult<List<Dept>> tree(@RequestBody MenuPageParam param) {
        return CommonResult.data(menuService.tree(param));
    }
}
