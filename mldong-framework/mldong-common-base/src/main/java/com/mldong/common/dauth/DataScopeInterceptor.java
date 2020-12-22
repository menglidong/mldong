package com.mldong.common.dauth;

import com.mldong.common.tool.StringTool;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

@Intercepts({@Signature(
        type = org.apache.ibatis.executor.Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class DataScopeInterceptor implements Interceptor {
    private static final Logger logger= LoggerFactory.getLogger(DataScopeInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String dataAuthSql = DataScopeHelper.getLocalDataAuthSql();
        // 不为空才处理
        if(StringTool.isNotEmpty(dataAuthSql)) {
            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            Object parameter = args[1];
            BoundSql boundSql;
            logger.debug(ms.getId());
            if (args.length == 4) {
                boundSql = ms.getBoundSql(parameter);
            } else {
                boundSql = (BoundSql) args[5];
            }
            String newSql = boundSql.getSql() + dataAuthSql ;
            BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), newSql,
                    boundSql.getParameterMappings(), boundSql.getParameterObject());
            // 把新的查询放到statement里
            MappedStatement newMs = newMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
            for (ParameterMapping mapping : boundSql.getParameterMappings()) {
                String prop = mapping.getProperty();
                if (boundSql.hasAdditionalParameter(prop)) {
                    newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
                }
            }
            Object[] queryArgs = invocation.getArgs();
            queryArgs[0] = newMs;
            logger.info("改写的SQL: {}", newSql);
            Object obj = invocation.proceed();
            // 执行完成-清除
            DataScopeHelper.clearDataAuthSql();
            return obj;
        } else {
            return invocation.proceed();
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        logger.debug(properties.toString());
    }

    /**
     * 定义一个内部辅助类，作用是包装 SQL
     */
    class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;
        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }

    }

    private MappedStatement newMappedStatement (MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new
                MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
            builder.keyProperty(ms.getKeyProperties()[0]);
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

}
