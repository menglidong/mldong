package com.mldong.modules.biz.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.biz.dto.DemoPageParam;
import com.mldong.modules.biz.dto.DemoParam;
import com.mldong.modules.biz.service.DemoService;
import com.mldong.modules.biz.vo.DemoVO;
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
    * 演示 前端控制器
    * </p>
*
* @author mldong
* @since 2023-12-28
*/
@RestController
@Api(tags = "演示管理")
@RequiredArgsConstructor
public class DemoController {
    private final DemoService demoService;
    /**
     * 添加演示
     * @param param
     * @return
     */
    @PostMapping("/biz/demo/save")
    @ApiOperation(value = "添加演示")
    @SaCheckPermission("biz:demo:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) DemoParam param) {
        demoService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除演示
     * @param param
     * @return
     */
    @PostMapping("/biz/demo/remove")
    @ApiOperation(value = "删除演示")
    @SaCheckPermission("biz:demo:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        demoService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改演示
     * @param param
     * @return
     */
    @PostMapping("/biz/demo/update")
    @ApiOperation(value = "修改演示")
    @SaCheckPermission("biz:demo:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) DemoParam param) {
        demoService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个演示
     * @param param
     * @return
     */
    @PostMapping("/biz/demo/detail")
    @ApiOperation(value = "查询单个演示")
    @SaCheckPermission("biz:demo:detail")
    public CommonResult<DemoVO> detail(@RequestBody IdParam param) {
        DemoVO demo = demoService.findById(param.getId());
        return CommonResult.data(demo);
    }
    /**
     *分页查询演示列表
     * @param param
     * @return
     */
    @PostMapping("/biz/demo/page")
    @ApiOperation(value = "分页查询演示列表")
    @SaCheckPermission("biz:demo:page")
    public CommonResult<CommonPage<DemoVO>> page(@RequestBody DemoPageParam param) {
        return CommonResult.data(demoService.page(param));
    }
}
