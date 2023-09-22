package com.mldong.base;

import cn.hutool.core.lang.Dict;
import lombok.Builder;
import lombok.Data;

/**
 * @author mldong
 * @date 2022/7/22
 */
@Data
@Builder
public class LabelValueVO {
    private String label;
    private Object value;
    private Dict ext;
}
