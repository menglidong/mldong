package com.mldong.common.ds;

import com.mldong.common.tool.StringTool;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        String dsId = ds.value();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        //得到被切面修饰的方法的参数列表
        Object[] args = point.getArgs();
        dsId = parseValue(dsId, method, args);
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            logger.error("数据源[{}]不存在，使用默认数据源 > {}", dsId, point.getSignature());
        } else {
            logger.debug("Use DataSource : {} > {}", ds.value(), point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(dsId);
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        String dsId = ds.value();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        //得到被切面修饰的方法的参数列表
        Object[] args = point.getArgs();
        dsId = parseValue(dsId, method, args);
        logger.debug("Revert DataSource : {} > {}", dsId, point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
    /**
     * 获取注释上的value
     * value 定义在注解上，支持SPEL表达式
     * @return
     */
    private String parseValue(String key, Method method, Object [] args){

        if(StringTool.isEmpty(key)) return null;

        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for(int i=0;i<paraNameArr.length;i++){
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context,String.class);
    }

}
