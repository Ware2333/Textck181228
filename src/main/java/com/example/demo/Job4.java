package com.example.demo;

import java.util.Arrays;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class Job4 {

    public static void main(String[] args) throws Exception {
        JobBuilder jobB = JobBuilder.newJob(Job3.class);
        JobDataMap jobData = new JobDataMap();
        jobData.put("list", Arrays.asList("aa", "bb", "cc"));
        jobB.usingJobData(jobData);
        JobDetail job = jobB.build();

        TriggerBuilder<Trigger> tgrB = TriggerBuilder.newTrigger();
        tgrB.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3));
        // tgrB.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"));
        Trigger trigger = tgrB.build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();

        // Thread.sleep(10000);// 运行一段时间后关闭
        // scheduler.shutdown(true);
    }

    public static void main2(String[] args) {
//        FormatScheduler fs = FormatScheduler.getInit(Job3.class, null);
//        // fs.startSimpleTrigger(intervalSeconds);
//        fs.startCronTrigger("0/2 * * * * ?");
    }

}
