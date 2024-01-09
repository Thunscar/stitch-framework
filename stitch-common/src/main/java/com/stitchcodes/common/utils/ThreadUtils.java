package com.stitchcodes.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @Author: stitch
 * @Date: 2023/5/7 14:42
 * @Description: 线程工具类
 */
public class ThreadUtils {

    private static final Logger log = LoggerFactory.getLogger(ThreadUtils.class);

    /**
     * 停止线程池
     *
     * @param executor 线程池
     */
    public static void shutdownAndAwaitTermination(ExecutorService executor) {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(120, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                    if (!executor.awaitTermination(120, TimeUnit.SECONDS)) {
                        log.error("Executor Service Did not Be Terminated");
                    }
                }
            } catch (InterruptedException ie) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 打印线程异常信息
     */
    public static void printThreadException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (CancellationException ce) {
                t = ce;
            }
        }
        if (t != null) {
            log.error(t.getMessage(), t);
        }
    }
}
