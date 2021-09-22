package com.mldong.common.tool;

import com.mldong.common.base.CommonError;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.exception.BizException;

import java.util.List;

public class AssertTool {
    private AssertTool(){}

    /**
     * 抛出业务异常
     * @param error
     */
    public static void throwBiz(CommonError error) {
        throw new BizException(error);
    }
    /**
     * 抛出业务异常-自定义消息
     * @param error
     */
    public static void throwBiz(CommonError error,String msg) {
        throw new BizException(new CommonError() {
            @Override
            public int getValue() {
                return error.getValue();
            }

            @Override
            public String getName() {
                return msg;
            }
        });
    }
    /**
     * 抛出业务异常-自定义消息
     * @param code 错误码
     * @param  msg 消息
     */
    public static void throwBiz(int code,String msg) {
        throw new BizException(new CommonError() {
            @Override
            public int getValue() {
                return code;
            }

            @Override
            public String getName() {
                return msg;
            }
        });
    }
    /**
     * 空值判断
     * @param object
     * @param msg
     */
    public static void notNull(Object object ,String msg) {
        if(object == null) {
            throw new BizException(new CommonError() {
                @Override
                public int getValue() {
                    return GlobalErrEnum.GL99990100.getValue();
                }

                @Override
                public String getName() {
                    return msg;
                }
            });
        }
        if(object instanceof String) {
            if(StringTool.isEmpty((String)object)){
                throw new BizException(new CommonError() {
                @Override
                public int getValue() {
                    return GlobalErrEnum.GL99990100.getValue();
                }

                @Override
                public String getName() {
                    return msg;
                }
            });
            }
        } else if(object instanceof List) {
            List list = (List)object;
            if(list.isEmpty()){
                throw new BizException(new CommonError() {
                    @Override
                    public int getValue() {
                        return GlobalErrEnum.GL99990100.getValue();
                    }

                    @Override
                    public String getName() {
                        return msg;
                    }
                });
            }
        }else if(object instanceof Object []) {
            Object [] list = (Object [])object;
            if(list.length==0){
                throw new BizException(new CommonError() {
                    @Override
                    public int getValue() {
                        return GlobalErrEnum.GL99990100.getValue();
                    }

                    @Override
                    public String getName() {
                        return msg;
                    }
                });
            }
        }
    }
}
