package com.stitchcodes.system.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;

/**
 * @Author: stitch
 * @Date: 2023/5/7 19:45
 * @Description: 保证应用退出时能关闭后台线程
 */
public class ShutdownManager {

    private static final Logger log = LoggerFactory.getLogger(ShutdownManager.class);


    @PreDestroy
    public void destroy() {
        shutdownAsyncManager();
    }

    /**
     * 关闭异步任务线程池
     */
    private void shutdownAsyncManager() {
        try {
            log.info("==========close async thread pool==========");
            AsyncManager.me().shutdown();
        } catch (Exception e) {
            log.error("close async thread pool failed", e);
        }
    }
}
