package com.mldong.mp;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.mldong.auth.LoginUser;
import com.mldong.base.YesNoEnum;
import com.mldong.web.LoginUserHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectionException;

import java.util.Date;

/**
 * 自定义sql字段填充器，自动填充创建修改相关字段
 *
 * @author mldong
 * @date 2023-09-21
 */
public class CustomMetaObjectHandler implements MetaObjectHandler {

    private static final Log log = Log.get();

    private static final String CREATE_USER = "createUser";

    private static final String CREATE_TIME = "createTime";

    private static final String UPDATE_USER = "updateUser";

    private static final String UPDATE_TIME = "updateTime";
    private static final String IS_DELETE = "isDeleted";
    private static final String SORT = "sort";

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            //为空则设置createUser（BaseEntity)
            if (metaObject.hasSetter(CREATE_USER)) {
                Object createUser = metaObject.getValue(CREATE_USER);
                if (ObjectUtil.isNull(createUser)) {
                    if(String.class.equals(metaObject.getGetterType(CREATE_USER))) {
                        setFieldValByName(CREATE_USER, StrUtil.toString(this.getUserUniqueId()), metaObject);
                    } else {
                        setFieldValByName(CREATE_USER, this.getUserUniqueId(), metaObject);
                    }
                }
            }
            //为空则设置createTime（BaseEntity)
            if (metaObject.hasSetter(CREATE_TIME)) {
                Object createTime = metaObject.getValue(CREATE_TIME);
                if (ObjectUtil.isNull(createTime)) {
                    setFieldValByName(CREATE_TIME, new Date(), metaObject);
                }
            }
            //为空则设置updateUser（BaseEntity)
            if (metaObject.hasSetter(UPDATE_USER)) {
                Object updateUser = metaObject.getValue(UPDATE_USER);
                if (ObjectUtil.isNull(updateUser)) {
                    if(String.class.equals(metaObject.getGetterType(UPDATE_USER))) {
                        setFieldValByName(UPDATE_USER, StrUtil.toString(this.getUserUniqueId()), metaObject);
                    } else {
                        setFieldValByName(UPDATE_USER, this.getUserUniqueId(), metaObject);
                    }
                }
            }

            //为空则设置updateTime（BaseEntity)
            if (metaObject.hasSetter(UPDATE_TIME)) {
                Object updateTime = metaObject.getValue(UPDATE_TIME);
                if (ObjectUtil.isNull(updateTime)) {
                    setFieldValByName(UPDATE_TIME, new Date(), metaObject);
                }
            }
            if (metaObject.hasSetter(IS_DELETE)) {
                Object isDeleted = metaObject.getValue(IS_DELETE);
                if (ObjectUtil.isNull(isDeleted)) {
                    setFieldValByName(IS_DELETE, YesNoEnum.NO.getCode(), metaObject);
                }
            }
            if (metaObject.hasSetter(SORT)) {
                Object sort = metaObject.getValue(SORT);
                if (ObjectUtil.isNull(sort)) {
                    setFieldValByName(SORT, DateUtil.currentSeconds(), metaObject);
                }
            }
        } catch (ReflectionException e) {
            log.error(e);
            log.warn(">>> CustomMetaObjectHandler处理过程中无相关字段，不做处理");
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            //设置updateUser（BaseEntity)
            setFieldValByName(UPDATE_USER, this.getUserUniqueId(), metaObject);
            //设置updateTime（BaseEntity)
            setFieldValByName(UPDATE_TIME, new Date(), metaObject);
        } catch (ReflectionException e) {
            log.warn(">>> CustomMetaObjectHandler处理过程中无相关字段，不做处理");
        }
    }

    /**
     * 获取用户唯一id
     */
    private Long getUserUniqueId() {
        LoginUser loginUser = LoginUserHolder.me();
        if (loginUser != null) {
            return loginUser.getId();
        }
        return 0L;
    }
}
