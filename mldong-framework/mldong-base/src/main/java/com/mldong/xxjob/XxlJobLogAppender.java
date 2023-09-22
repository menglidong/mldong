package com.mldong.xxjob;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.AppenderBase;
import com.xxl.job.core.context.XxlJobHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 *
 * 处理日志事件
 * @author mldong
 * @date 2023/7/10
 */
@Component
@ConditionalOnProperty(prefix = "xxl-job",name = "enabled",havingValue = "true")
public class XxlJobLogAppender extends AppenderBase<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        if (XxlJobHelper.getJobId() == -1) {
            return;
        }
        if (Level.ERROR.equals(iLoggingEvent.getLevel())) {
            ThrowableProxy throwableProxy = (ThrowableProxy) iLoggingEvent.getThrowableProxy();
            if (throwableProxy != null) {
                XxlJobHelper.log(throwableProxy.getThrowable());
            } else {
                XxlJobHelper.log(iLoggingEvent.getMessage());
            }
        } else {
            XxlJobHelper.log(iLoggingEvent.getMessage());
        }
    }
}