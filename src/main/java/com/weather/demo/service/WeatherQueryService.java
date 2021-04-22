package com.weather.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weather.demo.bean.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class WeatherQueryService {
    RestTemplate restTemplate = new RestTemplate();

    public Weather queryDefaultWeather(){
        String defaultCity = "http://api.openweathermap.org/data/2.5/weather?q=Melbourne&appid=d5a8766e86069a5eeed1f9da547923fe&units=metric";
        return assembleData(restTemplate.getForObject(defaultCity, JSONObject.class));
    }

    public Weather queryWeatherByCity(String cityName){
        String customCity = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=d5a8766e86069a5eeed1f9da547923fe&units=metric";
        return assembleData(restTemplate.getForObject(customCity, JSONObject.class));
    }

    public Weather assembleData(JSONObject results){
        Map<String, ?> jsonMap = JSONObject.toJavaObject(results, Map.class);
        // temperature
        StringBuilder sb1 = new StringBuilder();
        LinkedHashMap<String, ?> lhp1 = (LinkedHashMap<String, ?>) jsonMap.get("main");
        Iterator it1 = lhp1.entrySet().iterator();
        int order1 = 0;
        while (it1.hasNext()) {
            order1++;
            Map.Entry entity = (Map.Entry) it1.next();
            if(order1 > 0){
                sb1.append(entity.getValue().toString());
                break;
            }
        }
        // weather
        StringBuilder sb2 = new StringBuilder();
        ArrayList<LinkedHashMap> list1 = (ArrayList<LinkedHashMap>) jsonMap.get("weather");
        LinkedHashMap<String, ?> lhp2 = list1.get(0);
        Iterator it2 = lhp2.entrySet().iterator();
        int order2 = 0;
        while (it2.hasNext()) {
            order2++;
            Map.Entry entity = (Map.Entry) it2.next();
            if(order2 > 1){
                sb2.append(entity.getValue().toString());
                break;
            }
        }
        // wind
        StringBuilder sb3 = new StringBuilder();
        LinkedHashMap<String, ?> lhp3 = (LinkedHashMap<String, ?>) jsonMap.get("wind");
        Iterator it3 = lhp3.entrySet().iterator();
        int order3 = 0;
        while (it3.hasNext()) {
            order3++;
            Map.Entry entity = (Map.Entry) it3.next();
            if(order3 > 0){
                sb3.append(entity.getValue().toString());
                break;
            }
        }
        // updatetime
        int updateTime = ((Integer) jsonMap.get("dt")).intValue();
        int ms = 1000;

        Weather weather = new Weather();
        weather.setCityName(jsonMap.get("name").toString());
        weather.setDtTime(new Date(updateTime * ms));
        weather.setTemperature(sb1.toString());
        weather.setWeather(sb2.toString());
        weather.setWind(sb3.toString());
        return weather;
    }
}
