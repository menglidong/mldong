package com.mldong.web;

import cn.hutool.core.lang.Dict;

/**
 * 查询参数
 * @author mldong
 * @date 2023/9/20
 */
public class QueryParamHolder {
    private final static ThreadLocal<Dict> QUERY_PARAM_THEAD_LOCAL = new ThreadLocal<>();
    public static void set(Dict dict) {
        QUERY_PARAM_THEAD_LOCAL.set(dict);
    }
    public static Dict me() {
        return QUERY_PARAM_THEAD_LOCAL.get();
    }
    public static void remove() {
        QUERY_PARAM_THEAD_LOCAL.remove();
    }
}
