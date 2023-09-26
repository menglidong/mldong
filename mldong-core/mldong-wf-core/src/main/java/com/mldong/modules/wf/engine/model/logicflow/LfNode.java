package com.mldong.modules.wf.engine.model.logicflow;

import cn.hutool.core.lang.Dict;
import lombok.Data;

import java.io.Serializable;
/**
 *
 * logicFlow节点
 * @author mldong
 * @date 2023/4/26
 */
@Data
public class LfNode implements Serializable {
    private String id; // 节点唯一id
    private String type; // 节点类型
    private int x; // 节点中心点x轴坐标
    private int y; // 节点中心点y轴坐标
    Dict properties; // 节点属性
    Dict text; // 节点文本
}