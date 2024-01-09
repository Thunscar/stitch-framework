package com.stitchcodes.system.config;

import com.stitchcodes.common.utils.ThreadUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: stitch
 * @Date: 2023/5/7 15:04
 * @Description: 线程池配置类
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 核心线程池大小
     */
    private int corePoolSize = 50;

    private int maxPoolSize = 200;
    private int queueCapacity = 1000;
    private int keepAliveSeconds = 300;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }

    /**
     * 执行周期性任务或定时任务
     *
     * @return 任务执行器
     */
    @Bean("scheduledExecutorService")
    public ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(corePoolSize, new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build(), new ThreadPoolExecutor.CallerRunsPolicy()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                ThreadUtils.printThreadException(r, t);
            }
        };
    }
}
