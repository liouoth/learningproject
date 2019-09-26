package com.leo.weather.configuration;

import com.leo.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {
    //JobDetail
    @Bean
    public JobDetail weatherDataSyncJobDetail(){
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob")
        .storeDurably().build();
    }

    //Trigger触发
    @Bean
    public Trigger weatherDataSyncTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1800).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail()).withIdentity("weatherDataSyncTrigger")
                .withSchedule(scheduleBuilder).build();
    }
}
