package com.leo.weather.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WeatherResponse {
    private Weahther data;
    private Integer status;
    private String desc;
}
