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
import java.util.Map;

/**
 * 文章审核结束后置处理器
 * @author mldong
 */
public class ArticleEndPostIntereptor implements SnakerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(ArticleEndPostIntereptor.class);
    @Override
    public void intercept(Execution execution) {
        logger.debug("start--ArticleEndPostIntereptor--start");
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
        Map<String,Object> args = execution.getArgs();
        if(Integer.valueOf(1).equals(args.get("approvalType"))) {
            // 设置文章状态为审批通过
            upArticle.setStatus(CmsArticle.StatusEnum.PASS);
        } else {
            upArticle.setStatus(CmsArticle.StatusEnum.NO_PASS);
        }
        articleMapper.updateByPrimaryKeySelective(upArticle);
        logger.debug("end--ArticleEndPostIntereptor--end");
    }
}
