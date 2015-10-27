package com.example.user.weatherapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.weatherapp.R;

public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //private EditText mDaysToDisp;
    private EditText mZipCode;
    private RadioGroup mBtnGroup;
    private Button mSubmitBtn;
    private Button mCancelBtn;
    private Spinner mDayDispSpinner;
    private CheckBox mCheckUseZipCode;

    //Variables used to get the sharedpreferences
    private String mTemperatureMeasurement;
    private String mLanguage;
    private String mZipCodePref;
    private String mDaysToDisplay;
    private Boolean mIsUsingZipCode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        //wire up checkbox
        mCheckUseZipCode = (CheckBox) findViewById(R.id.useZipCheckBox);

        //Load SharedPrefs
        LoadPrefs();

        //wire up
        mZipCode = (EditText)findViewById(R.id.zipCodeEditText);
        mBtnGroup = (RadioGroup)findViewById(R.id.btn_group);
        ArrayAdapter dispAdapter=ArrayAdapter.createFromResource(this,R.array.disp_days,android.R.layout.simple_spinner_dropdown_item);
        mDayDispSpinner = (Spinner) findViewById(R.id.dayDispSpinner);
        mDayDispSpinner.setAdapter(dispAdapter);
        mDayDispSpinner.setOnItemSelectedListener(this);

        mSubmitBtn = (Button)findViewById(R.id.btn_submit);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    SavePrefs("DaysToDisplay",mDaysToDisplay);

                    //handle if group button selected if then pass F or C
                    if(mBtnGroup.getCheckedRadioButtonId() != -1) {
                        RadioButton radioButton = (RadioButton) findViewById(mBtnGroup.getCheckedRadioButtonId());
                        mTemperatureMeasurement = radioButton.getText().toString();
                        SavePrefs("TempMeasurement", mTemperatureMeasurement);
                    }


                    //use the zip code to relocate
                    //if the user has imputed a zipcode
                    if(mCheckUseZipCode.isChecked()) {
                        mZipCodePref = mZipCode.getText().toString();
                        SavePrefs("ZipCode", mZipCodePref);
                        mIsUsingZipCode = true;
                        SavePrefs("IsUsingZipCode", mIsUsingZipCode);
                    } else {
                        mIsUsingZipCode = false;
                        SavePrefs("IsUsingZipCode", mIsUsingZipCode);
                    }
                    /*if(TextUtils.isEmpty(mZipCode.getText().toString())) {}
                    else {
                        mZipCodePref = mZipCode.getText().toString();
                        SavePrefs("ZipCode", mZipCodePref);
                        mIsUsingZipCode = true;
                        SavePrefs("IsUsingZipCode", mIsUsingZipCode);
                    }
*/
                    //Todo: create new groupbutton in layout and pass the language
                    mLanguage = "ENG";
                    SavePrefs("Language", mLanguage);

                    Intent intent = new Intent(SettingActivity.this, WeatherMainActivity.class);
                    finish();
                    startActivity(intent);

            }

        });

        mCancelBtn = (Button)findViewById(R.id.btn_cancel);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, WeatherMainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        //finish wire up

    }

    private void LoadPrefs() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        mDaysToDisplay = sp.getString("DaysToDisplay", "3");
        mLanguage = sp.getString("Language", "ENG");
        mTemperatureMeasurement = sp.getString("TempMeasurement", "Fahrenheit");
        mZipCodePref = sp.getString("ZipCode", "22202");
        mIsUsingZipCode = sp.getBoolean("IsUsingZipCode", false);
        if(mIsUsingZipCode) {
            mCheckUseZipCode.setChecked(true);
        }
    }

    private  void SavePrefs(String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value);
        edit.commit();
    }

    private  void SavePrefs(String key, Boolean value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key,value);
        edit.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SettingActivity.this, WeatherMainActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        mDaysToDisplay = myText.getText().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
