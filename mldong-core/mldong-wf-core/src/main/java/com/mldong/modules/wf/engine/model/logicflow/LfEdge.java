package com.mldong.modules.wf.engine.model.logicflow;

import cn.hutool.core.lang.Dict;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
/**
 *
 * logicFlow边
 * @author mldong
 * @date 2023/4/26
 */
@Data
public class LfEdge implements Serializable {
    private String id; // 边唯一id
    private String type; // 边类型
    private String sourceNodeId; // 源节点id
    private String targetNodeId; // 目标节点id
    private Dict properties; // 边属性
    private Dict text; // 边文本
    private LfPoint startPoint; // 边开始点坐标
    private LfPoint endPoint; // 边结束点坐标
    private List<LfPoint> pointsList; // 边所有点集合
}
