package com.example.user.weatherapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.weatherapp.model.JsonForecast;
import com.example.user.weatherapp.model.WeatherData;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Theon_Z on 15/10/11.
 */
public class DownloadWeatherDataAsyncTask extends AsyncTask<String,Integer,JsonForecast> {

    private Context mContext;
    private  DownloadWeatherCompletionListener mCompletionListener;

    public  interface DownloadWeatherCompletionListener{
        public void weatherDataDownloaded(JsonForecast jsonForecast);
        public void weatherDataFailToDownload(Exception exception);
    }

    public  DownloadWeatherDataAsyncTask(Context context) {mContext = context;}

    public void setmCompletionListener(DownloadWeatherCompletionListener completionListener){
        mCompletionListener = completionListener;
    }

    @Override
    protected JsonForecast doInBackground(String... query) {

        try{
            
        }
    }
}
