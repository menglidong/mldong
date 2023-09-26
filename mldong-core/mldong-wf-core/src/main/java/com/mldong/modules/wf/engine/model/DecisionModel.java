package com.mldong.modules.wf.engine.model;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.expression.ExpressionUtil;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.enums.err.WfErrEnum;
import com.mldong.modules.wf.engine.ex.JFlowException;
import com.mldong.modules.wf.engine.DecisionHandler;
import lombok.Data;
/**
 *
 * 决策模型
 * @author mldong
 * @date 2023/4/25
 */
@Data
public class DecisionModel extends NodeModel {
    private String expr; // 决策表达式
    private String handleClass; // 决策处理类
    @Override
    public void exec(Execution execution) {
        // 执行决策节点自定义执行逻辑
        boolean isFound = false;
        String nextNodeName = null;
        if(StrUtil.isNotEmpty(expr)) {
            Object obj = ExpressionUtil.eval(expr, execution.getArgs());
            nextNodeName = Convert.toStr(obj,"");
        } else if(StrUtil.isNotEmpty(handleClass)) {
            DecisionHandler decisionHandler = ReflectUtil.newInstance(handleClass);
            nextNodeName = decisionHandler.decide(execution);
        }
        for(TransitionModel transitionModel: getOutputs()){
            if (StrUtil.isNotEmpty(transitionModel.getExpr()) && Convert.toBool(ExpressionUtil.eval(transitionModel.getExpr(), execution.getArgs()), false)) {
                // 决策节点输出边存在表达式，则使用输出边的表达式，true则执行
                isFound = true;
                transitionModel.setEnabled(true);
                transitionModel.execute(execution);
            } else if(transitionModel.getTo().equalsIgnoreCase(nextNodeName)) {
                // 找到对应的下一个节点
                isFound = true;
                transitionModel.setEnabled(true);
                transitionModel.execute(execution);
            }
        }
        if(!isFound) {
            // 找不到下一个可执行路线
            throw new JFlowException(WfErrEnum.NOT_FOUND_NEXT_NODE);
        }
    }
}