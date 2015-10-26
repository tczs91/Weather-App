package com.example.user.weatherapp.model;

import org.json.JSONObject;

/**
 * Created by Theon_Z on 10/19/15.
 */
public class JsonForecastDay implements JsonPopulator {

    private JsonHigh mJsonHigh;
    private JsonLow mJsonLow;
    private String mCondition;
    private String mIconUrl;
    private JsonDate mJsonDate;

    public JsonHigh getJsonHigh() {
        return mJsonHigh;
    }

    public JsonDate getJsonDate() {
        return mJsonDate;
    }

    public void setJsonDate(JsonDate jsonDate) {
        mJsonDate = jsonDate;
    }

    public void setJsonHigh(JsonHigh jsonHigh) {
        mJsonHigh = jsonHigh;
    }

    public JsonLow getJsonLow() {
        return mJsonLow;
    }

    public void setJsonLow(JsonLow jsonLow) {
        mJsonLow = jsonLow;
    }

    public String getCondition() {
        return mCondition;
    }

    public void setCondition(String condition) {
        mCondition = condition;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }

    @Override
    public void Populate(JSONObject data) {

        mJsonHigh = new JsonHigh();
        mJsonHigh.Populate(data.optJSONObject("high"));

        mJsonLow = new JsonLow();
        mJsonLow.Populate(data.optJSONObject("low"));

        mJsonDate = new JsonDate();
        mJsonDate.Populate(data.optJSONObject("date"));

        mCondition = data.optString("conditions");
        mIconUrl = data.optString("icon_url");
    }
}
