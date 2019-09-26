package com.leo.weather.controller;

import com.leo.weather.service.CityDataService;
import com.leo.weather.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/weather")
public class WeatherDataController {
    @Autowired
    private WeatherDataService weatherDataService;
    @Autowired
    private CityDataService cityDataService;

    @GetMapping("/report/{cityCode}")
    public ModelAndView getWeatherReport(@PathVariable String cityCode, Map<String, Object> map) {
        map.put("title", "天气预报");
        map.put("cityCode", cityCode);
        map.put("weatherRes",weatherDataService.getWeatherDataByCityCode(cityCode));
        map.put("cityList", cityDataService.listCity());
        return new ModelAndView("report", map);
    }


}
