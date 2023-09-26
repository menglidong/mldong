package com.mldong.modules.wf.engine.model;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.mldong.modules.wf.engine.Action;
import com.mldong.modules.wf.engine.FlowInterceptor;
import com.mldong.modules.wf.engine.core.Execution;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 节点模型
 * @author mldong
 * @date 2023/4/25
 */
@Data
public abstract class NodeModel extends BaseModel implements Action {
    private String layout;// 布局属性(x,y,w,h)
    // 输入边集合
    private List<TransitionModel> inputs = new ArrayList<TransitionModel>();
    // 输出边集合
    private List<TransitionModel> outputs = new ArrayList<TransitionModel>();
    private String preInterceptors; // 节点前置拦截器
    private String postInterceptors; // 节点后置拦截器

    /**
     * 由子类自定义执行方法
     * @param execution
     */
    abstract void exec(Execution execution);
    @Override
    public void execute(Execution execution) {
        // 0.设置当前节点模型
        execution.setNodeModel(this);
        // 1. 调用前置拦截器
        execPreInterceptors(execution);
        // 2. 调用子类的exec方法
        exec(execution);
        // 3. 调用后置拦截器
        execPostInterceptors(execution);
    }
    /**
     * 执行输出边
     */
    protected void runOutTransition(Execution execution) {
        outputs.forEach(tr->{
            tr.setEnabled(true);
            tr.execute(execution);
        });
    }

    /**
     * 执行节点前置拦截器
     * @param execution
     */
    private void execPreInterceptors(Execution execution) {
        if(StrUtil.isEmpty(preInterceptors)) {
            preInterceptors = execution.getProcessModel().getPreInterceptors();
        }
        execInterceptors(preInterceptors,execution);
    }
    /**
     * 执行节点后置拦截器
     * @param execution
     */
    private void execPostInterceptors(Execution execution) {
        if(StrUtil.isEmpty(postInterceptors)) {
            postInterceptors = execution.getProcessModel().getPostInterceptors();
        }
        execInterceptors(postInterceptors,execution);
    }
    /**
     * 执行节点拦截器
     * @param execution
     */
    private void execInterceptors(String interceptors,Execution execution) {
        if(StrUtil.isEmpty(interceptors)) return;
        // 存在多个，英文逗号分割
        String [] interceptorArr =  interceptors.split(",");
        for (int i = 0; i < interceptorArr.length; i++) {
            String interceptor = interceptorArr[i];
            // 反射实例化
            FlowInterceptor flowInterceptor = ReflectUtil.newInstance(interceptor);
            if(flowInterceptor!=null){
                // 调用拦截器方法
                flowInterceptor.intercept(execution);
            }
        }
    }
    @Override
    public String toString() {
        return StrUtil.format("调用模型节点执行方法：model:{},name:{},displayName:{}", this.getClass().getSimpleName(), getName(),getDisplayName());
    }

}