package com.mldong.modules.sys.controller;

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
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	/**
	 * 添加用户
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	public int save(@RequestBody SysUser param) {
		return sysUserService.save(param);
	}
	/**
	 * 更新用户
	 * @param param
	 * @return
	 */
	@PostMapping("update")
	public int update(@RequestBody SysUser param) {
		return sysUserService.update(param);
	}
	/**
	 * 删除用户
	 * @param param
	 * @return
	 */
	@PostMapping("delete")
	public int delete(Long id) {
		return sysUserService.delete(id);
	}
	/**
	 * 通过id获取用户
	 * @param param
	 * @return
	 */
	@GetMapping("get")
	public SysUser get(Long id) {
		return sysUserService.get(id);
	}
	/**
	 * 分页查询用户列表
	 * @param param
	 * @return
	 */
	@GetMapping("list")
	public Page<SysUser> list(SysUser param, @RequestParam(defaultValue="1")Integer pageNum, @RequestParam(defaultValue="10")int pageSize) {
		return sysUserService.list(param, pageNum, pageSize);
	}
}
