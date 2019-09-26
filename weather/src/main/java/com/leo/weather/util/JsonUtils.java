package com.leo.weather.util;

import com.alibaba.fastjson.JSONReader;
import com.leo.weather.entity.City;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    /** 读取json文件
     *  将json文件转换成List<T>对象列表
     * */
    public static <T> List<T> jsonFileToObjectList(String jsonUrl,Class<T> clazz) throws IOException {
        FileReader fileReader = new FileReader(jsonUrl);
        List<T> resultList = new ArrayList<>();
        if (fileReader!=null){
            JSONReader reader = new JSONReader(fileReader);
            reader.startArray();
            while (reader.hasNext()){
                T t = reader.readObject(clazz);
                if (t!=null){
                    resultList.add(t);
                }
            }
            reader.endArray();
            return resultList;
        }else {
            throw new IOException();
        }
    }

    //test: 读取json 传入对应类型
    public static void main(String[] args) {
        try {
            List<City> list = jsonFileToObjectList("E:\\localspace\\weather\\src\\main\\resources\\cityZ.json",City.class);
            list.stream().forEach(e -> {
                System.out.println(e.toString());
            });
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
