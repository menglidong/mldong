package com.mldong.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mldong.common.validator.Groups;
import javax.validation.constraints.*;
import com.mldong.modules.sys.entity.SysNotice;

/**
 * <p>接收请求参数实体</p>
 * <p>Table: sys_notice - 通知公告</p>
 * @since 2020-10-27 05:10:53
 */
@ApiModel(description="通知公告")
public class SysNoticeParam{

	@ApiModelProperty(value="编号-更新时必填")
	@NotNull(message="编号不能为空",groups={Groups.Update.class})
    private Long id;
    @ApiModelProperty(value = "公告标题",required=false)
    private String title;
    @ApiModelProperty(value = "公告类型(10->通知|TZ,20->公告|GG)",required=false)
    private SysNotice.TypeEnum type;
    @ApiModelProperty(value = "公告内容",required=false)
    private String content;
    /**
     * 获取编号
     *
     */
    public Long getId(){
        return this.id;
    }
	 /**
     * 设置编号
     *
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }
    /**
     * 获取公告标题
     *
     */
    public String getTitle(){
        return this.title;
    }
	 /**
     * 设置公告标题
     *
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * 获取公告类型(10->通知|TZ,20->公告|GG)
     *
     */
    public SysNotice.TypeEnum getType(){
        return this.type;
    }
	 /**
     * 设置公告类型(10->通知|TZ,20->公告|GG)
     *
     * @param type
     */
    public void setType(SysNotice.TypeEnum type){
        this.type = type;
    }
    /**
     * 获取公告内容
     *
     */
    public String getContent(){
        return this.content;
    }
	 /**
     * 设置公告内容
     *
     * @param content
     */
    public void setContent(String content){
        this.content = content;
    }
}