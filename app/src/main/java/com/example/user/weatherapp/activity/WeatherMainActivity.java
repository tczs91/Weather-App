package com.example.user.weatherapp.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.weatherapp.R;
import com.example.user.weatherapp.asynctask.DownloadWeatherDataAsyncTask;
import com.example.user.weatherapp.model.WeatherDay;
import com.example.user.weatherapp.sensor.LocationFinder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WeatherMainActivity extends AppCompatActivity implements DownloadWeatherDataAsyncTask.DownloadWeatherCompletionListener, LocationFinder.LocationDetector{
    private static  final  int LOCATION_ACCESS_REQUEST_CODE = 1; //this can be any number

    private final String TAG = "WeatherMainActivity";

    //Test Download Weather Data
    private TextView mLocationTextView;
    private ImageButton mReLocateButton;
    private Button mSettingButton;

    private List<WeatherDay> mWeatherDay = new ArrayList<WeatherDay>();
    private Location mlocation;


    private ProgressDialog progressDialog;
    private DownloadWeatherDataAsyncTask downloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_main);

        downloadTask = new DownloadWeatherDataAsyncTask(this,this);
        progressDialog = new ProgressDialog(this);


        //wire up
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
        mReLocateButton = (ImageButton) findViewById(R.id.relocateButton);
        mSettingButton = (Button) findViewById(R.id.settingButton);
        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherMainActivity.this, SettingActivity.class);
                finish();
                startActivity(intent);
            }
        });

        //inform user of intent to use their location (only for Android 6.0+)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //show dialog
            new AlertDialog.Builder(this)
                    .setTitle(R.string.location_permission_title)
                    .setMessage(R.string.location_permission_message)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //prompt user with system dialog for location permission upon user clicking okay dialog button
                            ActivityCompat.requestPermissions(WeatherMainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_ACCESS_REQUEST_CODE);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(false)
                    .show();
        }

        //detect the user location
        /*LocationFinder locationFinder = new LocationFinder(this,this);
        locationFinder.detectLocation();
        String longtitude = String.valueOf(mlocation.getLongitude());
        String latitude = String.valueOf(mlocation.getLatitude());
*/
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        //Use VA/Arlington as the location to test

        //String query = latitude + "," +longtitude;

        String query = "VA/Arlington";
        //// TODO: 10/25/15 find the user's location
        downloadTask.execute(query);

        
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch(requestCode){
            case LOCATION_ACCESS_REQUEST_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission granted, yay!
                    //some apps may want to do something special upon receiving permission, but we won't in the case of the trivia app
                }
                else{
                    //permission denied, nooooo
                    //some apps may want to do something special upon NOT receiving permission, but we won't in the case of the trivia app
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void weatherDataDownloaded(List<WeatherDay> jsonForecast) {
        progressDialog.hide();
        mWeatherDay = jsonForecast;

        populateListView();
        mLocationTextView.setText(jsonForecast.get(0).getLocation());
    }

    @Override
    public void weatherDataFailToDownload(Exception exception) {
        progressDialog.hide();
        Log.d(TAG, "Weather information not found");
    }

    @Override
    public void locationFound(Location location) {
        Log.d(TAG,"location found");
        mlocation = location;
    }

    @Override
    public void locationNotFound(LocationFinder.FailureReason failureReason) {
        Log.d(TAG,"location not found");
    }

    private class WeatherListAdapter extends ArrayAdapter<WeatherDay> {
        public WeatherListAdapter(){
            super(WeatherMainActivity.this, R.layout.item_view, mWeatherDay);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Make sure we have a view to work with
            View itemView = convertView;
            if (itemView== null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent,false);
            }

            //find the weatherDay to work with.
            WeatherDay currentDay = mWeatherDay.get(position);

            //fill the view
            ImageView imageView = (ImageView) itemView.findViewById(R.id.conditionIconImageView);

             Picasso.with(WeatherMainActivity.this).load(currentDay.getIconUrl()).into(imageView);
            //Week Day:
            TextView weekDayText = (TextView) itemView.findViewById(R.id.weekDayTextView);
            weekDayText.setText(currentDay.getWeekDay());

            //High temperature:
            TextView tempHighText = (TextView) itemView.findViewById(R.id.tempHighTextView);
            tempHighText.setText(String.valueOf(currentDay.getHighTempC()) + "\u00B0");

            //Low temperature:
            TextView tempLowText = (TextView) itemView.findViewById(R.id.tempLowTextView);
            tempLowText.setText(String.valueOf(currentDay.getLowTempC()) +"\u00B0");
            return itemView;
        }
    }

    private void populateListView() {
        ListView weatherListView = (ListView) findViewById(R.id.weatherListView);
        ArrayAdapter<WeatherDay> adapter = new WeatherListAdapter();
        weatherListView.setAdapter(adapter);
    }
}
