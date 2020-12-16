package com.mldong.modules.sys.controller;

import com.mldong.common.annotation.LoginUser;
import com.mldong.common.base.CommonPage;
import com.mldong.common.base.CommonResult;
import com.mldong.common.base.IdParam;
import com.mldong.common.base.IdsParam;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
import com.mldong.common.upload.UploadMimeType;
import com.mldong.common.validator.Groups;
import com.mldong.common.web.RequestHolder;
import com.mldong.modules.sys.dto.*;
import com.mldong.modules.sys.entity.SysUploadConfig;
import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.service.SysUploadConfigService;
import com.mldong.modules.sys.service.SysUserService;
import com.mldong.modules.sys.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/sys/user")
@Api(tags="sys-用户管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="用户管理",scope="sys:user:index")
    })
})
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUploadConfigService sysUploadConfigService;
	/**
	 * 添加用户
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	@ApiOperation(value="添加用户", notes="添加用户",authorizations={
		@Authorization(value="添加用户",scopes={
	    	@AuthorizationScope(description="添加用户",scope="sys:user:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysUserParam param) {
		int count = sysUserService.save(param);
		if(count>0) {
			return CommonResult.success("添加用户成功", null);
		} else {
			return CommonResult.fail("添加用户失败", null);
		}
	}
	/**
	 * 更新用户
	 * @param param
	 * @return
	 */
	@PostMapping("update")
	@ApiOperation(value="更新用户", notes="更新用户",authorizations={
		@Authorization(value="更新用户",scopes={
	    	@AuthorizationScope(description="更新用户",scope="sys:user:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysUserParam param) {
		int count = sysUserService.update(param);
		if(count>0) {
			return CommonResult.success("更新用户成功", null);
		} else {
			return CommonResult.fail("更新用户失败", null);
		}
	}
	/**
	 * 删除用户
	 * @param param
	 * @return
	 */
	@PostMapping("remove")
	@ApiOperation(value="删除用户", notes="删除用户",authorizations={
		@Authorization(value="删除用户",scopes={
	    	@AuthorizationScope(description="删除用户",scope="sys:user:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysUserService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除用户成功", null);
		} else {
			return CommonResult.fail("删除用户失败", null);
		}
	}
	/**
	 * 通过id获取用户
	 * @param param
	 * @return
	 */
	@PostMapping("get")
	@ApiOperation(value="通过id获取用户", notes="通过id获取用户",authorizations={
		@Authorization(value="通过id获取用户",scopes={
	    	@AuthorizationScope(description="通过id获取用户",scope="sys:user:get")
	    })
	})
	public CommonResult<SysUser> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取用户成功",sysUserService.get(param.getId()));
	}
	/**
	 * 分页查询用户列表
	 * @param param
	 * @return
	 */
	@PostMapping("list")
	@ApiOperation(value="分页查询用户列表", notes="分页查询用户列表",authorizations={
		@Authorization(value="分页查询用户列表",scopes={
	    	@AuthorizationScope(description="分页查询用户列表",scope="sys:user:list")
	    })
	})
	public CommonResult<CommonPage<SysUser>> list(@RequestBody @Validated SysUserPageParam param) {
		return CommonResult.success("查询用户成功",sysUserService.list(param));
	}
	/**
	 * 获取当前用户信息
	 * @return
	 */
	@PostMapping("info")
	@ApiOperation(value="获取当前用户信息", notes="获取当前用户信息")
	public CommonResult<SysUserVo> info(@LoginUser Long userId) {
		return CommonResult.success(sysUserService.getUserInfo(userId));
	}
	/**
	 * 自定义分页查询用户列表
	 * @param param
	 * @return
	 */
	@PostMapping("listWithExt")
	@ApiOperation(value="自定义分页查询用户列表", notes="自定义分页查询用户列表",authorizations={
			@Authorization(value="自定义分页查询用户列表",scopes={
					@AuthorizationScope(description="自定义分页查询用户列表",scope="sys:user:listWithExt")
			})
	})
	public CommonResult<CommonPage<SysUserResult>> listWithExt(@RequestBody @Validated SysUserPageParam param) {
		return CommonResult.success("查询用户成功",sysUserService.listWithExt(param));
	}

	/**
	 * 获取当前用户信息-用于更新
	 * @return
	 */
	@PostMapping("getProfile")
	@ApiOperation(value="获取当前用户信息-用于更新", notes="获取当前用户信息-用于更新")
	public CommonResult<SysUserVo> getProfile(@LoginUser Long userId) {
		return CommonResult.success(sysUserService.getProfile(userId));
	}
	/**
	 * 更新当前用户密码
	 * @return
	 */
	@PostMapping("updatePwd")
	@ApiOperation(value="更新当前用户密码", notes="更新当前用户密码")
	public CommonResult<?> updatePwd(@RequestBody @Validated SysUpdatePwdParam param) {
		Long userId = RequestHolder.getUserId();
		param.setUserId(userId);
		return CommonResult.success(sysUserService.updatePwd(param));
	}
	/**
	 * 更新当前用户头像
	 * @return
	 */
	@PostMapping("uploadAvatar")
	@ApiOperation(value="更新当前用户头像", notes="更新当前用户头像")
	public CommonResult<?> uploadAvatar(@RequestParam("avatarfile") MultipartFile file) {
		if(file.isEmpty()) {
			throw new BizException(GlobalErrEnum.GL99990007);
		}
		String bizType = "avatar";
		SysUploadConfig config = sysUploadConfigService.getByBizType(bizType);
		if(config == null) {
			throw new BizException(GlobalErrEnum.GL99990008);
		}
		if(!UploadMimeType.getMimeType(config.getFileExt()).contains(file.getContentType())) {
			throw new BizException(GlobalErrEnum.GL99990006);
		}
		if(file.getSize()>config.getFileSizeMax()) {
			throw new BizException(GlobalErrEnum.GL99990009);
		}
		Long userId = RequestHolder.getUserId();
		SysAvatarParam param = new SysAvatarParam();
		param.setUserId(userId);
		String fileName = file.getOriginalFilename();  // 文件名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
		String filePath = config.getUploadDir() + config.getUploadSubDir();
		fileName = UUID.randomUUID() + suffixName; // 新文件名
		File dest = new File(filePath, fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BizException(GlobalErrEnum.GL99990007);
		}
		param.setAvatar(config.getUploadSubDir()+"/"+fileName);
		sysUserService.uploadAvatar(param);
		return CommonResult.success(param.getAvatar());
	}
	/**
	 * 更新当前用户基本信息
	 * @return
	 */
	@PostMapping("updateProfile")
	@ApiOperation(value="更新当前用户基本信息", notes="更新当前用户基本信息")
	public CommonResult<?> updateProfile(@RequestBody @Validated SysUpdateProfileParam param) {
		Long userId = RequestHolder.getUserId();
		param.setUserId(userId);
		return CommonResult.success(sysUserService.updateProfile(param));
	}
}
