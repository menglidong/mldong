package com.mldong.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class CommonResult<T> {
    @ApiModelProperty(value = "状态码（0：成功，其他失败）")
    private int code;
    @ApiModelProperty(value = "消息描述")
    private String msg;
    @ApiModelProperty(value = "返回的数据")
    private T data;

    public CommonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功-提交类
     * @return
     */
    public static CommonResult<?> ok() {
        return new CommonResult<>(0, "成功", null );
    }
    /**
     * 返回成功-提交类
     * @param msg
     * @return
     */
    public static CommonResult<?> ok(String msg) {
        return new CommonResult<>(0, msg, null );
    }
    /**
     * 返回成功-提交类
     * @param code
     * @param msg
     * @return
     */
    public static CommonResult<?> fail(int code, String msg) {
        return new CommonResult<>(code, msg, null );
    }
    /**
     * 返回成功-提交类
     * @param errEnum
     * @return
     */
    public static CommonResult<?> fail(ErrEnum errEnum) {
        return fail(errEnum.getCode(), errEnum.getMessage());
    }
    /**
     * 返回成功-提交类
     * @param msg
     * @return
     */
    public static CommonResult<?> fail(String msg) {
        return fail(99999999, msg);
    }
    /**
     * 返回成功-提交类
     * @return
     */
    public static CommonResult data(Object data) {
        return new CommonResult(0, "成功", data );
    }
}
