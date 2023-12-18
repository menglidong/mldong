package com.mldong.base;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SwaggerDocket {
    private String groupName;
    private String basePackage;
}