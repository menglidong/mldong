package com.m.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CaptchaConfigurationSelector.class)
public @interface EnableCaptcha {
    AdviceMode mode() default AdviceMode.PROXY;
    int order() default Ordered.HIGHEST_PRECEDENCE;
}
