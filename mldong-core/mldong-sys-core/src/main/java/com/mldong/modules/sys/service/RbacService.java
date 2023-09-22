package com.mldong.modules.sys.service;


import com.mldong.auth.RolePermCode;
import com.mldong.base.CommonPage;
import com.mldong.modules.sys.dto.RoleMenuPageParam;
import com.mldong.modules.sys.dto.RoleMenuParam;
import com.mldong.modules.sys.dto.UserRolePageParam;
import com.mldong.modules.sys.dto.UserRoleParam;
import com.mldong.modules.sys.vo.MenuVO;
import com.mldong.modules.sys.vo.UserVO;

import java.util.List;

public interface RbacService {
    /**
     * 添加r_角色菜单关系
     * @param param
     * @return
     */
    public boolean saveRoleMenu(List<RoleMenuParam> param);

    /**
     * 角色ID获取菜单ID集合
     * @param id
     * @return
     */
    public List<Long> roleMenuIds(Long id);

    /**
     * 分页获取角色菜单列表
     * @param param
     * @return
     */
    public List<MenuVO> roleMenuList(RoleMenuPageParam param);

    /**
     * 添加r_用户角色关系
     * @param param
     * @return
     */
    public boolean saveUserRole(List<UserRoleParam> param);

    /**
     * 删除r_用户角色关系
     * @param param
     * @return
     */
    public boolean removeUserRole(List<UserRoleParam> param);

    /**
     * 通过角色ID获取用户列表
     * @param param
     * @return
     */
    public CommonPage<UserVO> userListByRoleId(UserRolePageParam param);

    /**
     * 获取用户列表-排除指定角色
     * @param param
     * @return
     */
    public CommonPage<UserVO> userListExcludeRoleId(UserRolePageParam param);
    /**
     * 获取权限码对象
     * @param roleId
     * @param appCode
     * @return
     */
    RolePermCode getInCache(Long roleId, String appCode);

    /**
     * 获取权限编码集合
     * @param roleIds
     * @param appCode
     * @return
     */
    List<String> getPermInCache(List<Long> roleIds,String appCode);
}
