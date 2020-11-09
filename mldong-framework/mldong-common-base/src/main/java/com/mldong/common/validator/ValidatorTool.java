package com.mldong.common.validator;

import com.mldong.common.base.BaseMapper;
import com.mldong.common.base.CommonError;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;
import tk.mybatis.mapper.entity.Condition;

public class ValidatorTool {
    private ValidatorTool() {}

    /**
     * 校验字段唯一性
     * @param baseMapper
     * @param clazz 实体类
     * @param property 属性
     * @param value 值
     * @return
     */
    public static boolean checkUnique(BaseMapper<?> baseMapper, Class<?> clazz, String property, Object value) {
        Condition condition = new Condition(clazz);
        condition.createCriteria()
                .andEqualTo(property, value);
        int count = baseMapper.selectCountByCondition(condition);
        if(count > 0) {
            throw new BizException(new CommonError() {
                @Override
                public int getValue() {
                    return GlobalErrEnum.GL99990100.getValue();
                }

                @Override
                public String getName() {
                    return property + "字段唯一，不可重复添加";
                }
            });
        }
        return true;
    }
    /**
     * 校验字段唯一性
     * @param baseMapper
     * @param clazz 实体类
     * @param property 属性
     * @param value 值
     * @param currentId 当前记录id
     * @return
     */
    public static boolean checkUniqueOnUpdate(BaseMapper<?> baseMapper, Class<?> clazz, String property, Object value, Long currentId) {
        Condition condition = new Condition(clazz);
        condition.createCriteria()
                .andNotEqualTo("id", currentId)
                .andEqualTo(property, value);
        int count = baseMapper.selectCountByCondition(condition);
        if(count > 0) {
            throw new BizException(new CommonError() {
                @Override
                public int getValue() {
                    return GlobalErrEnum.GL99990100.getValue();
                }
                @Override
                public String getName() {
                    return property + "字段唯一，请更换别的值";
                }
            });
        }
        return true;
    }
}
