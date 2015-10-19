package com.example.user.weatherapp.model;

/**
 * Created by Theon_Z on 10/19/15.
 */
public class WeatherDay {
    //The weather information
    // The temperature (Fahrenheit and Celsius)
    private float mHighTempF;
    private float mHighTempC;
    private float mLowTempF;
    private float mLowTempC;
    private String mConditions;
    private String mIconUrl;

    public void setWeatherData(Float highTempF,Float highTempC,Float lowTempF,
                               Float lowTempC,String conditions,String iconUrl){

        mHighTempF =  highTempF;
        mHighTempC = highTempC;
        mLowTempF = lowTempF;
        mLowTempC = lowTempC;
        mConditions = conditions;
        mIconUrl = iconUrl;
    }

    public float getHighTempF() {
        return mHighTempF;
    }

    public float getHighTempC() {
        return mHighTempC;
    }

    public float getLowTempF() {
        return mLowTempF;
    }

    public float getLowTempC() {
        return mLowTempC;
    }

    public String getConditions() {
        return mConditions;
    }

    public String getIconUrl() {
        return mIconUrl;
    }
}
