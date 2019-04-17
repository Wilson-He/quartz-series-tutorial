package io.github.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

import java.util.Date;

/**
 * HelloJob
 *
 * @author Wilson
 * @date 2019/4/14
 */
public class HelloJobApp {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        HolidayCalendar excludeCalendar = new HolidayCalendar();
        excludeCalendar.addExcludedDate(new Date());
        scheduler.start();
        Trigger triggerA = TriggerBuilder.newTrigger()
                .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
                .withIdentity("class-a-detail","group2")
                .forJob("class-a-detail","group1")
                .usingJobData("name", "Wilson")
                .usingJobData("sex", "man")
//                .modifiedByCalendar("excludeNow")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(3))
                .build();
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(HelloJob.class)
                .storeDurably()
                .withIdentity("class-a-detail", "group1")
                .usingJobData("age","18")
                .usingJobData("name","Coco")
                .build();
        scheduler.addJob(jobDetail,true);
        scheduler.scheduleJob(triggerA);
        // 睡眠3秒，否则Trigger不会触发的情况会报错
        Thread.sleep(3000);
        // 添加Calendar限制并更新关联的Trigger
        scheduler.addCalendar("excludeNow", excludeCalendar, true, true);

    }
}
