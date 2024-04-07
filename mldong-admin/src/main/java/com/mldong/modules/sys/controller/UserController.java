package com.mldong.modules.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.mldong.auth.LoginUser;
import com.mldong.base.*;
import com.mldong.modules.sys.dto.UserPageParam;
import com.mldong.modules.sys.dto.UserParam;
import com.mldong.modules.sys.dto.UserRoleParam;
import com.mldong.modules.sys.service.UserService;
import com.mldong.modules.sys.vo.DeptUserTreeVO;
import com.mldong.modules.sys.vo.UserVO;
import com.mldong.validation.Groups;
import com.mldong.web.LoginUserHolder;
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
    * 用户 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-20
*/
@RestController
@Api(tags = "用户管理")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    /**
     * 添加用户
     * @param param
     * @return
     */
    @PostMapping("/sys/user/save")
    @ApiOperation(value = "添加用户")
    @SaCheckPermission("sys:user:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) UserParam param) {
        userService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除用户
     * @param param
     * @return
     */
    @PostMapping("/sys/user/remove")
    @ApiOperation(value = "删除用户")
    @SaCheckPermission("sys:user:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        userService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改用户
     * @param param
     * @return
     */
    @PostMapping("/sys/user/update")
    @ApiOperation(value = "修改用户")
    @SaCheckPermission("sys:user:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) UserParam param) {
        userService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个用户
     * @param param
     * @return
     */
    @PostMapping("/sys/user/detail")
    @ApiOperation(value = "查询单个用户")
    @SaCheckPermission("sys:user:detail")
    public CommonResult<UserVO> detail(@RequestBody IdParam param) {
        UserVO user = userService.findById(param.getId());
        return CommonResult.data(user);
    }
    /**
     *分页查询用户列表
     * @param param
     * @return
     */
    @PostMapping("/sys/user/page")
    @ApiOperation(value = "分页查询用户列表")
    @SaCheckPermission("sys:user:page")
    public CommonResult<CommonPage<UserVO>> page(@RequestBody UserPageParam param) {
        return CommonResult.data(userService.page(param));
    }
    @PostMapping("/sys/user/permCode")
    @ApiOperation(value="个人-权限标识")
    public CommonResult<List<String>> permCode() {
        return CommonResult.data(StpUtil.getPermissionList());
    }
    @PostMapping("/sys/user/resetPassword")
    @SaCheckPermission("sys:user:resetPassword")
    public CommonResult<?> resetPwd(@RequestBody IdsParam param) {
        boolean isSuccess = userService.resetPwd(param.getIds());
        if(isSuccess) {
            return CommonResult.ok("重置用户密码成功");
        } else {
            return CommonResult.fail("重置用户密码失败");
        }
    }
    @PostMapping("/sys/user/locked")
    @SaCheckPermission(value = {"sys:user:locked","sys:user:unLocked"},mode = SaMode.OR)
    public CommonResult<?> locked(@RequestBody IdsParam param) {
        boolean isSuccess = userService.locked(param.getIds());
        if(isSuccess) {
            return CommonResult.ok("锁定用户成功");
        } else {
            return CommonResult.fail("锁定用户失败");
        }
    }
    @PostMapping("/sys/user/unLocked")
    @SaCheckPermission(value = {"sys:user:locked","sys:user:unLocked"},mode = SaMode.OR)
    public CommonResult<?> unLocked(@RequestBody IdsParam param) {
        boolean isSuccess = userService.unLocked(param.getIds());
        if(isSuccess) {
            return CommonResult.ok("取消锁定用户成功");
        } else {
            return CommonResult.fail("取消锁定用户失败");
        }
    }
    @PostMapping("/sys/user/select")
    @ApiOperation(value="用户下拉列表", notes="sys:user:select")
    public CommonResult<List<LabelValueVO>> select(@RequestBody @Validated UserPageParam param) {
        return CommonResult.data(userService.select(param));
    }


    @PostMapping("/sys/user/updateInfo")
    @ApiOperation(value="修改个人信息", notes="sys:user:updateInfo")
    public CommonResult<?> updateInfo(@RequestBody @Validated UserParam param) {
        boolean isSuccess = userService.updateInfo(param);
        if(isSuccess) {
            return CommonResult.ok("修改个人信息成功");
        } else {
            return CommonResult.fail("修改个人信息失败");
        }
    }


    @PostMapping("/sys/user/info")
    @ApiOperation(value="获取用户个人信息", notes="sys:user:info")
    public CommonResult<LoginUser> info() {
        return CommonResult.data(userService.info());
    }

    @PostMapping("/sys/user/updatePwd")
    @ApiOperation(value="修改用户个人密码", notes="sys:user:updatePwd")
    public CommonResult<?> updatePwd(@RequestBody @Validated({UserParam.UpdatePwd.class}) UserParam param) {
        param.setId(LoginUserHolder.me().getId());
        if(userService.updatePwd(param)) {
            return CommonResult.ok("修改用户个人密码成功");
        } else {
            return CommonResult.fail("修改用户个人密码失败");
        }
    }
    @PostMapping("/sys/user/getDeptUserTree")
    @ApiOperation(value="获取部门用户树", notes="sys:user:getDeptUserTree")
    public CommonResult<List<DeptUserTreeVO>> getDeptUserTree() {
        return CommonResult.data(userService.getDeptUserTree());
    }
    /**
     * 授权角色
     * @param param
     * @return
     */
    @PostMapping("/sys/user/grantRole")
    @ApiOperation(value = "授权角色")
    @SaCheckPermission("sys:user:grantRole")
    public CommonResult<?> grantRole(@RequestBody @Validated({UserRoleParam.GrantRole.class}) UserRoleParam param) {
        userService.grantRole(param.getUserId(),param.getRoleIdList());
        return CommonResult.ok();
    }
    /**
     * 获取在线用户列表
     * @param param
     * @return
     */
    @PostMapping("/sys/user/onlineUserList")
    @ApiOperation(value = "获取在线用户列表")
    @SaCheckPermission("sys:user:onlineUserList")
    public CommonResult<List<LoginUser>> onlineUserList(@RequestBody @Validated UserPageParam param) {
        return CommonResult.data(userService.onlineUserList(param));
    }
    /**
     * 根据token值强制注销
     * @param param
     * @return
     */
    @PostMapping("/sys/user/logoutByTokenValue")
    @ApiOperation(value = "根据token值强制注销")
    @SaCheckPermission("sys:user:logoutByTokenValue")
    public CommonResult<?> logoutByTokenValue(@RequestBody IdsStrParam param) {
        for (String str : param.getIds()) {
            userService.logoutByTokenValue(str);
        }
        return CommonResult.ok();
    }
    /**
     * 根据token值踢下线
     * @param param
     * @return
     */
    @PostMapping("/sys/user/kickoutByTokenValue")
    @ApiOperation(value = "根据token值踢下线")
    @SaCheckPermission("sys:user:kickoutByTokenValue")
    public CommonResult<?> kickoutByTokenValue(@RequestBody IdsStrParam param) {
        for (String str : param.getIds()) {
            userService.kickoutByTokenValue(str);
        }
        return CommonResult.ok();
    }
    /**
     * 根据登录ID强制注销
     * @param param
     * @return
     */
    @PostMapping("/sys/user/logoutByLoginId")
    @ApiOperation(value = "根据登录ID强制注销")
    @SaCheckPermission("sys:user:logoutByLoginId")
    public CommonResult<?> logoutByLoginId(@RequestBody IdsParam param) {
        for (Long id : param.getIds()) {
            userService.logoutByLoginId(id);
        }
        return CommonResult.ok();
    }
    /**
     * 根据登录ID踢下线
     * @param param
     * @return
     */
    @PostMapping("/sys/user/kickoutByLoginId")
    @ApiOperation(value = "根据登录ID踢下线")
    @SaCheckPermission("sys:user:kickoutByLoginId")
    public CommonResult<?> kickoutByLoginId(@RequestBody IdsParam param) {
        for (Long id : param.getIds()) {
            userService.kickoutByLoginId(id);
        }
        return CommonResult.ok();
    }
}
