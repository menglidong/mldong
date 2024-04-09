package com.mldong.modules.dev.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.context.constant.ConstantContextHolder;
import com.mldong.modules.dev.dto.SchemaPageParam;
import com.mldong.modules.dev.dto.SchemaParam;
import com.mldong.modules.dev.enums.DevConst;
import com.mldong.modules.dev.service.SchemaService;
import com.mldong.modules.dev.vo.SchemaVO;
import com.mldong.validation.Groups;
import com.mldong.web.LoginUserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* <p>
    * 数据模型 前端控制器
    * </p>
*
* @author mldong
* @since 2024-01-17
*/
@RestController
@Api(tags = "数据模型管理")
@RequiredArgsConstructor
public class SchemaController {
    private static final Environment environment = SpringUtil.getBean(Environment.class);
    private static final String profileActive = environment.getProperty("spring.profiles.active",String.class);
    private final SchemaService schemaService;
    /**
     * 添加数据模型
     * @param param
     * @return
     */
    @PostMapping("/dev/schema/save")
    @ApiOperation(value = "添加数据模型")
    @SaCheckPermission("dev:schema:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) SchemaParam param) {
        schemaService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除数据模型
     * @param param
     * @return
     */
    @PostMapping("/dev/schema/remove")
    @ApiOperation(value = "删除数据模型")
    @SaCheckPermission("dev:schema:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        schemaService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改数据模型
     * @param param
     * @return
     */
    @PostMapping("/dev/schema/update")
    @ApiOperation(value = "修改数据模型")
    @SaCheckPermission("dev:schema:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) SchemaParam param) {
        schemaService.update(param);
        return CommonResult.ok();
    }
    /**
     * 修改数据模型列表字段
     * @param param
     * @return
     */
    @PostMapping("/dev/schema/updateListKeys")
    @ApiOperation(value = "修改数据模型列表字段")
    @SaCheckPermission("dev:schema:updateListKeys")
    public CommonResult<?> updateListKeys(@RequestBody @Validated({SchemaParam.UpdateListKeys.class}) SchemaParam param) {
        schemaService.update(param);
        return CommonResult.ok();
    }
    /**
     * 修改数据模型搜索表单字段
     * @param param
     * @return
     */
    @PostMapping("/dev/schema/updateSearchFormKeys")
    @ApiOperation(value = "修改数据模型搜索表单字段")
    @SaCheckPermission("dev:schema:updateSearchFormKeys")
    public CommonResult<?> updateSearchFormKeys(@RequestBody @Validated({SchemaParam.UpdateSearchFormKeys.class}) SchemaParam param) {
        schemaService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个数据模型
     * @param param
     * @return
     */
    @PostMapping("/dev/schema/detail")
    @ApiOperation(value = "查询单个数据模型")
    @SaCheckPermission(value = {"dev:schema:detail","dev:schema:updateListKeys","dev:schema:updateSearchFormKeys"}, mode = SaMode.OR)
    public CommonResult<SchemaVO> detail(@RequestBody IdParam param) {
        SchemaVO schema = schemaService.findById(param.getId());
        return CommonResult.data(schemaService.getByTableName(schema.getTableName()));
    }
    /**
     *分页查询数据模型列表
     * @param param
     * @return
     */
    @PostMapping("/dev/schema/page")
    @ApiOperation(value = "分页查询数据模型列表")
    @SaCheckPermission("dev:schema:page")
    public CommonResult<CommonPage<SchemaVO>> page(@RequestBody SchemaPageParam param) {
        return CommonResult.data(schemaService.page(param));
    }
    /**
     * 获取数据库表
     * @return
     */
    @PostMapping("/dev/schema/dbTable")
    @ApiOperation(value = "获取数据库表")
    @SaCheckPermission(value = {"dev:schema:dbTable","dev:schema:importTable"},mode = SaMode.OR)
    public CommonResult<?> dbTable(@RequestBody SchemaPageParam param) {
        return CommonResult.data(schemaService.dbTable(param.getKeywords()));
    }
    /**
     * 导入数据表
     * @return
     */
    @PostMapping("/dev/schema/importTable")
    @ApiOperation(value = "导入数据表")
    @SaCheckPermission("dev:schema:importTable")
    public CommonResult<?> importTable(@RequestBody SchemaParam param) {
        schemaService.importTable(param.getSchemaGroupId(),param.getTableNames());
        return CommonResult.ok();
    }
    @GetMapping("/dev/schema/getByTableName")
    @SaIgnore
    public CommonResult<SchemaVO> getByTableName(@RequestParam(value = "tableName", required = true) String tableName,
                                                 @RequestHeader(value = "appId", required = false) String appId,
                                                 @RequestHeader(value = "appSecret", required = false)String appSecret) {
        // 生产环境需要手动校验权限
        if(ObjectUtil.equals(profileActive,"prod")) {
            StpUtil.checkLogin();// 登录即可访问
//            if(!LoginUserHolder.me().isSuperAdmin()) {
//                StpUtil.checkPermission("dev:schema:getByTableName");
//            }
        } else {
            // 其他环境先校验请求头，appId,appSecret
            String defalutAppId = ConstantContextHolder.getSysConfigWithDefault(DevConst.DEFAULT_SCHEMA_APP_ID,String.class, DevConst.DEFAULT_SCHEMA_APP_ID);
            String defaultAppSecret = ConstantContextHolder.getSysConfigWithDefault(DevConst.DEFAULT_SCHEMA_APP_SECRET,String.class, DevConst.DEFAULT_SCHEMA_APP_SECRET);
            if(!(ObjectUtil.equals(appId, defalutAppId) && ObjectUtil.equals(appSecret, defaultAppSecret))) {
                // 密钥不正确，手动校验权限--登录即可访问
                StpUtil.checkLogin();
//                if(!LoginUserHolder.me().isSuperAdmin()) {
//                    StpUtil.checkPermission("dev:schema:getByTableName");
//                }
            }
        }
        return CommonResult.data(schemaService.getByTableName(tableName));
    }
}
