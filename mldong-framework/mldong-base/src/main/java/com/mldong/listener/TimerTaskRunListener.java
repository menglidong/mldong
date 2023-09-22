package com.mldong.listener;

import cn.hutool.core.util.StrUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import cn.hutool.extra.spring.SpringUtil;
import com.mldong.timer.TimerTaskRunner;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

import java.util.Map;

/**
 *
 * 项目定时任务启动的listener
 * @author mldong
 * @date 2023-09-21
 */
public class TimerTaskRunListener implements ApplicationListener<ApplicationStartedEvent>, Ordered {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {

        Map<String, TimerTaskRunner> timerTaskRunnerMap = SpringUtil.getBeansOfType(TimerTaskRunner.class);
        timerTaskRunnerMap.forEach((name,timerTaskRunner)->{
            if(!timerTaskRunner.isXxlJob() && StrUtil.isNotEmpty(timerTaskRunner.getCron())){
                // 定义hutool的任务
                Task task = () -> {
                    timerTaskRunner.action();
                };
                // 开始执行任务
                CronUtil.schedule(timerTaskRunner.getClass().toString(), timerTaskRunner.getCron(), task);
            }
        });
        // 设置秒级别的启用
        CronUtil.setMatchSecond(true);
        // 启动定时器执行器
        CronUtil.start();
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
