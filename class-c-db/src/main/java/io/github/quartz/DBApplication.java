package io.github.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author: Wilson
 * @date: 2019/4/16
 **/
@SpringBootApplication
@RestController
@RequestMapping("/")
public class DBApplication {
    @Resource
    QuartzProperties quartzProperties;

    public static void main(String[] args) {
        SpringApplication.run(DBApplication.class);
    }

    @GetMapping("/get")
    public void init() {
        System.out.println(quartzProperties.getProperties().get("org.quartz.job-store.table-prefix"));
    }
}
