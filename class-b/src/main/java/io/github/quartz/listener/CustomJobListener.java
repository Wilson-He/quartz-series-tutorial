package io.github.quartz.listener;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.listeners.JobListenerSupport;

/**
 * @author: Wilson
 * @date: 2019/4/15
 **/
public class CustomJobListener extends JobListenerSupport {
    @Override
    public String getName() {
        return "customJobName";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        JobKey key = context.getJobDetail().getKey();
        System.err.println("Job name:" + key.getName() + "\tgroup:" + key.getGroup());
    }
}
