package com.stitchcodes.system.manager;

import com.stitchcodes.common.utils.SpringUtils;
import com.stitchcodes.common.utils.ThreadUtils;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: stitch
 * @Date: 2023/5/7 13:49
 * @Description:
 */
public class AsyncManager {

    private static final String EXECUTE_SERVICE_BEAN = "scheduledExecutorService";

    /**
     * 操作延时时间 10ms
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private ScheduledExecutorService executor = SpringUtils.getBean(EXECUTE_SERVICE_BEAN);

    /**
     * 单例模式
     */
    private AsyncManager() {
    }

    private static AsyncManager me = new AsyncManager();

    /**
     * 获取
     *
     * @return
     */
    public static AsyncManager me() {
        return me;
    }

    /**
     * 执行任务
     *
     * @param timerTask 任务
     */
    public void execute(TimerTask timerTask) {
        this.executor.schedule(timerTask, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 终止异步操作线程池
     */
    public void shutdown() {
        ThreadUtils.shutdownAndAwaitTermination(this.executor);
    }


}
