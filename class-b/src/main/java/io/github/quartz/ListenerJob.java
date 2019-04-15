package io.github.quartz;

import com.alibaba.fastjson.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * ListenerJob
 *
 * @author Wilson
 * @date 2019/4/14
 */
public class ListenerJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("ListenerJob");
    }
}
