package com.example.user.weatherapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.weatherapp.model.WeatherData;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.net.URL;

/**
 * Created by Theon_Z on 15/10/11.
 */
public class DownloadWeatherDataAsyncTask extends AsyncTask<String,Integer,WeatherData> {

    private Context mContext;
    private  DownloadWeatherCompletionListener mCompletionListener;

    public  interface DownloadWeatherCompletionListener{
        public void weatherDataDownloaded(WeatherData weatherData);
        public void weatherDataFailToDownload();
    }

    public  DownloadWeatherDataAsyncTask(Context context) {mContext = context;}

    public void setmCompletionListener(DownloadWeatherCompletionListener completionListener){
        mCompletionListener = completionListener;
    }
    
    @Override
    protected URL doInBackground(String... location) {

        try{
            JsonObject jsonResult =
        }
    }
}
