package com.example.user.weatherapp.model;

/**
 * Created by Theon_Z on 10/19/15.
 */
public class WeatherDay {
    //The weather information
    // The temperature (Fahrenheit and Celsius)
    private double mHighTempF;
    private double mHighTempC;
    private double mLowTempF;
    private double mLowTempC;
    private String mConditions;
    private String mIconUrl;
    private String mLocation;
    private String mWeekDay;

    public String getWeekDay() {
        return mWeekDay;
    }

    public void setWeekDay(String weekDay) {
        mWeekDay = weekDay;
    }

    public double getHighTempF() {
        return mHighTempF;
    }

    public void setHighTempF(double highTempF) {
        mHighTempF = highTempF;
    }

    public double getHighTempC() {
        return mHighTempC;
    }

    public void setHighTempC(double highTempC) {
        mHighTempC = highTempC;
    }

    public double getLowTempF() {
        return mLowTempF;
    }

    public void setLowTempF(double lowTempF) {
        mLowTempF = lowTempF;
    }

    public double getLowTempC() {
        return mLowTempC;
    }

    public void setLowTempC(double lowTempC) {
        mLowTempC = lowTempC;
    }

    public String getConditions() {
        return mConditions;
    }

    public void setConditions(String conditions) {
        mConditions = conditions;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }
}
