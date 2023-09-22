package com.mldong.modules.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mldong.base.CommonPage;
import com.mldong.base.CommonResult;
import com.mldong.base.IdParam;
import com.mldong.base.IdsParam;
import com.mldong.modules.sys.dto.RolePageParam;
import com.mldong.modules.sys.dto.RoleParam;
import com.mldong.modules.sys.service.RoleService;
import com.mldong.modules.sys.vo.RoleVO;
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
    * 角色 前端控制器
    * </p>
*
* @author mldong
* @since 2023-09-21
*/
@RestController
@Api(tags = "角色管理")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    /**
     * 添加角色
     * @param param
     * @return
     */
    @PostMapping("/sys/role/save")
    @ApiOperation(value = "添加角色")
    @SaCheckPermission("sys:role:save")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) RoleParam param) {
        roleService.save(param);
        return CommonResult.ok();
    }
    /**
     * 删除角色
     * @param param
     * @return
     */
    @PostMapping("/sys/role/remove")
    @ApiOperation(value = "删除角色")
    @SaCheckPermission("sys:role:remove")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        roleService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }
    /**
     * 修改角色
     * @param param
     * @return
     */
    @PostMapping("/sys/role/update")
    @ApiOperation(value = "修改角色")
    @SaCheckPermission("sys:role:update")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) RoleParam param) {
        roleService.update(param);
        return CommonResult.ok();
    }
    /**
     * 查询单个角色
     * @param param
     * @return
     */
    @PostMapping("/sys/role/detail")
    @ApiOperation(value = "查询单个角色")
    @SaCheckPermission("sys:role:detail")
    public CommonResult<RoleVO> detail(@RequestBody IdParam param) {
        RoleVO role = roleService.findById(param.getId());
        return CommonResult.data(role);
    }
    /**
     *分页查询角色列表
     * @param param
     * @return
     */
    @PostMapping("/sys/role/page")
    @ApiOperation(value = "分页查询角色列表")
    @SaCheckPermission("sys:role:page")
    public CommonResult<CommonPage<RoleVO>> page(@RequestBody RolePageParam param) {
        return CommonResult.data(roleService.page(param));
    }
}
