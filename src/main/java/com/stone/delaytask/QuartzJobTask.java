package com.stone.delaytask;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author chen
 * @create 2022-07-24 17:58
 *
 * 基于Quarz实现
 *
 * 优点:简单，支持集群
 *
 * 缺点:
 * 对服务内存要求高
 * 延时不太准确，可能到下一个任务后才能执行
 * 扫描频繁，对数据库损耗大
 *
 **/

public class QuartzJobTask implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行任务....");
    }

    public static void main(String[] args) throws SchedulerException {
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobTask.class)
                .withIdentity("job1", "group1")
                .build();

        //创建触发任务
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger1", "group2")
                .withSchedule(
                        SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever()
                )
                .build();


        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

    }
}
