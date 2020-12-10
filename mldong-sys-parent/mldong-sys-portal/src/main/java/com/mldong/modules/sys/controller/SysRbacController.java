package com.mldong.modules.sys.controller;

import com.mldong.common.annotation.LoginUser;
import com.mldong.modules.sys.vo.RouterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.IdAndIdsParam;
import com.mldong.common.base.IdParam;
import com.mldong.modules.sys.dto.SysUserWithRoleIdPageParam;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.service.SysRbacService;
import com.mldong.modules.sys.vo.SysAccessTreeVo;
import com.mldong.modules.sys.vo.SysMenuTreeVo;

import java.util.List;

@RestController
@RequestMapping("/sys/rbac")
@Api(tags="sys-权限管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="权限管理",scope="sys:rbac:index")
    })
})
public class SysRbacController {
	@Autowired
	private SysRbacService sysRbacService;

	@PostMapping("listAccessTree")
	@ApiOperation(value="获取权限资源树", notes="获取权限资源树",authorizations={
		@Authorization(value="获取权限资源树",scopes={
	    	@AuthorizationScope(description="获取权限资源树",scope="sys:rbac:listAccessTree")
	    })
	})
	public CommonResult<SysAccessTreeVo> listAccessTree(@RequestBody @Validated IdParam param, @LoginUser Long userId) {
		return CommonResult.success("获取权限资源树",sysRbacService.listAccessTree(userId, param.getId()));
	}
	@PostMapping("listMenuByRoleId")
	@ApiOperation(value="通过角色id获取菜单", notes="通过角色id获取菜单",authorizations={
		@Authorization(value="通过角色id获取菜单",scopes={
	    	@AuthorizationScope(description="通过角色id获取菜单",scope="sys:rbac:listMenuByRoleId")
	    })
	})
	public CommonResult<SysMenuTreeVo> listMenuByRoleId(@RequestBody @Validated IdParam param, @LoginUser Long userId) {
		return CommonResult.success("获取权限资源树",sysRbacService.listMenuByRoleId(userId, param.getId()));
	}
	@PostMapping("listUserByRoleId")
	@ApiOperation(value="角色成员列表", notes="角色成员列表",authorizations={
		@Authorization(value="角色成员列表",scopes={
	    	@AuthorizationScope(description="角色成员列表",scope="sys:rbac:listUserByRoleId")
	    })
	})
	public CommonResult<CommonPage<SysUser>> listUserByRoleId(@RequestBody @Validated SysUserWithRoleIdPageParam param) {
		return CommonResult.success("角色成员列表",sysRbacService.listUserByRoleId(param));
	}

	@PostMapping("listUserNoInRole")
	@ApiOperation(value="查询未加入指定角色的用户列表", notes="查询未加入指定角色的用户列表",authorizations={
		@Authorization(value="查询未加入指定角色的用户列表",scopes={
	    	@AuthorizationScope(description="查询未加入指定角色的用户列表",scope="sys:rbac:listUserNoInRole")
	    })
	})
	public CommonResult<CommonPage<SysUser>> listUserNoInRole(@RequestBody @Validated SysUserWithRoleIdPageParam param) {
		return CommonResult.success("查询未加入指定角色的用户列表",sysRbacService.listUserNoInRole(param));
	}

	@PostMapping("saveUserRole")
	@ApiOperation(value="保存用户角色关系", notes="保存用户角色关系",authorizations={
		@Authorization(value="保存用户角色关系",scopes={
	    	@AuthorizationScope(description="保存用户角色关系",scope="sys:rbac:saveUserRole")
	    })
	})
	public CommonResult<?> saveUserRole(@RequestBody @Validated IdAndIdsParam param) {
		int count = sysRbacService.saveUserRole(param);
		if(count>0) {
			return CommonResult.success("保存用户角色关系成功", null);
		} else {
			return CommonResult.fail("保存用户角色关系失败", null);
		}
	}

	@PostMapping("deleteUserRole")
	@ApiOperation(value="从角色中移除用户", notes="从角色中移除用户",authorizations={
		@Authorization(value="从角色中移除用户",scopes={
	    	@AuthorizationScope(description="从角色中移除用户",scope="sys:rbac:deleteUserRole")
	    })
	})
	public CommonResult<?> deleteUserRole(@RequestBody @Validated IdAndIdsParam param) {
		int count = sysRbacService.deleteUserRole(param);
		if(count>0) {
			return CommonResult.success("从角色中移除用户成功", null);
		} else {
			return CommonResult.fail("从角色中移除用户失败", null);
		}
	}
	@PostMapping("saveRoleAccess")
	@ApiOperation(value="保存角色权限资源关系", notes="保存角色权限资源关系",authorizations={
		@Authorization(value="保存角色权限资源关系",scopes={
	    	@AuthorizationScope(description="保存角色权限资源关系",scope="sys:rbac:saveRoleAccess")
	    })
	})
	public CommonResult<?> saveRoleAccess(@RequestBody @Validated IdAndIdsParam param) {
		int count = sysRbacService.saveRoleAccess(param);
		if(count>0) {
			return CommonResult.success("保存角色权限资源关系成功", null);
		} else {
			return CommonResult.fail("保存角色权限资源关系失败", null);
		}
	}
	@PostMapping("saveRoleMenu")
	@ApiOperation(value="保存角色菜单关系", notes="保存角色菜单关系",authorizations={
		@Authorization(value="保存角色菜单关系",scopes={
	    	@AuthorizationScope(description="保存角色菜单关系",scope="sys:rbac:saveRoleMenu")
	    })
	})
	public CommonResult<?> saveRoleMenu(@RequestBody @Validated IdAndIdsParam param) {
		int count = sysRbacService.saveRoleMenu(param);
		if(count>0) {
			return CommonResult.success("保存角色菜单关系成功", null);
		} else {
			return CommonResult.fail("保存角色菜单关系失败", null);
		}
	}
	/**
	 * 获取当前用户路由列表
	 * @return
	 */
	@PostMapping("getRouters")
	@ApiOperation(value="获取当前用户路由列表", notes="获取当前用户路由列表")
	public CommonResult<List<RouterVo>> getRouters(@LoginUser Long userId) {
		return CommonResult.success(sysRbacService.getRouters(userId));
	}
	/**
	 * 获取当前用户接口清单
	 * @return
	 */
	@PostMapping("listApiList")
	@ApiOperation(value="获取当前用户接口清单", notes="获取当前用户接口清单")
	public CommonResult<SysAccessTreeVo> listApiList(@LoginUser Long userId) {
		return CommonResult.success(sysRbacService.listAccessTree(userId, null));
	}
}
