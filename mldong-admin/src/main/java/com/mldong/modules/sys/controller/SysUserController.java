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

import com.github.pagehelper.Page;
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
	public int save(@RequestBody SysUser param) {
		return sysUserService.save(param);
	}
	/**
	 * 更新用户
	 * @param param
	 * @return
	 */
	@PostMapping("update")
	@ApiOperation(value="更新用户", notes="更新用户")
	public int update(@RequestBody SysUser param) {
		return sysUserService.update(param);
	}
	/**
	 * 删除用户
	 * @param param
	 * @return
	 */
	@PostMapping("delete")
	@ApiOperation(value="删除用户", notes="删除用户")
	public int delete(@ApiParam(value="用户id")Long id) {
		return sysUserService.delete(id);
	}
	/**
	 * 通过id获取用户
	 * @param param
	 * @return
	 */
	@GetMapping("get")
	@ApiOperation(value="通过id获取用户", notes="通过id获取用户")
	public SysUser get(@ApiParam(value="用户id",required=true)Long id) {
		return sysUserService.get(id);
	}
	/**
	 * 分页查询用户列表
	 * @param param
	 * @return
	 */
	@GetMapping("list")
	@ApiOperation(value="分页查询用户列表", notes="分页查询用户列表")
	public Page<SysUser> list(SysUser param, @ApiParam(value="第n页，默认1")@RequestParam(defaultValue="1")Integer pageNum, @ApiParam(value="每页大小，默认10")@RequestParam(defaultValue="10")int pageSize) {
		return sysUserService.list(param, pageNum, pageSize);
	}
}
