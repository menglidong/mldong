package com.mldong.timer;

/**
 *
 * 定时器执行者
 * <p>
 * 定时器都要实现本接口，并需要把实现类加入到spring容器中 *
 * @author mldong
 * @date 2023-09-21
 */
public interface TimerTaskRunner {

    /**
     * 任务执行的具体内容
     *
、     */
    void action();
    String getCron();
    boolean isXxlJob();
}
