package io.github.quartz;

import com.alibaba.fastjson.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * HelloJob
 *
 * @author Wilson
 * @date 2019/4/14
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("Hello Job");
        System.out.println("trigger:" + context.getTrigger().getKey().getName());
        JSONObject merged = new JSONObject();
        context.getMergedJobDataMap().forEach(merged::put);
        System.out.println(merged);
        JSONObject jobMap = new JSONObject();
        context.getMergedJobDataMap().forEach(jobMap::put);
        System.out.println("jobMap: " + jobMap);
    }
}
