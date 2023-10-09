package com.mldong.modules.wf.engine.model;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.mldong.modules.wf.engine.CandidateHandler;
import com.mldong.modules.wf.engine.core.ServiceContext;
import com.mldong.modules.wf.entity.Candidate;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 流程模型
 * @author mldong
 * @date 2023/4/25
 */
@Data
public class ProcessModel extends BaseModel {
    private String type; // 流程定义分类
    private String instanceUrl; // 启动实例要填写的表单key
    private String expireTime; // 期待完成时间变量key
    private String instanceNoClass; // 实例编号生成器实现类
    private String preInterceptors; // 节点前置拦截器
    private String postInterceptors; // 节点后置拦截器
    // 流程定义的所有节点
    private List<NodeModel> nodes = new ArrayList<NodeModel>();
    // 流程定义的所有任务节点
    private List<TaskModel> tasks = new ArrayList<TaskModel>();

    /**
     * 获取开始节点
     * @return
     */
    public StartModel getStart() {
        StartModel startModel = null;
        for (int i = 0; i < nodes.size(); i++) {
            NodeModel nodeModel = nodes.get(i);
            if(nodeModel instanceof StartModel) {
                startModel = (StartModel) nodeModel;
                break;
            }
        }
        return startModel;
    }
    /**
     * 获取process定义的指定节点名称的节点模型
     * @param nodeName 节点名称
     * @return
     */
    public NodeModel getNode(String nodeName) {
        for(NodeModel node : nodes) {
            if(node.getName().equals(nodeName)) {
                return node;
            }
        }
        return null;
    }

    /**
     * 获取下一个任务节点模型集合
     * @param nodeName
     * @return
     */
    public List<TaskModel> getNextTaskModels(String nodeName) {
        List<TaskModel> res = new ArrayList<>();
        NodeModel nodeModel = getNode(nodeName);
        if(nodeModel == null) return res;
        // 获取所有输出边的目标节点
        List<NodeModel> nextNodeModelList = nodeModel.getOutputs().stream().map(item->{
            return item.getTarget();
        }).collect(Collectors.toList());
        nextNodeModelList.forEach(item->{
            if(item instanceof TaskModel) {
                res.add((TaskModel) item);
            }
        });
        if(res.isEmpty()) {
            // 如果下一个节点不存在任务节点，递归往下找
            nextNodeModelList.forEach(item->{
                List<TaskModel> taskModelList = getNextTaskModels(item.getName());
                res.addAll(taskModelList);
            });
        }
        return res;
    }

    /**
     * 获取下一个任务节点的候选人
     * @param nodeName
     * @return
     */
    public List<Candidate> getNextTaskModelCandidates(String nodeName) {
        List<Candidate> res = new ArrayList<>();
        List<TaskModel> nextTaskModels = getNextTaskModels(nodeName);
        nextTaskModels.forEach(item->{
            res.addAll(getCandidates(item));
        });
        return res;
    }
    /**
     * 根据任务模型获取候选人
     * @param taskModel
     * @return
     */
    public List<Candidate> getCandidates(TaskModel taskModel) {
        List<Candidate> res = new ArrayList<>();
        // 从上下文中查找候选人处理人
        List<CandidateHandler> handlerList = ServiceContext.findList(CandidateHandler.class);
        handlerList.forEach(handler->{
            // 通过候选从处理类获取候选人集合
            List<Candidate> candidateList = handler.handle(taskModel);
            if(candidateList!=null) {
                res.addAll(candidateList);
            }
        });
        // 通过候选人处理类获取修选人
        String candidateHandler = taskModel.getCandidateHandler();
        if(StrUtil.isNotEmpty(candidateHandler)) {
            CandidateHandler candidateHandlerClass = ReflectUtil.newInstance(candidateHandler);
            List<Candidate> candidateList = candidateHandlerClass.handle(taskModel);
            if(candidateList!=null) {
                res.addAll(candidateList);
            }
        }
        // 去重
        return res.stream().distinct().collect(Collectors.toList());
    }
    /**
     * 根据指定的节点类型返回流程定义中所有模型对象
     * @param clazz 节点类型
     * @param <T> 泛型
     * @return 节点列表
     */
    public <T> List<T> getModels(Class<T> clazz) {
        List<T> models = new ArrayList<T>();
        buildModels(models, getStart().getNextModels(clazz), clazz);
        return models;
    }

    private <T> void buildModels(List<T> models, List<T> nextModels, Class<T> clazz) {
        for(T nextModel : nextModels) {
            if(!models.contains(nextModel)) {
                models.add(nextModel);
                buildModels(models, ((NodeModel)nextModel).getNextModels(clazz), clazz);
            }
        }
    }
}
