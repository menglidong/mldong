package com.m.annotation;

import com.m.config.CaptchaConfiguration;
import com.m.controller.CaptchaController;
import com.m.filter.CaptchaFilter;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

public class CaptchaConfigurationSelector extends AdviceModeImportSelector<EnableCaptcha> {
    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        return new String[]{CaptchaConfiguration.class.getName(), CaptchaController.class.getName(), CaptchaFilter.class.getName()};
    }
}
