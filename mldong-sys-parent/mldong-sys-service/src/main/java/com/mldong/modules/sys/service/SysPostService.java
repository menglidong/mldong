package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.sys.dto.SysPostParam;
import com.mldong.modules.sys.dto.SysPostPageParam;
import com.mldong.modules.sys.entity.SysPost;
/**
 * <p>业务接口层</p>
 * <p>岗位</p>
 *
 * @since 2020-10-21 09:08:07
 */
public interface SysPostService {
	/**
	 * 添加岗位
	 * @param param
	 * @return
	 */
	public int save(SysPostParam param);
	/**
	 * 更新岗位
	 * @param param
	 * @return
	 */
	public int update(SysPostParam param);
	/**
	 * 删除岗位
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询岗位
	 * @param id
	 * @return
	 */
	public SysPost get(Long id);
	/**
	 * 分页查询岗位列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<SysPost> list(SysPostPageParam param);

}
