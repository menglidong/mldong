package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.sys.dto.SysMenuParam;
import com.mldong.modules.sys.dto.SysMenuPageParam;
import com.mldong.modules.sys.entity.SysMenu;
/**
 * <p>业务接口层</p>
 * <p>菜单</p>
 *
 * @since 2020-06-07 09:45:41
 */
public interface SysMenuService {
	/**
	 * 添加菜单
	 * @param param
	 * @return
	 */
	public int save(SysMenuParam param);
	/**
	 * 更新菜单
	 * @param param
	 * @return
	 */
	public int update(SysMenuParam param);
	/**
	 * 删除菜单
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询菜单
	 * @param id
	 * @return
	 */
	public SysMenu get(Long id);
	/**
	 * 分页查询菜单列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<SysMenu> list(SysMenuPageParam param);

}
