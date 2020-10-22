package ${basePackage}.modules.${moduleName}.controller;

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

import ${basePackage}.common.base.CommonPage;
import ${basePackage}.common.base.CommonResult;
import ${basePackage}.common.base.IdParam;
import ${basePackage}.common.base.IdsParam;
import ${basePackage}.common.validator.Groups;
import ${basePackage}.modules.${moduleName}.dto.${table.className}Param;
import ${basePackage}.modules.${moduleName}.dto.${table.className}PageParam;
import ${basePackage}.modules.${moduleName}.entity.${table.className};
import ${basePackage}.modules.${moduleName}.service.${table.className}Service;

@RestController
@RequestMapping("/${moduleName}/${table.tableCameName?replace(moduleName,"")?uncap_first}")
@Api(tags="${moduleName}-${table.remark?replace("表","")}管理",authorizations={
    @Authorization(value="${moduleName}|${moduleDesc}",scopes={
    	@AuthorizationScope(description="${table.remark?replace("表","")}管理",scope="${moduleName}:${table.tableCameName?replace(moduleName,"")?uncap_first}:index")
    })
})
public class ${table.className}Controller {
	@Autowired
	private ${table.className}Service ${table.tableCameName}Service;

	@PostMapping("save")
	@ApiOperation(value="添加${table.remark?replace("表","")}", notes="添加${table.remark?replace("表","")}",authorizations={
		@Authorization(value="添加${table.remark?replace("表","")}",scopes={
	    	@AuthorizationScope(description="添加${table.remark?replace("表","")}",scope="${moduleName}:${table.tableCameName?replace(moduleName,"")?uncap_first}:save")
	    })
	})
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ${table.className}Param param) {
		int count = ${table.tableCameName}Service.save(param);
		if(count>0) {
			return CommonResult.success("添加${table.remark?replace("表","")}成功", null);
		} else {
			return CommonResult.fail("添加${table.remark?replace("表","")}失败", null);
		}
	}

	@PostMapping("update")
	@ApiOperation(value="修改${table.remark?replace("表","")}", notes="修改${table.remark?replace("表","")}",authorizations={
		@Authorization(value="修改${table.remark?replace("表","")}",scopes={
	    	@AuthorizationScope(description="修改${table.remark?replace("表","")}",scope="${moduleName}:${table.tableCameName?replace(moduleName,"")?uncap_first}:update")
	    })
	})
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ${table.className}Param param) {
		int count = ${table.tableCameName}Service.update(param);
		if(count>0) {
			return CommonResult.success("修改${table.remark?replace("表","")}成功", null);
		} else {
			return CommonResult.fail("修改${table.remark?replace("表","")}失败", null);
		}
	}

	@PostMapping("remove")
	@ApiOperation(value="删除${table.remark?replace("表","")}", notes="删除${table.remark?replace("表","")}",authorizations={
		@Authorization(value="删除${table.remark?replace("表","")}",scopes={
	    	@AuthorizationScope(description="删除${table.remark?replace("表","")}",scope="${moduleName}:${table.tableCameName?replace(moduleName,"")?uncap_first}:remove")
	    })
	})
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = ${table.tableCameName}Service.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除${table.remark?replace("表","")}成功", null);
		} else {
			return CommonResult.fail("删除${table.remark?replace("表","")}失败", null);
		}
	}

	@PostMapping("get")
	@ApiOperation(value="通过id获取${table.remark?replace("表","")}", notes="通过id获取${table.remark?replace("表","")}",authorizations={
		@Authorization(value="通过id获取${table.remark?replace("表","")}",scopes={
	    	@AuthorizationScope(description="通过id获取${table.remark?replace("表","")}",scope="${moduleName}:${table.tableCameName?replace(moduleName,"")?uncap_first}:get")
	    })
	})
	public CommonResult<${table.className}> get(@RequestBody @Validated IdParam param) {
		return CommonResult.success("获取${table.remark?replace("表","")}成功",${table.tableCameName}Service.get(param.getId()));
	}

	@PostMapping("list")
	@ApiOperation(value="分页查询${table.remark?replace("表","")}", notes="分页查询${table.remark?replace("表","")}",authorizations={
		@Authorization(value="分页查询${table.remark?replace("表","")}",scopes={
	    	@AuthorizationScope(description="分页查询${table.remark?replace("表","")}",scope="${moduleName}:${table.tableCameName?replace(moduleName,"")?uncap_first}:list")
	    })
	})
	public CommonResult<CommonPage<${table.className}>> list(@RequestBody @Validated ${table.className}PageParam param) {
		return CommonResult.success("查询${table.remark?replace("表","")}成功",${table.tableCameName}Service.list(param));
	}
}
