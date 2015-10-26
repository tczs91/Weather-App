package com.example.user.weatherapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.weatherapp.model.JsonDate;
import com.example.user.weatherapp.model.JsonForecastDay;
import com.example.user.weatherapp.model.JsonHigh;
import com.example.user.weatherapp.model.JsonLow;
import com.example.user.weatherapp.model.WeatherDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Theon_Z on 15/10/11.
 */
public class DownloadWeatherDataAsyncTask extends AsyncTask<String,Integer,String> {
    private static final String TAG = "DownloadWeatherDataTask";

    private Context mContext;
    private  DownloadWeatherCompletionListener mCompletionListener;
    private  Exception error;

    public  interface DownloadWeatherCompletionListener{
        public void weatherDataDownloaded(List<WeatherDay> jsonForecast);
        public void weatherDataFailToDownload(Exception exception);
    }

    public  DownloadWeatherDataAsyncTask(Context context, DownloadWeatherCompletionListener completionListener) {
        mContext = context;
        mCompletionListener = completionListener;
    }


    @Override
    protected String doInBackground(String... query) {
        String weatherUrl = String.format("http://api.wunderground.com/api/37e29e893fb83d0d/forecast10day/geolookup" +
                "/q/%s.json",query);

        try {
            URL url = new URL(weatherUrl);

            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine())!= null) {
                result.append(line);
            }

            return result.toString();

        } catch (Exception e) {
            //Todo handle url not found exception
            error = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s == null && error != null) {
            mCompletionListener.weatherDataFailToDownload(error);
            return;
        }

        try{
            JSONObject data = new JSONObject(s);
            try {
                JSONObject forecast = data.getJSONObject("forecast");
                JSONArray forecastday = forecast.getJSONObject("simpleforecast").getJSONArray("forecastday");

                List<WeatherDay> weatherResults = new ArrayList<WeatherDay>();
                JsonHigh jsonHighTemp = new JsonHigh();
                JsonLow jsonLowTemp = new JsonLow();
                JsonDate jsonDateTemp = new JsonDate();
                String weekDay;
                JSONObject jsonLocation = data.getJSONObject("location");
                String location = jsonLocation.getString("city") + " " +jsonLocation.getString("state");

                for(int n = 0; n < forecastday.length(); n++) {
                    WeatherDay weatherTemp = new WeatherDay();
                    JSONObject forecastTemp = forecastday.getJSONObject(n);
                    JsonForecastDay jsonDayTemp = new JsonForecastDay();
                    jsonDayTemp.Populate(forecastTemp);
                    weatherTemp.setConditions(jsonDayTemp.getCondition());
                    weatherTemp.setIconUrl(jsonDayTemp.getIconUrl());
                    jsonHighTemp = jsonDayTemp.getJsonHigh();
                    jsonLowTemp = jsonDayTemp.getJsonLow();
                    jsonDateTemp = jsonDayTemp.getJsonDate();
                    weekDay = jsonDateTemp.getWeekDay();
                    weatherTemp.setHighTempC(jsonHighTemp.getCelsius());
                    weatherTemp.setHighTempF(jsonHighTemp.getFahrenheit());
                    weatherTemp.setLowTempC(jsonLowTemp.getCelsius());
                    weatherTemp.setLowTempF(jsonLowTemp.getFahrenheit());
                    weatherTemp.setLocation(location);
                    weatherTemp.setWeekDay(weekDay);
                    weatherResults.add(weatherTemp);
                }
                mCompletionListener.weatherDataDownloaded(weatherResults);

            } catch (JSONException e) {
                error = e;
                mCompletionListener.weatherDataFailToDownload(new LocationNotFoundException("No weather information found for the location provided!"));
                return;
            }
        } catch (JSONException e) {
            mCompletionListener.weatherDataFailToDownload(e);
        }


    }

    public class LocationNotFoundException extends Exception {

        public LocationNotFoundException(String detailMessage) {
            super(detailMessage);
        }
    }
}
