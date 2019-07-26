package com.cxd.cool.task;

import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class TaskJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("---------定时任务--->>>>>" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

}
