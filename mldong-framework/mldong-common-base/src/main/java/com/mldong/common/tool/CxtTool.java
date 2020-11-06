package com.mldong.common.tool;

import org.springframework.context.ApplicationContext;

public class CxtTool {
    private static ApplicationContext applicationContext = null;

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        CxtTool.applicationContext = applicationContext;
    }
}
