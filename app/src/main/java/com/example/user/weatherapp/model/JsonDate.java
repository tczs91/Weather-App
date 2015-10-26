package com.example.user.weatherapp.model;

import org.json.JSONObject;

/**
 * Created by Theon_Z on 10/25/15.
 */
public class JsonDate implements JsonPopulator {
    private String mWeekDay;

    public String getWeekDay() {
        return mWeekDay;
    }

    public void setWeekDay(String weekDay) {
        mWeekDay = weekDay;
    }

    @Override
    public void Populate(JSONObject data) {
        mWeekDay = data.optString("weekday");
    }
}
