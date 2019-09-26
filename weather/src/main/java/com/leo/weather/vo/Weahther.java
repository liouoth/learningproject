package com.leo.weather.vo;

import lombok.Data;

import java.util.List;

@Data
public class Weahther {
    private Yesterday yesterday;
    private String city;
    private List<Forecast> forecast;
    private String ganmao;
    private String wendu;
}
