package io.github.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(HelloJob.class)
                .withIdentity("class-a-detail","group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startNow()
                .withIdentity("class-a-detail","group")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .withRepeatCount(5))
                .build();
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
