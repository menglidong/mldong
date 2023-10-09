package com.mldong.modules.sys.api;

import cn.hutool.core.lang.Dict;

import java.util.Map;

/**
 * 系统用户api
 * @author mldong
 * @date 2023/10/7
 */
public interface UserApi {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    Dict findById(Long id);
}
