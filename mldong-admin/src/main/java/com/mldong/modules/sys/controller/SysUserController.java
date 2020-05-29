package com.mldong.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.service.SysUserService;

@RestController
@RequestMapping("/sys/user")
@Api(tags="sys-用户管理")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	/**
	 * 添加用户
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	@ApiOperation(value="添加用户", notes="添加用户")
	public CommonResult<?> save(@RequestBody SysUser param) {
		int count = sysUserService.save(param);
		if(count>0) {
			return CommonResult.success();
		} else {
			return CommonResult.fail();
		}
	}
	/**
	 * 更新用户
	 * @param param
	 * @return
	 */
	@PostMapping("update")
	@ApiOperation(value="更新用户", notes="更新用户")
	public CommonResult<?> update(@RequestBody SysUser param) {
		int count = sysUserService.update(param);
		if(count>0) {
			return CommonResult.success();
		} else {
			return CommonResult.fail();
		}
	}
	/**
	 * 删除用户
	 * @param param
	 * @return
	 */
	@PostMapping("delete")
	@ApiOperation(value="删除用户", notes="删除用户")
	public CommonResult<?> delete(@ApiParam(value="用户id")Long id) {
		int count = sysUserService.delete(id);
		if(count>0) {
			return CommonResult.success();
		} else {
			return CommonResult.fail();
		}
	}
	/**
	 * 通过id获取用户
	 * @param param
	 * @return
	 */
	@GetMapping("get")
	@ApiOperation(value="通过id获取用户", notes="通过id获取用户")
	public CommonResult<SysUser> get(@ApiParam(value="用户id",required=true)Long id) {
		return CommonResult.success(sysUserService.get(id));
	}
	/**
	 * 分页查询用户列表
	 * @param param
	 * @return
	 */
	@GetMapping("list")
	@ApiOperation(value="分页查询用户列表", notes="分页查询用户列表")
	public CommonResult<CommonPage<SysUser>> list(SysUser param, @ApiParam(value="第n页，默认1")@RequestParam(defaultValue="1")Integer pageNum, @ApiParam(value="每页大小，默认10")@RequestParam(defaultValue="10")int pageSize) {
		return CommonResult.success(sysUserService.list(param, pageNum, pageSize));
	}
}
