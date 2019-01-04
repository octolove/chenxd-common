package com.cxd.cool.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 */
@Component
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private TaskExecutor taskExecutor;

    @Scheduled(cron = "0 0 12 * * ?")
    @Async("taskProcessExecutor")
    public void collectData() {
        System.out.println("......collectData......" + taskExecutor);
    }
}
