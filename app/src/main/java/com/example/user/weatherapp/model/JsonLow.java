package com.example.user.weatherapp.model;

import org.json.JSONObject;

/**
 * Created by Theon_Z on 10/19/15.
 */
public class JsonLow implements JsonPopulator{
    private double mFahrenheit;
    private double mCelsius;

    public double getFahrenheit() {
        return mFahrenheit;
    }

    public void setFahrenheit(double fahrenheit) {
        mFahrenheit = fahrenheit;
    }

    public double getCelsius() {
        return mCelsius;
    }

    public void setCelsius(double celsius) {
        mCelsius = celsius;
    }

    @Override
    public void Populate(JSONObject data) {

        mFahrenheit = data.optDouble("fahrenheit");
        mCelsius = data.optDouble("celsius");
    }
}
