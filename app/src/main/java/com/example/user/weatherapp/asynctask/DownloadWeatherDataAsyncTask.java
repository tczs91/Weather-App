package com.example.user.weatherapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.net.URL;

/**
 * Created by Theon_Z on 15/10/11.
 */
public class DownloadWeatherDataAsyncTask extends AsyncTask<String,Integer,URL> {

    private Context mContext;
    private  DownloadWeatherCompletionListener mCompletionListener;

    public  interface DownloadWeatherCompletionListener{
        public void weatherUrlFound(URL url);
        public void weatherUrlNotFound();

    }

    public  DownloadWeatherDataAsyncTask(Context context) {mContext = context;}


    @Override
    protected URL doInBackground(String... location) {

        try{
            JsonObject jsonResult =
        }
    }
}
