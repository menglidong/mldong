package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.sys.dto.SysDeptParam;
import com.mldong.modules.sys.dto.SysDeptPageParam;
import com.mldong.modules.sys.entity.SysDept;
/**
 * <p>业务接口层</p>
 * <p>部门</p>
 *
 * @since 2020-10-21 09:08:16
 */
public interface SysDeptService {
	/**
	 * 添加部门
	 * @param param
	 * @return
	 */
	public int save(SysDeptParam param);
	/**
	 * 更新部门
	 * @param param
	 * @return
	 */
	public int update(SysDeptParam param);
	/**
	 * 删除部门
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询部门
	 * @param id
	 * @return
	 */
	public SysDept get(Long id);
	/**
	 * 分页查询部门列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<SysDept> list(SysDeptPageParam param);

}
