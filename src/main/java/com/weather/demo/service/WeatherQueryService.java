package com.weather.demo.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherQueryService {
    RestTemplate restTemplate = new RestTemplate();

    public JSONObject queryWeather(){
        String aaa = "http://api.openweathermap.org/data/2.5/weather?q=Melbourne&appid=d5a8766e86069a5eeed1f9da547923fe&units=metric";
        return restTemplate.getForObject(aaa, JSONObject.class);
    }
}
