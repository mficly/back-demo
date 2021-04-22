package com.weather.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.weather.demo.bean.Weather;
import com.weather.demo.service.WeatherQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

@RestController
public class WeatherController {
    @Resource
    WeatherQueryService weatherQueryService;

    @RequestMapping("/api/v1/getWeather")
    public ModelAndView entry(ModelAndView mv){
        JSONObject results = weatherQueryService.queryDefaultWeather();
        Map<String, ?> jsonMap = JSONObject.toJavaObject(results, Map.class);

        Weather weather = new Weather();
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

        weather.setCityName(jsonMap.get("name").toString());
        weather.setDtTime(new Date(updateTime * ms));
        weather.setTemperature(sb1.toString());
        weather.setWeather(sb2.toString());
        weather.setWind(sb3.toString());
        mv.addObject("weather",weather);
        mv.setViewName("weather");

       String[] cities = new String[]{"Melbourne", "Sydney", "Wollongong"};
        mv.addObject("cities",cities);
        return mv;
    }

    @RequestMapping("/api/v1/getWeatherByCity")
    public ModelAndView query(ModelAndView mv, String city){
        JSONObject results = weatherQueryService.queryWeatherByCity(city);
        return mv;
    }
}
