package com.mldong.modules.wf.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.wf.dto.ProcessDesignHisPageParam;
import com.mldong.modules.wf.dto.ProcessDesignHisParam;
import com.mldong.modules.wf.service.ProcessDesignHisService;
import com.mldong.modules.wf.vo.ProcessDesignHisVO;
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
    * 流程设计历史 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-25
*/
@RestController
@Api(tags = "流程设计历史管理")
@RequiredArgsConstructor
public class ProcessDesignHisController {
    private final ProcessDesignHisService processDesignHisService;
    /**
     * 添加流程设计历史
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesignHis/save")
    @ApiOperation(value = "添加流程设计历史")
    @SaCheckPermission("wf:processDesignHis:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ProcessDesignHisParam param) {
        processDesignHisService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除流程设计历史
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesignHis/remove")
    @ApiOperation(value = "删除流程设计历史")
    @SaCheckPermission("wf:processDesignHis:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        processDesignHisService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改流程设计历史
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesignHis/update")
    @ApiOperation(value = "修改流程设计历史")
    @SaCheckPermission("wf:processDesignHis:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ProcessDesignHisParam param) {
        processDesignHisService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个流程设计历史
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesignHis/detail")
    @ApiOperation(value = "查询单个流程设计历史")
    @SaCheckPermission("wf:processDesignHis:detail")
    public CommonResult<ProcessDesignHisVO> detail(@RequestBody IdParam param) {
        ProcessDesignHisVO processDesignHis = processDesignHisService.findById(param.getId());
        return CommonResult.data(processDesignHis);
    }
    /**
     *分页查询流程设计历史列表
     * @param param
     * @return
     */
    @PostMapping("/wf/processDesignHis/page")
    @ApiOperation(value = "分页查询流程设计历史列表")
    @SaCheckPermission("wf:processDesignHis:page")
    public CommonResult<CommonPage<ProcessDesignHisVO>> page(@RequestBody ProcessDesignHisPageParam param) {
        return CommonResult.data(processDesignHisService.page(param));
    }
}
