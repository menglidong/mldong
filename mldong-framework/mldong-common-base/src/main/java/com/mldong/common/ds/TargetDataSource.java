package com.mldong.common.ds;

import java.lang.annotation.*;
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value() default "dataSource";
}
