package com.mldong.modules.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.sys.dto.ConfigPageParam;
import com.mldong.modules.sys.dto.ConfigParam;
import com.mldong.modules.sys.service.ConfigService;
import com.mldong.modules.sys.vo.ConfigVO;
import com.mldong.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
* <p>
    * 配置 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-21
*/
@RestController
@Api(tags = "配置管理")
@RequiredArgsConstructor
public class ConfigController {
    private final ConfigService configService;
    /**
     * 添加配置
     * @param param
     * @return
     */
    @PostMapping("/sys/config/save")
    @ApiOperation(value = "添加配置")
    @SaCheckPermission("sys:config:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ConfigParam param) {
        configService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除配置
     * @param param
     * @return
     */
    @PostMapping("/sys/config/remove")
    @ApiOperation(value = "删除配置")
    @SaCheckPermission("sys:config:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        configService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改配置
     * @param param
     * @return
     */
    @PostMapping("/sys/config/update")
    @ApiOperation(value = "修改配置")
    @SaCheckPermission("sys:config:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ConfigParam param) {
        configService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个配置
     * @param param
     * @return
     */
    @PostMapping("/sys/config/detail")
    @ApiOperation(value = "查询单个配置")
    @SaCheckPermission("sys:config:detail")
    public CommonResult<ConfigVO> detail(@RequestBody IdParam param) {
        ConfigVO config = configService.findById(param.getId());
        return CommonResult.data(config);
    }
    /**
     *分页查询配置列表
     * @param param
     * @return
     */
    @PostMapping("/sys/config/page")
    @ApiOperation(value = "分页查询配置列表")
    @SaCheckPermission("sys:config:page")
    public CommonResult<CommonPage<ConfigVO>> page(@RequestBody ConfigPageParam param) {
        return CommonResult.data(configService.page(param));
    }
}
