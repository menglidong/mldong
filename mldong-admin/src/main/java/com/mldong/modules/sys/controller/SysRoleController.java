package com.mldong.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.IdParam;
import com.mldong.common.base.IdsParam;
import com.mldong.common.validator.Groups;
import com.mldong.modules.sys.dto.SysRoleParam;
import com.mldong.modules.sys.entity.SysRole;
import com.mldong.modules.sys.service.SysRoleService;

@RestController
@RequestMapping("/sys/role")
@Api(tags="sys-角色管理")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	/**
	 * 添加角色
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	@ApiOperation(value="添加角色", notes="添加角色")
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysRoleParam param) {
		int count = sysRoleService.save(param);
		if(count>0) {
			return CommonResult.success("添加角色成功", null);
		} else {
			return CommonResult.fail("添加角色失败", null);
		}
	}
	/**
	 * 更新角色
	 * @param param
	 * @return
	 */
	@PostMapping("update")
	@ApiOperation(value="更新角色", notes="更新角色")
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysRoleParam param) {
		int count = sysRoleService.update(param);
		if(count>0) {
			return CommonResult.success("更新角色成功", null);
		} else {
			return CommonResult.fail("更新角色失败", null);
		}
	}
	/**
	 * 删除角色
	 * @param param
	 * @return
	 */
	@PostMapping("remove")
	@ApiOperation(value="删除角色", notes="删除角色")
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysRoleService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除角色成功", null);
		} else {
			return CommonResult.fail("删除角色失败", null);
		}
	}
	/**
	 * 通过id获取角色
	 * @param param
	 * @return
	 */
	@PostMapping("get")
	@ApiOperation(value="通过id获取角色", notes="通过id获取角色")
	public CommonResult<SysRole> get(@RequestBody IdParam param) {
		return CommonResult.success("获取角色成功",sysRoleService.get(param.getId()));
	}
	/**
	 * 分页查询角色列表
	 * @param param
	 * @return
	 */
	@PostMapping("list")
	@ApiOperation(value="分页查询角色列表", notes="分页查询角色列表")
	public CommonResult<CommonPage<SysRole>> list(SysRoleParam param, @ApiParam(value="第n页，默认1")@RequestParam(defaultValue="1")Integer pageNum, @ApiParam(value="每页大小，默认10")@RequestParam(defaultValue="10")int pageSize) {
		return CommonResult.success("查询角色成功",sysRoleService.list(param, pageNum, pageSize));
	}
}
