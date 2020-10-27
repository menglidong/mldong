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
import com.mldong.common.base.IdParam;
import com.mldong.common.base.IdsParam;
import com.mldong.common.validator.Groups;
import com.mldong.modules.sys.dto.SysNoticeParam;
import com.mldong.modules.sys.dto.SysNoticePageParam;
import com.mldong.modules.sys.entity.SysNotice;
import com.mldong.modules.sys.service.SysNoticeService;

@RestController
@RequestMapping("/sys/notice")
@Api(tags="sys-通知公告管理",authorizations={
    @Authorization(value="sys|系统管理",scopes={
    	@AuthorizationScope(description="通知公告管理",scope="sys:notice:index")
    })
})
public class SysNoticeController {
	@Autowired
	private SysNoticeService sysNoticeService;

	@PostMapping("save")
	@ApiOperation(value="添加通知公告", notes="添加通知公告",authorizations={
		@Authorization(value="添加通知公告",scopes={
	    	@AuthorizationScope(description="添加通知公告",scope="sys:notice:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SysNoticeParam param) {
		int count = sysNoticeService.save(param);
		if(count>0) {
			return CommonResult.success("添加通知公告成功", null);
		} else {
			return CommonResult.fail("添加通知公告失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改通知公告", notes="修改通知公告",authorizations={
		@Authorization(value="修改通知公告",scopes={
	    	@AuthorizationScope(description="修改通知公告",scope="sys:notice:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SysNoticeParam param) {
		int count = sysNoticeService.update(param);
		if(count>0) {
			return CommonResult.success("修改通知公告成功", null);
		} else {
			return CommonResult.fail("修改通知公告失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除通知公告", notes="删除通知公告",authorizations={
		@Authorization(value="删除通知公告",scopes={
	    	@AuthorizationScope(description="删除通知公告",scope="sys:notice:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = sysNoticeService.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除通知公告成功", null);
		} else {
			return CommonResult.fail("删除通知公告失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取通知公告", notes="通过id获取通知公告",authorizations={
		@Authorization(value="通过id获取通知公告",scopes={
	    	@AuthorizationScope(description="通过id获取通知公告",scope="sys:notice:get")
	    })
	})
	public CommonResult<SysNotice> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取通知公告成功",sysNoticeService.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询通知公告", notes="分页查询通知公告",authorizations={
		@Authorization(value="分页查询通知公告",scopes={
	    	@AuthorizationScope(description="分页查询通知公告",scope="sys:notice:list")
	    })
	})
	public CommonResult<CommonPage<SysNotice>> list(@RequestBody @Validated SysNoticePageParam param) {
		return CommonResult.success("查询通知公告成功",sysNoticeService.list(param));
	}
}
