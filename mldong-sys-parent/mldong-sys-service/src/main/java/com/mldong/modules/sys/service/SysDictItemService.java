package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.sys.dto.SysDictItemParam;
import com.mldong.modules.sys.dto.SysDictItemPageParam;
import com.mldong.modules.sys.entity.SysDictItem;
/**
 * <p>业务接口层</p>
 * <p>字典项</p>
 *
 * @since 2020-06-11 11:49:37
 */
public interface SysDictItemService {
	/**
	 * 添加字典项
	 * @param param
	 * @return
	 */
	public int save(SysDictItemParam param);
	/**
	 * 更新字典项
	 * @param param
	 * @return
	 */
	public int update(SysDictItemParam param);
	/**
	 * 删除字典项
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询字典项
	 * @param id
	 * @return
	 */
	public SysDictItem get(Long id);
	/**
	 * 分页查询字典项列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<SysDictItem> list(SysDictItemPageParam param);

}
