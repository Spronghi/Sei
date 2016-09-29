package com.spronghi.kiu.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.spronghi.kiu.R;

import com.spronghi.kiu.http.KiuerService;
import com.spronghi.kiu.model.Kiuer;


/**
 * Created by spronghi on 14/09/16.
 */
public class SignupKiuerActivity extends AppCompatActivity {
    private EditText usernameText;
    private EditText password;
    private EditText confPass;
    private EditText email;
    private EditText favoriteCityText;
    private Toolbar toolbar;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_kiuer);

        usernameText = (EditText) findViewById(R.id.activity_signup_kiuer_username);
        password = (EditText) findViewById(R.id.activity_signup_kiuer_password);
        confPass = (EditText) findViewById(R.id.activity_signup_kiuer_confpassword);
        email = (EditText) findViewById(R.id.activity_signup_kiuer_email);
        favoriteCityText = (EditText) findViewById(R.id.activity_signup_kiuer_favorite_city);
        okButton = (Button) findViewById(R.id.activity_signup_kiuer_ok_button);
        toolbar = (Toolbar) findViewById(R.id.activity_signup_kiuer_toolbar);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Empty");
                } else if(TextUtils.isEmpty(usernameText.getText().toString())) {
                    usernameText.setError("Empty");
                } else if(TextUtils.isEmpty(confPass.getText().toString())) {
                    confPass.setError("Empty");
                } else if(TextUtils.isEmpty(email.getText().toString())) {
                    email.setError("Empty");
                } else if(TextUtils.isEmpty(favoriteCityText.getText().toString())) {
                    favoriteCityText.setError("Empty");
                } else if(!(password.getText().toString().equals(confPass.getText().toString()))) {
                    password.setError("Not equals");
                    confPass.setError("Not equals");
                } else {
                    Kiuer kiuer = new Kiuer();
                    kiuer.setUsername(usernameText.getText().toString());
                    kiuer.setFavoriteCity(favoriteCityText.getText().toString());
                    kiuer.setPassword(password.getText().toString());
                    KiuerService.create(kiuer);

                    Intent intent = new Intent(SignupKiuerActivity.this, MainActivity.class);
                    startActivity(intent);
                    SignupKiuerActivity.this.finish();
                }
            }
        });

        setupToolbar();
    }

    private void setupToolbar(){

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupKiuerActivity.this, LoginActivity.class);
                startActivity(intent);
                SignupKiuerActivity.this.finish();
            }
        });
    }
}