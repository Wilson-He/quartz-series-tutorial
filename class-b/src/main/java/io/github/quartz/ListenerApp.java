package io.github.quartz;

import io.github.quartz.listener.CustomJobListener;
import io.github.quartz.listener.CustomTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.NameMatcher;
import org.quartz.impl.matchers.OrMatcher;
import org.quartz.impl.matchers.StringMatcher;

/**
 * ListenerJob
 *
 * @author Wilson
 * @date 2019/4/14
 */
public class ListenerApp {
    public static void main(String[] args) throws SchedulerException, InterruptedException {

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.getListenerManager().addTriggerListener(new CustomTriggerListener(), OrMatcher.or(NameMatcher.nameContains("triggerA"), NameMatcher.nameContains("triggerB")));
        scheduler.getListenerManager().addJobListener(new CustomJobListener());
        scheduler.start();
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(ListenerJob.class)
                .withIdentity("class-a-detail", "group1")
                .usingJobData("age", "18")
                .usingJobData("name", "Coco")
                .build();
        Trigger triggerA = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
//                .withIdentity("class-a-detail","group")
                .forJob(jobDetail)
                .withIdentity("trigger-b", "group-b")
                .usingJobData("name", "Wilson")
                .usingJobData("sex", "man")
//                关联Calendar
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(3))
                .build();
        scheduler.scheduleJob(jobDetail, triggerA);
    }
}
