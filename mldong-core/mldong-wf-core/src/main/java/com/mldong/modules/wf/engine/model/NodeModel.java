package com.mldong.modules.wf.engine.model;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.mldong.modules.wf.engine.Action;
import com.mldong.modules.wf.engine.FlowInterceptor;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.enums.ProcessTaskPerformTypeEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public <T> List<T> getNextModels(Class<T> clazz) {
        List<T> models = new ArrayList<T>();
        // 记录已递归项，防止死循环
        Map<String,Object> temp = new HashMap();
        for(TransitionModel tm : this.getOutputs()) {
            addNextModels(models, tm, clazz, temp);
        }
        return models;
    }

    protected <T> void addNextModels(List<T> models, TransitionModel tm, Class<T> clazz,Map<String,Object> temp) {
        if(temp.get(tm.getTo())!=null) {
            return;
        }
        if(clazz.isInstance(tm.getTarget())) {
            models.add((T)tm.getTarget());
        } else {
            for(TransitionModel tm2 : tm.getTarget().getOutputs()) {
                temp.put(tm.getTo(), tm.getTarget());
                addNextModels(models, tm2, clazz,temp);
            }
        }
    }
    /**
     * 根据父节点模型、当前节点模型判断是否可退回。可退回条件：
     * 1、满足中间无fork、join、subprocess模型
     * 2、满足父节点模型如果为任务模型时，参与类型为any
     * @param parent 父节点模型
     * @return 是否可以退回
     */
    public static boolean canRejected(NodeModel current, NodeModel parent) {
        boolean result = false;
        for(TransitionModel tm : current.getInputs()) {
            NodeModel source = tm.getSource();
            if(source == parent) {
                return true;
            }
            if(source instanceof ForkModel
                    || source instanceof JoinModel
                    //|| source instanceof SubProcessModel
                    || source instanceof StartModel) {
                continue;
            }
            result = result || canRejected(source, parent);
        }
        return result;
    }
    @Override
    public String toString() {
        return StrUtil.format("调用模型节点执行方法：model:{},name:{},displayName:{}", this.getClass().getSimpleName(), getName(),getDisplayName());
    }

}