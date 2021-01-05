package com.mldong.modules.cms.dto;

import com.mldong.modules.cms.entity.CmsArticle;
import io.swagger.annotations.ApiModelProperty;

public class CmsArticleWithExt extends CmsArticle {
    @ApiModelProperty(value = "栏目名称")
    private String categoryName;
    @ApiModelProperty(value = "栏目图标")
    private String categoryIcon;
    @ApiModelProperty(value = "文章扩展信息配置")
    private String extArticleFormConfig;
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getExtArticleFormConfig() {
        return extArticleFormConfig;
    }

    public void setExtArticleFormConfig(String extArticleFormConfig) {
        this.extArticleFormConfig = extArticleFormConfig;
    }
}
