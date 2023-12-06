package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.wf.dto.ProcessSurrogatePageParam;
import com.mldong.modules.wf.dto.ProcessSurrogateParam;
import com.mldong.modules.wf.service.ProcessSurrogateService;
import com.mldong.modules.wf.vo.ProcessSurrogateVO;
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
    * 流程委托代理 前端控制器
    * </p>
*
* @author mldong
* @since 2023-12-06
*/
@RestController
@Api(tags = "流程委托代理管理")
@RequiredArgsConstructor
public class ProcessSurrogateController {
    private final ProcessSurrogateService processSurrogateService;
    /**
     * 添加流程委托代理
     * @param param
     * @return
     */
    @PostMapping("/wf/processSurrogate/save")
    @ApiOperation(value = "添加流程委托代理")
    @SaCheckPermission("wf:processSurrogate:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ProcessSurrogateParam param) {
        processSurrogateService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除流程委托代理
     * @param param
     * @return
     */
    @PostMapping("/wf/processSurrogate/remove")
    @ApiOperation(value = "删除流程委托代理")
    @SaCheckPermission("wf:processSurrogate:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        processSurrogateService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改流程委托代理
     * @param param
     * @return
     */
    @PostMapping("/wf/processSurrogate/update")
    @ApiOperation(value = "修改流程委托代理")
    @SaCheckPermission("wf:processSurrogate:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ProcessSurrogateParam param) {
        processSurrogateService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个流程委托代理
     * @param param
     * @return
     */
    @PostMapping("/wf/processSurrogate/detail")
    @ApiOperation(value = "查询单个流程委托代理")
    @SaCheckPermission("wf:processSurrogate:detail")
    public CommonResult<ProcessSurrogateVO> detail(@RequestBody IdParam param) {
        ProcessSurrogateVO processSurrogate = processSurrogateService.findById(param.getId());
        return CommonResult.data(processSurrogate);
    }
    /**
     *分页查询流程委托代理列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processSurrogate/page")
    @ApiOperation(value = "分页查询流程委托代理列表")
    @SaCheckPermission("wf:processSurrogate:page")
    public CommonResult<CommonPage<ProcessSurrogateVO>> page(@RequestBody ProcessSurrogatePageParam param) {
        return CommonResult.data(processSurrogateService.page(param));
    }
}
