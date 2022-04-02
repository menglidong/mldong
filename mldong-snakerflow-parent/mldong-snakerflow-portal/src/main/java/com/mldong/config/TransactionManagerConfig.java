package com.mldong.config;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Order
@Configuration
@ConditionalOnClass({DataSource.class, EmbeddedDatabaseType.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@EnableTransactionManagement
public class TransactionManagerConfig {
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* org.snaker.engine.core..*.*(..))";

    public TransactionManagerConfig() {
    }

    @Bean
    public TransactionInterceptor txAdvice(TransactionManager transactionManager) {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(0);
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(0);
        requiredTx.setTimeout(5);
        Map<String, TransactionAttribute> txMap = new HashMap();
        txMap.put("start*", requiredTx);
        txMap.put("execute*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("remove*", requiredTx);
        txMap.put("assign*", requiredTx);
        txMap.put("create*", requiredTx);
        txMap.put("complete*", requiredTx);
        txMap.put("finish*", requiredTx);
        txMap.put("terminate*", requiredTx);
        txMap.put("do*", requiredTx);
        txMap.put("take*", requiredTx);
        txMap.put("deploy*", requiredTx);
        txMap.put("undeploy*", requiredTx);
        txMap.put("redeploy*", requiredTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("find*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        txMap.put("search*", readOnlyTx);
        txMap.put("is*", readOnlyTx);
        txMap.put("*", requiredTx);
        source.setNameMap(txMap);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* org.snaker.engine.core..*.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
}
