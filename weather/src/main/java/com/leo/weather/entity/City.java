package com.leo.weather.entity;

import lombok.Data;

@Data
public class City {
    private String id;
    private String cityEn;
    private String cityZh;
    private String provinceEn;
    private String provinceZh;
    private String leaderEn;
    private String leaderZh;
    private String lat;
    private String lon;
}
