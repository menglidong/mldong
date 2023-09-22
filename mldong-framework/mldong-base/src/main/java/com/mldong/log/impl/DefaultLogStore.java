package com.mldong.log.impl;

import com.mldong.log.ILogStore;
import com.mldong.log.LogParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultLogStore implements ILogStore {
    @Override
    public void save(LogParam param) {
    //[requestNo][userId][userName][url][ip][brower][time][body][params][resData]
        log.debug("[{}][{}][{}][{}][{}][{}][{}][{}][{}][{}]",
                param.getRequestNo(),
                param.getUserId(),
                param.getUserName(),
                param.getUrl(),
                param.getIp(),
                param.getBrower(),
                param.getTime(),
                param.getBody(),
                param.getParams(),
                param.getResData()
        );
    }
}
