package com.qg.task;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

@Configuration
public class MyTaskThreadPoolConfigurer  implements AsyncConfigurer {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        //调度器
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.initialize();
        executor.setPoolSize(5);
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return threadPoolTaskScheduler();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
