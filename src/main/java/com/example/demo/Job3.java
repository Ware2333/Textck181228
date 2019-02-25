package com.example.demo;

import org.quartz.Job;
//import org.quartz.JobDataMap;
//import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class Job3 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        JobDetail detail = context.getJobDetail();
//        JobDataMap jobData = detail.getJobDataMap();
//        Object list = jobData.get("list");
        System.out.println("---------------");
    }

}
