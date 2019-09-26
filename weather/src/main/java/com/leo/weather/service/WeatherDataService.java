package com.leo.weather.service;

import com.leo.weather.vo.WeatherResponse;

public interface WeatherDataService {
    WeatherResponse getWeatherDataByCityName(String cityName);
    WeatherResponse getWeatherDataByCityCode(String cityCode);
    void syncDataByCityCode(String cityCode);
}
