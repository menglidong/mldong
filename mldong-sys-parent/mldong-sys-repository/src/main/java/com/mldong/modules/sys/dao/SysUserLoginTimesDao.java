package com.mldong.modules.sys.dao;
import com.mldong.modules.sys.entity.SysUserLoginTimes;
import com.mldong.common.base.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>持久层-可自定义层</p>
 * <p>用户登录次数</p>
 *
 * @since 2022-04-23 05:22:32
 */
@Repository
public interface SysUserLoginTimesDao extends BaseMapper<SysUserLoginTimes> {

}