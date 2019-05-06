package com.cxd.cool.task;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ChenxdJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("------------>>>>>"+DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

}
