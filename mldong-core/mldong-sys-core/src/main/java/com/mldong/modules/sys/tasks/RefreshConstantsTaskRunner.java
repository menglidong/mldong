package com.mldong.modules.sys.tasks;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mldong.base.YesNoEnum;
import com.mldong.context.constant.ConstantContext;
import com.mldong.modules.sys.entity.Config;
import com.mldong.modules.sys.service.ConfigService;
import com.mldong.timer.TimerTaskRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 定时器：用来根据sys_config表中的配置，刷新ConstantContextHolder中的缓存
 * <p>
 * 防止由于直接改动数据库，而调用缓存常量时，产生数据不一致问题
 * @author mldong
 * @date 2023-09-21
 */
@Component
public class RefreshConstantsTaskRunner implements TimerTaskRunner {

    private static final Log log = Log.get();

    @Resource
    private ConfigService configService;

    @Override
    public void action() {
        log.info("====开始同步配置到缓存====");
        // 查询库中的所有配置
        LambdaQueryWrapper<Config> configLambdaQueryWrapper = new LambdaQueryWrapper<>();
        configLambdaQueryWrapper.eq(Config::getEnabled, YesNoEnum.YES);
        configLambdaQueryWrapper.select(Config::getCode, Config::getContent);
        List<Config> list = configService.list(configLambdaQueryWrapper);
        // 所有配置添加到缓存中，覆盖已有配置
        list.forEach(config -> ConstantContext.putConstant(config.getCode(), config.getContent()));
        log.info("====同步配置到缓存结束====");
    }

    @Override
    public String getCron() {
        return "0 0/1 * * * ?";
    }

    @Override
    public boolean isXxlJob() {
        return false;
    }

}
