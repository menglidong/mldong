package com.mldong.common.logger;

/**
 * 保存请求日志接口
 */
public interface IRequestLogStore {
    public int saveRequestLog(LoggerModel model);
}
