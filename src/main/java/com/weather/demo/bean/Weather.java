package com.weather.demo.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public class Weather {
    // 城市名称
    private String cityName;
    // 更新时间
    private Date dtTime;
    // 天气
    private String weather;
    // 温度
    private String temperature;
    // 风级
    private String wind;


    public String getCityName(){
        return cityName;
    }
    public void setCityName(String cityName){
        this.cityName = cityName;
    }

    public Date getDtTime(){
        return dtTime;
    }
    public void setDtTime(Date dtTime){
        this.dtTime = dtTime;
    }

    public String getWeather(){
        return weather;
    }
    public void setWeather(String weather){
        this.weather = weather;
    }

    public String getTemperature(){
        return temperature;
    }
    public void setTemperature(String temperature){
        this.temperature = temperature;
    }

    public String getWind(){
        return wind;
    }
    public void setWind(String wind){
        this.wind = wind;
    }
}
