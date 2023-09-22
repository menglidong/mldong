package com.mldong.modules.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.sys.dto.DeptPageParam;
import com.mldong.modules.sys.dto.DeptParam;
import com.mldong.modules.sys.service.DeptService;
import com.mldong.modules.sys.vo.DeptVO;
import com.mldong.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.mldong.modules.sys.entity.Dept;
/**
* <p>
    * 部门 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-21
*/
@RestController
@Api(tags = "部门管理")
@RequiredArgsConstructor
public class DeptController {
    private final DeptService deptService;
    /**
     * 添加部门
     * @param param
     * @return
     */
    @PostMapping("/sys/dept/save")
    @ApiOperation(value = "添加部门")
    @SaCheckPermission("sys:dept:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) DeptParam param) {
        deptService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除部门
     * @param param
     * @return
     */
    @PostMapping("/sys/dept/remove")
    @ApiOperation(value = "删除部门")
    @SaCheckPermission("sys:dept:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        deptService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改部门
     * @param param
     * @return
     */
    @PostMapping("/sys/dept/update")
    @ApiOperation(value = "修改部门")
    @SaCheckPermission("sys:dept:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) DeptParam param) {
        deptService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个部门
     * @param param
     * @return
     */
    @PostMapping("/sys/dept/detail")
    @ApiOperation(value = "查询单个部门")
    @SaCheckPermission("sys:dept:detail")
    public CommonResult<DeptVO> detail(@RequestBody IdParam param) {
        DeptVO dept = deptService.findById(param.getId());
        return CommonResult.data(dept);
    }
    /**
     *分页查询部门列表
     * @param param
     * @return
     */
    @PostMapping("/sys/dept/page")
    @ApiOperation(value = "分页查询部门列表")
    @SaCheckPermission("sys:dept:page")
    public CommonResult<CommonPage<DeptVO>> page(@RequestBody DeptPageParam param) {
        return CommonResult.data(deptService.page(param));
    }
    /**
     *查询部门列表
     * @param param
     * @return
     */
    @PostMapping("/sys/dept/list")
    @ApiOperation(value = "查询部门列表")
    @SaCheckPermission("sys:dept:list")
    public CommonResult<List<Dept>> list(@RequestBody DeptPageParam param) {
        return CommonResult.data(deptService.list(param.buildQueryWrapper()));
    }
    /**
     *查询部门树
     * @param param
     * @return
     */
    @PostMapping("/sys/dept/tree")
    @ApiOperation(value = "查询部门树")
    @SaCheckPermission("sys:dept:tree")
    public CommonResult<List<Dept>> tree(@RequestBody DeptPageParam param) {
        return CommonResult.data(deptService.tree(param));
    }
}
