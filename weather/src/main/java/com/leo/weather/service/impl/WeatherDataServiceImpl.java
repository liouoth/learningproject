package com.leo.weather.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.leo.weather.service.WeatherDataService;
import com.leo.weather.vo.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final long TIME_OUT = 1800L;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    //公共的url
    private static final String COMMON_URL = "http://wthrcdn.etouch.cn/weather_mini?";

    @Override
    public WeatherResponse getWeatherDataByCityName(String cityName) {
        System.out.println(COMMON_URL.concat("city=").concat(cityName));
        return doGetWeatherData(COMMON_URL.concat("city=").concat(cityName));
    }

    @Override
    public WeatherResponse getWeatherDataByCityCode(String cityCode) {
        return doGetWeatherData(COMMON_URL.concat("citykey=").concat(cityCode));
    }

    //定时缓存天气数据
    @Override
    public void syncDataByCityCode(String cityCode) {
        this.doGetWeatherData(COMMON_URL.concat("citykey=").concat(cityCode));
    }

    //整合redis
    private WeatherResponse doGetWeatherData(String url){
        String key = url;
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)){
            WeatherResponse response = JSON.parseObject(ops.get(key),WeatherResponse.class);
            return response;
        }else {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            WeatherResponse response = null;
            if (responseEntity.getStatusCode().value() == 200){
                ops.set(key,responseEntity.getBody(),TIME_OUT, TimeUnit.SECONDS);
                response =  JSON.parseObject(responseEntity.getBody(),WeatherResponse.class);
            }
            return response;
        }
    }

}
