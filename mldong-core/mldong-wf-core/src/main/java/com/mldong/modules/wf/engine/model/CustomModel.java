package com.mldong.modules.wf.engine.model;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.ex.JFlowException;
import com.mldong.modules.wf.engine.handlers.IHandler;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * 自定义节点模型
 * @author mldong
 * @date 2023/12/7
 */
@Data
public class CustomModel extends NodeModel{
    private String clazz; // 类路径
    private String methodName; // 方法名
    private String args; // 入参
    private String var; // 执行返回值的变量
    /**
     * 加载模型时初始化的对象实例
     */
    private Object invokeObject;

    public void exec(Execution execution) {
        if(invokeObject == null) {
            invokeObject = ReflectUtil.newInstance(clazz);
        }
        if(invokeObject == null) {
            throw new JFlowException("自定义模型[class=" + clazz + "]实例化对象失败");
        }

        if(invokeObject instanceof IHandler) {
            IHandler handler = (IHandler)invokeObject;
            handler.handle(execution);
        } else {
            Object[] objects = getArgs(execution.getArgs(), args);
            Class<?> paramTypes[] = Arrays.stream(objects).map(Object::getClass).toArray(Class[]::new);
            Method method = ReflectUtil.getMethod(invokeObject.getClass(),methodName, paramTypes);
            if(method == null) {
                throw new JFlowException("自定义模型[class=" + clazz + "]无法找到方法名称:" + methodName);
            }
            Object returnValue = ReflectUtil.invoke(invokeObject, method, objects);
            if(StrUtil.isNotEmpty(var)) {
                execution.getArgs().put(var, returnValue);
            }
        }
        execution.getEngine().processTaskService().history(execution, this);
        runOutTransition(execution);
    }

    /**
     * 根据传递的执行参数、模型的参数列表返回实际的参数对象数组
     * @param execArgs 运行时传递的参数数据
     * @param args 自定义节点需要的参数
     * @return 调用自定义节点类方法的参数数组
     */
    private Object[] getArgs(Map<String, Object> execArgs, String args) {
        Object[] objects = null;
        if(StrUtil.isNotEmpty(args)) {
            String[] argArray = args.split(",");
            objects = new Object[argArray.length];
            for(int i = 0; i < argArray.length; i++) {
                objects[i] = execArgs.get(argArray[i]);
            }
        }
        return objects;
    }
}
