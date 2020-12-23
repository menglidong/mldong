package com.mldong.modules.cms.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.modules.cms.dto.CmsArticleParam;
import com.mldong.modules.cms.dto.CmsArticlePageParam;
import com.mldong.modules.cms.dto.CmsArticleWithExt;
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

	/**
	 * 查询文章列表-包含扩展信息
	 * @param param
	 * @return
	 */
	public CommonPage<CmsArticleWithExt> listWithExt(CmsArticlePageParam param);
	/**
	 * 通过id获取文章-包含扩展信息
	 * @param id
	 * @return
	 */
	public CmsArticleWithExt getWithExt(Long id);
	/**
	 * 查询文章列表-数据权限1
	 * @param param
	 * @return
	 */
	public CommonPage<CmsArticleWithExt> listOnDataScope(CmsArticlePageParam param);
	/**
	 * 查询文章列表-数据权限2
	 * @param param
	 * @return
	 */
	public CommonPage<CmsArticleWithExt> listOnDataScope2(CmsArticlePageParam param);
}
