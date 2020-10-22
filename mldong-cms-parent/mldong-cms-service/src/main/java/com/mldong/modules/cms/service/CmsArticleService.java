package com.mldong.modules.cms.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.cms.dto.CmsArticleParam;
import com.mldong.modules.cms.dto.CmsArticlePageParam;
import com.mldong.modules.cms.entity.CmsArticle;
/**
 * <p>业务接口层</p>
 * <p>文章</p>
 *
 * @since 2020-10-22 10:47:45
 */
public interface CmsArticleService {
	/**
	 * 添加文章
	 * @param param
	 * @return
	 */
	public int save(CmsArticleParam param);
	/**
	 * 更新文章
	 * @param param
	 * @return
	 */
	public int update(CmsArticleParam param);
	/**
	 * 删除文章
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询文章
	 * @param id
	 * @return
	 */
	public CmsArticle get(Long id);
	/**
	 * 分页查询文章列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<CmsArticle> list(CmsArticlePageParam param);

}
