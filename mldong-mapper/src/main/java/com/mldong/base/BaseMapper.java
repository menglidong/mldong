package com.mldong.base;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.RowBoundsMapper;
/**
 * mapper基类
 * @author mldong
 *
 * @param <T>
 */
public interface BaseMapper<T> extends tk.mybatis.mapper.common.BaseMapper<T>,
		ConditionMapper<T>, RowBoundsMapper<T>, Marker {

}
