package com.mldong.modules.cms.dao;

import com.mldong.common.dauth.DataScope;
import com.mldong.modules.cms.dto.CmsArticlePageParam;
import com.mldong.modules.cms.dto.CmsArticleWithExt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsArticleDao {
    /**
     * 查询文章列表-包含扩展信息
     * @param param
     * @return
     */
    public List<CmsArticleWithExt> selectWithExt(CmsArticlePageParam param);

    /**
     * 通过id获取文章-包含扩展信息
     * @param id
     * @return
     */
    public CmsArticleWithExt selectOneWithExt(Long id);

    /**
     * 查询文章列表-数据权限
     * @param param
     * @return
     */
    @DataScope
    public List<CmsArticleWithExt> selectOnDataScope(CmsArticlePageParam param);
}
