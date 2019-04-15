package io.github.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * HelloJob
 *
 * @author Wilson
 * @date 2019/4/14
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        System.out.println(context.getTrigger().getJobKey());
        System.out.println("Hello Job");
    }
}
