package com.weather.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.weather.demo.bean.Weather;
import com.weather.demo.service.WeatherQueryService;
import org.springframework.web.bind.annotation.*;
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
        Weather weather = weatherQueryService.queryDefaultWeather();

        mv.addObject("weather",weather);
        mv.setViewName("weather");

       String[] cities = new String[]{"Melbourne", "Sydney", "Wollongong"};
        mv.addObject("cities",cities);
        return mv;
    }

    @PostMapping("/api/v1/getWeatherByCity/{city}")
    public ModelAndView query(@PathVariable("city") String city){
        Weather weather = weatherQueryService.queryWeatherByCity(city);
        String[] cities = new String[]{"Melbourne", "Sydney", "Wollongong"};

        ModelAndView mv = new ModelAndView("/api/v1/getWeatherByCity/"+city);
        mv.addObject("cities",cities);
        mv.addObject("weather",weather);
        return mv;
    }
}
