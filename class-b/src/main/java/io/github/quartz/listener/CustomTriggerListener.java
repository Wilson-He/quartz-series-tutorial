package io.github.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.listeners.TriggerListenerSupport;

/**
 * @author: Wilson
 * @date: 2019/4/15
 **/
public class CustomTriggerListener extends TriggerListenerSupport {
    @Override
    public String getName() {
        return "customTriggerName";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        TriggerKey triggerKey = trigger.getKey();
        System.err.println("trigger name:" + triggerKey.getName() + "\tgroup:" + triggerKey.getGroup());
    }
}
