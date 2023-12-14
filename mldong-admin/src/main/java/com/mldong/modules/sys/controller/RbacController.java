package com.mldong.modules.sys.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.modules.sys.dto.RoleMenuPageParam;
import com.mldong.modules.sys.dto.RoleMenuParam;
import com.mldong.modules.sys.dto.UserRolePageParam;
import com.mldong.modules.sys.dto.UserRoleParam;
import com.mldong.modules.sys.service.RbacService;
import com.mldong.modules.sys.vo.MenuVO;
import com.mldong.modules.sys.vo.UserVO;
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
 * 权限 前端控制器
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@RestController
@Api(tags="sys-RBAC管理")
@RequiredArgsConstructor
public class RbacController {
    private final RbacService rbacService;

    @PostMapping("/sys/rbac/saveRoleMenu")
    @ApiOperation(value="添加角色菜单")
    @SaCheckPermission("sys:rbac:saveRoleMenu")
    public CommonResult<?> saveRoleMenu(@RequestBody List<RoleMenuParam> param) {
        boolean isSuccess = rbacService.saveRoleMenu(param);
        if(isSuccess) {
            return CommonResult.ok("添加角色菜单成功");
        } else {
            return CommonResult.fail("添加角色菜单失败");
        }
    }
    @PostMapping("/sys/rbac/roleMenuIds")
    @ApiOperation(value="角色ID获取菜单ID集合")
    @SaCheckPermission(value = {"sys:rbac:roleMenuIds","sys:rbac:saveRoleMenu"}, mode = SaMode.OR)
    public CommonResult<List<Long>> roleMenuIds(@RequestBody RoleMenuParam param){
        return  CommonResult.data(rbacService.roleMenuIds(param.getRoleId()));
    }

    @PostMapping("/sys/rbac/roleMenuList")
    @ApiOperation(value="获取角色菜单列表")
    @SaCheckPermission("sys:rbac:roleMenuList")
    public CommonResult<List<MenuVO>> roleMenuList(@RequestBody @Validated RoleMenuPageParam param) {
        return CommonResult.data(rbacService.roleMenuList(param));
    }

    @PostMapping("/sys/rbac/saveUserRole")
    @ApiOperation(value="添加用户角色关系")
    @SaCheckPermission("sys:rbac:saveUserRole")
    public CommonResult<?> saveUserRole(@RequestBody List<UserRoleParam> param) {
        boolean isSuccess = rbacService.saveUserRole(param);
        if(isSuccess) {
            return CommonResult.ok("添加用户角色关系成功");
        } else {
            return CommonResult.fail("添加用户角色关系失败");
        }
    }
    @PostMapping("/sys/rbac/removeUserRole")
    @ApiOperation(value="删除用户角色关系")
    @SaCheckPermission("sys:rbac:removeUserRole")
    public CommonResult<?> removeUserRole(@RequestBody List<UserRoleParam> param) {
        boolean isSuccess = rbacService.removeUserRole(param);
        if(isSuccess) {
            return CommonResult.ok("删除用户角色关系成功");
        } else {
            return CommonResult.fail("删除用户角色关系失败");
        }
    }

    @PostMapping("/sys/rbac/userListByRoleId")
    @ApiOperation(value="通过角色ID获取用户列表")
    @SaCheckPermission("sys:rbac:userListByRoleId")
    public CommonResult<CommonPage<UserVO>> userListByRoleId(@RequestBody @Validated UserRolePageParam param) {
        return CommonResult.data(rbacService.userListByRoleId(param));
    }

    @PostMapping("/sys/rbac/userListExcludeRoleId")
    @ApiOperation(value="获取用户列表-排除指定角色", notes="sys:rbac:userListExcludeRoleId")
    @SaCheckPermission("sys:rbac:userListExcludeRoleId")
    public CommonResult<CommonPage<UserVO>> userListExcludeRoleId(@RequestBody @Validated UserRolePageParam param) {
        return CommonResult.data(rbacService.userListExcludeRoleId(param));
    }
}
