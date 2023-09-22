package com.mldong.listener;

import com.mldong.web.QueryParamHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * 用于删除请求线程中的绑定的查询参数
 * @author mldong
 * @date 2023/09/21
 */
@Slf4j
public class RemoveQueryParamListener implements ApplicationListener<ServletRequestHandledEvent> {

    @Override
    public void onApplicationEvent(ServletRequestHandledEvent event) {
        QueryParamHolder.remove();
    }

}