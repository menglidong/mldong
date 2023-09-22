package ${package.Controller};

import cn.dev33.satoken.annotation.SaCheckPermission;
import ${g.basePackage}.base.CommonPage;
import ${g.basePackage}.base.CommonResult;
import ${g.basePackage}.base.IdParam;
import ${g.basePackage}.base.IdsParam;
import ${package.Other}.${entity}PageParam;
import ${package.Other}.${entity}Param;
import ${package.Service}.${table.serviceName};
import ${package.Entity?replace("entity","vo")}.${entity}VO;
import ${g.basePackage}.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
<#if isTree>
import java.util.List;
import ${package.Entity}.${entity};
</#if>
/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
@RestController
@Api(tags = "${table.comment!}管理")
@RequiredArgsConstructor
public class ${entity}Controller {
    private final ${entity}Service ${table.entityPath}Service;
    /**
     * 添加${table.comment!}
     * @param param
     * @return
     */
    @PostMapping("/${package.ModuleName}/${table.entityPath}/save")
    @ApiOperation(value = "添加${table.comment!}")
    @SaCheckPermission("${package.ModuleName}:${table.entityPath}:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ${entity}Param param) {
        ${table.entityPath}Service.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除${table.comment!}
     * @param param
     * @return
     */
    @PostMapping("/${package.ModuleName}/${table.entityPath}/remove")
    @ApiOperation(value = "删除${table.comment!}")
    @SaCheckPermission("${package.ModuleName}:${table.entityPath}:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        ${table.entityPath}Service.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改${table.comment!}
     * @param param
     * @return
     */
    @PostMapping("/${package.ModuleName}/${table.entityPath}/update")
    @ApiOperation(value = "修改${table.comment!}")
    @SaCheckPermission("${package.ModuleName}:${table.entityPath}:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ${entity}Param param) {
        ${table.entityPath}Service.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个${table.comment!}
     * @param param
     * @return
     */
    @PostMapping("/${package.ModuleName}/${table.entityPath}/detail")
    @ApiOperation(value = "查询单个${table.comment!}")
    @SaCheckPermission("${package.ModuleName}:${table.entityPath}:detail")
    public CommonResult<${entity}VO> detail(@RequestBody IdParam param) {
        ${entity}VO ${table.entityPath} = ${table.entityPath}Service.findById(param.getId());
        return CommonResult.data(${table.entityPath});
    }
    /**
     *分页查询${table.comment!}列表
     * @param param
     * @return
     */
    @PostMapping("/${package.ModuleName}/${table.entityPath}/page")
    @ApiOperation(value = "分页查询${table.comment!}列表")
    @SaCheckPermission("${package.ModuleName}:${table.entityPath}:page")
    public CommonResult<CommonPage<${entity}VO>> page(@RequestBody ${entity}PageParam param) {
        return CommonResult.data(${table.entityPath}Service.page(param));
    }
	<#if isTree>
    /**
     *查询${table.comment!}列表
     * @param param
     * @return
     */
    @PostMapping("/${package.ModuleName}/${table.entityPath}/list")
    @ApiOperation(value = "查询${table.comment!}列表")
    @SaCheckPermission("${package.ModuleName}:${table.entityPath}:list")
    public CommonResult<List<${entity}>> list(@RequestBody ${entity}PageParam param) {
        return CommonResult.data(${table.entityPath}Service.list(param.buildQueryWrapper()));
    }
    /**
     *查询${table.comment!}树
     * @param param
     * @return
     */
    @PostMapping("/${package.ModuleName}/${table.entityPath}/tree")
    @ApiOperation(value = "查询${table.comment!}树")
    @SaCheckPermission("${package.ModuleName}:${table.entityPath}:tree")
    public CommonResult<List<${entity}>> tree(@RequestBody ${entity}PageParam param) {
        return CommonResult.data(${table.entityPath}Service.tree(param));
    }
    </#if>
}
