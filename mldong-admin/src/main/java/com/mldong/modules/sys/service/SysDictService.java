package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.common.scanner.model.DictModel;
import com.mldong.modules.sys.dto.SysDictKeyParam;
import com.mldong.modules.sys.dto.SysDictPageParam;
import com.mldong.modules.sys.dto.SysDictParam;
import com.mldong.modules.sys.entity.SysDict;
/**
 * <p>业务接口层</p>
 * <p>字典</p>
 *
 * @since 2020-06-11 11:49:37
 */
public interface SysDictService {
	/**
	 * 添加字典
	 * @param param
	 * @return
	 */
	public int save(SysDictParam param);
	/**
	 * 更新字典
	 * @param param
	 * @return
	 */
	public int update(SysDictParam param);
	/**
	 * 删除字典
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询字典
	 * @param id
	 * @return
	 */
	public SysDict get(Long id);
	/**
	 * 分页查询字典列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<SysDict> list(SysDictPageParam param);
	/**
	 * 通过字典唯一编码查询
	 * @param param
	 * @return
	 */
	public DictModel getByDictKey(SysDictKeyParam param);
	/**
	 * 获取所有字典枚举
	 * @return
	 */
	public List<DictModel> listAllEnum();

}
