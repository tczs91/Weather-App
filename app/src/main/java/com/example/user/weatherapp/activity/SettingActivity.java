package com.example.user.weatherapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.user.weatherapp.R;

public class SettingActivity extends AppCompatActivity {
    private EditText mDaysToDisp;
    private EditText mZipCode;
    private RadioGroup mBtnGroup;
    private String mTemperatureMeasurement;

    private Button mSubmitBtn;
    private Button mCancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        //wire up
        mDaysToDisp = (EditText)findViewById(R.id.daysToDispEditText);
        mZipCode = (EditText)findViewById(R.id.zipCodeEditText);
        mBtnGroup = (RadioGroup)findViewById(R.id.btn_group);

        mSubmitBtn = (Button)findViewById(R.id.btn_submit);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check() == true) {
                    //// TODO: 10/26/15 return to weathermain and change the settings
                    Intent intent = new Intent(SettingActivity.this, WeatherMainActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        
            /*@Override
            public void onClick(View v) {
                if(check() == true) {
                    User user = new User();
                    user.setUserEmail(mUserEmailValue.getText().toString());
                    user.setUserName(mUserNickNameValue.getText().toString());
                    user.setUserPwd(mUserPwdValue.getText().toString());
                    user.setUserGender(mUserGender);
                    user.setUserDob(mUserDob);
                    user.setUserDescription(mUserDesValue.getText().toString());
                    UserService userService = new UserService(SignUpActivity.this, "SignUp", SignUpActivity.this);
                    userService.signUp(user);
                }
            }*/
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


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void LoadPrefs() {

    }

    private  void SavePrefs(String key, boolean value) {

    }

    private  void SavePrefs(String key, String value) {

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SettingActivity.this, WeatherMainActivity.class);
        finish();
        startActivity(intent);
    }

    private boolean check() {
        if(TextUtils.isEmpty(mDaysToDisp.getText().toString())) {
            return true;
        } else if(mDaysToDisp.getText().toString().equals("1")
                ||mDaysToDisp.getText().toString().equals("2")
                ||mDaysToDisp.getText().toString().equals("3")
                ||mDaysToDisp.getText().toString().equals("4")
                ||mDaysToDisp.getText().toString().equals("5")
                ||mDaysToDisp.getText().toString().equals("6")
                ||mDaysToDisp.getText().toString().equals("7")
                ||mDaysToDisp.getText().toString().equals("8")
                ||mDaysToDisp.getText().toString().equals("9")
                ||mDaysToDisp.getText().toString().equals("10")){
            return true;
        } else {
            Toast.makeText(SettingActivity.this, "The number of days to display should be 1 through 10!",Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
