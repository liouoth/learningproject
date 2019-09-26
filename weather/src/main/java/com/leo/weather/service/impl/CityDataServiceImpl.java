package com.leo.weather.service.impl;

import com.leo.weather.entity.City;
import com.leo.weather.service.CityDataService;
import com.leo.weather.util.JsonUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CityDataServiceImpl implements CityDataService {
    @Override
    public List<City> listCity() {
        List<City> list = null;
        try {
            list = JsonUtils.jsonFileToObjectList("/Users/maowenqing/workspace/git/learningproject/weather/out/production/resources/cityZ.json", City.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
