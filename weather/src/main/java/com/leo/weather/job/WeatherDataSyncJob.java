package com.leo.weather.job;

import com.leo.weather.entity.City;
import com.leo.weather.service.CityDataService;
import com.leo.weather.service.WeatherDataService;
import com.leo.weather.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

//只有一个execute方法
@Slf4j
public class WeatherDataSyncJob extends QuartzJobBean {
    @Autowired
    private WeatherDataService weatherDataService;
    @Autowired
    private CityDataService cityDataService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("【定时任务】{}---更新天气数据", LocalDateTime.now());
        List<City> list = cityDataService.listCity();
        if (list != null) {
            list.stream().forEach(
                    e -> weatherDataService.syncDataByCityCode(e.getId())
            );
        }

    }
}
