package com.mldong.modules.cms.dto;

import com.mldong.modules.cms.entity.CmsArticle;
import io.swagger.annotations.ApiModelProperty;

public class CmsArticleWithExt extends CmsArticle {
    @ApiModelProperty(value = "栏目名称")
    private String categoryName;
    @ApiModelProperty(value = "栏目图标")
    private String categoryIcon;
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
}
