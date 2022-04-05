package com.mldong.modules.cms.wf.interceptor;

import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.tool.AssertTool;
import com.mldong.common.tool.CxtTool;
import com.mldong.modules.cms.entity.CmsArticle;
import com.mldong.modules.cms.mapper.CmsArticleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;

import java.util.Date;

/**
 * 文章审核开始节点前置拦截器
 * @author mldong
 */
public class ArticleStartPreInterceptor implements SnakerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(ArticleStartPreInterceptor.class);
    @Override
    public void intercept(Execution execution) {
        logger.debug("start--ArticleStartPreInterceptor--start");
        Order order = execution.getOrder();
        CmsArticleMapper articleMapper = CxtTool.getBean(CmsArticleMapper.class);
        CmsArticle article = articleMapper.selectByPrimaryKey(order.getVariableMap().get("f_articleId"));
        if(article == null) {
            // 找不到文章
            AssertTool.throwBiz(GlobalErrEnum.GL99990003);
        }
        CmsArticle upArticle = new CmsArticle();
        upArticle.setId(article.getId());
        upArticle.setUpdateTime(new Date());
        // 可以使用order.getId()或order.getOrderNo(),这里为了查询方便，直接使用orderId
        upArticle.setBusinessNo(order.getId());
        // 设置文章状态为审核中
        upArticle.setStatus(CmsArticle.StatusEnum.AUDITING);
        articleMapper.updateByPrimaryKeySelective(upArticle);
        logger.debug("end--ArticleStartPreInterceptor--end");
    }
}
