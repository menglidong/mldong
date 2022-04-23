package com.mldong.swagger.config;

public class SwaggerDocket {
    private String groupName;
    private String basePackage;

    public SwaggerDocket(String groupName, String basePackage) {
        this.groupName = groupName;
        this.basePackage = basePackage;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
