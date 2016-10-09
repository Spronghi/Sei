package com.spronghi.kiu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.spronghi.kiu.R;
import com.spronghi.kiu.http.HttpConnector;

/**
 * Created by spronghi on 09/10/16.
 */
public class FirstExecuteActivity extends AppCompatActivity {
    private EditText ip;
    private Button okButton;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_execute);

        ip = (EditText) findViewById(R.id.activity_first_execute_ip_address);
        okButton = (Button) findViewById(R.id.activity_first_execute_ok_button);

    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    public void setOkButton(View view) {
        if(!TextUtils.isEmpty(ip.getText())){
            preferences = getSharedPreferences("IPAddress",0);
            preferences.edit().putString("IPAddress", ip.getText().toString()).commit();

            HttpConnector.setServerIP(ip.getText().toString());

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            ip.setError("Empty");
        }

    }

}
