package com.example.user.weatherapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Theon_Z on 10/14/15.
 */
public class WeatherData implements Serializable {
    private List<WeatherDay> mWeatherDays;

    public WeatherData() { super(); }

    public WeatherData(List<WeatherDay> weatherDays) {
        mWeatherDays = weatherDays;
    }

    public List<WeatherDay> getmWeatherDays() { return mWeatherDays; }

    public void setmWeatherDays(List<WeatherDay> weatherDays) { mWeatherDays = weatherDays; }

}
