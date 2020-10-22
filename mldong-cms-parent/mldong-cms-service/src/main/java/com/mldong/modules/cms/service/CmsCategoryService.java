package com.mldong.modules.cms.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.cms.dto.CmsCategoryParam;
import com.mldong.modules.cms.dto.CmsCategoryPageParam;
import com.mldong.modules.cms.entity.CmsCategory;
/**
 * <p>业务接口层</p>
 * <p>栏目</p>
 *
 * @since 2020-10-22 10:47:45
 */
public interface CmsCategoryService {
	/**
	 * 添加栏目
	 * @param param
	 * @return
	 */
	public int save(CmsCategoryParam param);
	/**
	 * 更新栏目
	 * @param param
	 * @return
	 */
	public int update(CmsCategoryParam param);
	/**
	 * 删除栏目
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询栏目
	 * @param id
	 * @return
	 */
	public CmsCategory get(Long id);
	/**
	 * 分页查询栏目列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<CmsCategory> list(CmsCategoryPageParam param);

}
