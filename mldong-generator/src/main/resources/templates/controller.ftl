package ${basePackage}.modules.${moduleName}.controller;

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

import ${basePackage}.common.base.CommonPage;
import ${basePackage}.common.base.CommonResult;
import ${basePackage}.common.base.IdParam;
import ${basePackage}.common.base.IdsParam;
import ${basePackage}.common.validator.Groups;
import ${basePackage}.modules.sys.dto.${table.className}Param;
import ${basePackage}.modules.sys.entity.${table.className};
import ${basePackage}.modules.sys.service.${table.className}Service;

@RestController
@RequestMapping("/${moduleName}/${table.tableCameName?replace(moduleName,"")?uncap_first}")
@Api(tags="${moduleName}-${table.remark?replace("表","")}管理")
public class ${table.className}Controller {
	@Autowired
	private ${table.className}Service ${table.tableCameName}Service;
	/**
	 * 添加${table.remark?replace("表","")}
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	@ApiOperation(value="添加${table.remark?replace("表","")}", notes="添加${table.remark?replace("表","")}")
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ${table.className}Param param) {
		int count = ${table.tableCameName}Service.save(param);
		if(count>0) {
			return CommonResult.success("添加${table.remark?replace("表","")}成功", null);
		} else {
			return CommonResult.fail("添加${table.remark?replace("表","")}失败", null);
		}
	}
	/**
	 * 更新${table.remark?replace("表","")}
	 * @param param
	 * @return
	 */
	@PostMapping("update")
	@ApiOperation(value="更新${table.remark?replace("表","")}", notes="更新${table.remark?replace("表","")}")
	public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ${table.className}Param param) {
		int count = ${table.tableCameName}Service.update(param);
		if(count>0) {
			return CommonResult.success("更新${table.remark?replace("表","")}成功", null);
		} else {
			return CommonResult.fail("更新${table.remark?replace("表","")}失败", null);
		}
	}
	/**
	 * 删除${table.remark?replace("表","")}
	 * @param param
	 * @return
	 */
	@PostMapping("remove")
	@ApiOperation(value="删除${table.remark?replace("表","")}", notes="删除${table.remark?replace("表","")}")
	public CommonResult<?> remove(@RequestBody IdsParam param) {
		int count = ${table.tableCameName}Service.remove(param.getIds());
		if(count>0) {
			return CommonResult.success("删除${table.remark?replace("表","")}成功", null);
		} else {
			return CommonResult.fail("删除${table.remark?replace("表","")}失败", null);
		}
	}
	/**
	 * 通过id获取${table.remark?replace("表","")}
	 * @param param
	 * @return
	 */
	@PostMapping("get")
	@ApiOperation(value="通过id获取${table.remark?replace("表","")}", notes="通过id获取${table.remark?replace("表","")}")
	public CommonResult<${table.className}> get(@RequestBody IdParam param) {
		return CommonResult.success("获取${table.remark?replace("表","")}成功",${table.tableCameName}Service.get(param.getId()));
	}
	/**
	 * 分页查询${table.remark?replace("表","")}列表
	 * @param param
	 * @return
	 */
	@PostMapping("list")
	@ApiOperation(value="分页查询${table.remark?replace("表","")}列表", notes="分页查询${table.remark?replace("表","")}列表")
	public CommonResult<CommonPage<${table.className}>> list(${table.className}Param param, @ApiParam(value="第n页，默认1")@RequestParam(defaultValue="1")Integer pageNum, @ApiParam(value="每页大小，默认10")@RequestParam(defaultValue="10")int pageSize) {
		return CommonResult.success("查询${table.remark?replace("表","")}成功",${table.tableCameName}Service.list(param, pageNum, pageSize));
	}
}
