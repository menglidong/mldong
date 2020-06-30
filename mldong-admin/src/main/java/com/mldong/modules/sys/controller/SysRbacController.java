package com.mldong.modules.sys.controller;

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
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.sys.dto.SysUserWithRoleIdPageParam;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.service.SysRbacService;
import com.mldong.modules.sys.vo.SysAccessTreeVo;
import com.mldong.modules.sys.vo.SysMenuTreeVo;

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
	    	@AuthorizationScope(description="获取权限资源树",scope="sys:role:listAccessTree")
	    })
	})
	public CommonResult<SysAccessTreeVo> listAccessTree(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取权限资源树",sysRbacService.listAccessTree(RequestHolder.getUserId(), param.getId()));
	}
	@PostMapping("listMenuByRoleId")
	@ApiOperation(value="通过角色id获取菜单", notes="通过角色id获取菜单",authorizations={
		@Authorization(value="通过角色id获取菜单",scopes={
	    	@AuthorizationScope(description="通过角色id获取菜单",scope="sys:role:listMenuByRoleId")
	    })
	})
	public CommonResult<SysMenuTreeVo> listMenuByRoleId(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取权限资源树",sysRbacService.listMenuByRoleId(RequestHolder.getUserId(), param.getId()));
	}
	@PostMapping("listUserByRoleId")
	@ApiOperation(value="角色成员列表", notes="角色成员列表",authorizations={
		@Authorization(value="角色成员列表",scopes={
	    	@AuthorizationScope(description="角色成员列表",scope="sys:role:listUserByRoleId")
	    })
	})
	public CommonResult<CommonPage<SysUser>> listUserByRoleId(@RequestBody @Validated SysUserWithRoleIdPageParam param) {
		return CommonResult.success("角色成员列表",sysRbacService.listUserByRoleId(param));
	}

	@PostMapping("listUserNoInRole")
	@ApiOperation(value="查询未加入指定角色的用户列表", notes="查询未加入指定角色的用户列表",authorizations={
		@Authorization(value="查询未加入指定角色的用户列表",scopes={
	    	@AuthorizationScope(description="查询未加入指定角色的用户列表",scope="sys:role:listUserNoInRole")
	    })
	})
	public CommonResult<CommonPage<SysUser>> listUserNoInRole(@RequestBody @Validated SysUserWithRoleIdPageParam param) {
		return CommonResult.success("查询未加入指定角色的用户列表",sysRbacService.listUserNoInRole(param));
	}

	@PostMapping("saveUserRole")
	@ApiOperation(value="保存用户角色关系", notes="保存用户角色关系",authorizations={
		@Authorization(value="保存用户角色关系",scopes={
	    	@AuthorizationScope(description="保存用户角色关系",scope="sys:role:saveUserRole")
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
	    	@AuthorizationScope(description="从角色中移除用户",scope="sys:role:deleteUserRole")
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
	    	@AuthorizationScope(description="保存角色权限资源关系",scope="sys:role:saveRoleAccess")
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
	@PostMapping("deleteRoleAccess")
	@ApiOperation(value="删除角色权限资源关系", notes="删除角色权限资源关系",authorizations={
		@Authorization(value="删除角色权限资源关系",scopes={
	    	@AuthorizationScope(description="删除角色权限资源关系",scope="sys:role:deleteRoleAccess")
	    })
	})
	public CommonResult<?> deleteRoleAccess(@RequestBody @Validated IdAndIdsParam param) {
		int count = sysRbacService.deleteRoleAccess(param);
		if(count>0) {
			return CommonResult.success("保存角色权限资源关系成功", null);
		} else {
			return CommonResult.fail("保存角色权限资源关系失败", null);
		}
	}
	@PostMapping("saveRoleMenu")
	@ApiOperation(value="保存角色菜单关系", notes="保存角色菜单关系",authorizations={
		@Authorization(value="保存角色菜单关系",scopes={
	    	@AuthorizationScope(description="保存角色菜单关系",scope="sys:role:saveRoleMenu")
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
	@PostMapping("deleteRoleMenu")
	@ApiOperation(value="删除角色菜单关系", notes="删除角色菜单关系",authorizations={
		@Authorization(value="删除角色菜单关系",scopes={
	    	@AuthorizationScope(description="删除角色菜单关系",scope="sys:role:deleteRoleMenu")
	    })
	})
	public CommonResult<?> deleteRoleMenu(@RequestBody @Validated IdAndIdsParam param) {
		int count = sysRbacService.deleteRoleMenu(param);
		if(count>0) {
			return CommonResult.success("删除角色菜单关系成功", null);
		} else {
			return CommonResult.fail("删除角色菜单关系失败", null);
		}
	}
}
