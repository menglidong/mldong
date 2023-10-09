package com.mldong;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.expression.ExpressionUtil;

/**
 * @author mldong
 * @date 2023/10/7
 */
public class MainTest {
    public static void main(String[] args) {
        System.err.println(ExpressionUtil.eval("#day>1", Dict.of("day",2)));
    }
}
