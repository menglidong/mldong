package com.mldong.modules.wf.engine.model.logicflow;

import lombok.Data;

import java.io.Serializable;
/**
 *
 * logicFlow坐标
 * @author mldong
 * @date 2023/4/26
 */
@Data
public class LfPoint implements Serializable {
    private int x; // x轴坐标
    private int y; // y轴坐标
}
