package com.spronghi.kiu.activity;

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

import com.spronghi.kiu.http.HelperService;
import com.spronghi.kiu.model.Helper;


/**
 * Created by spronghi on 14/09/16.
 */
public class SignupHelperActivity  extends AppCompatActivity {
    private EditText usernameText;
    private EditText password;
    private EditText confPass;
    private EditText email;
    private EditText favoriteCityText;
    private EditText favoriteCostText;
    private Button okButton;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_helper);

        usernameText = (EditText) findViewById(R.id.activity_signup_helper_username);
        password = (EditText) findViewById(R.id.activity_signup_helper_password);
        confPass = (EditText) findViewById(R.id.activity_signup_helper_confpassword);
        email = (EditText) findViewById(R.id.activity_signup_helper_email);
        favoriteCityText = (EditText) findViewById(R.id.activity_signup_helper_favorite_city);
        favoriteCostText = (EditText) findViewById(R.id.activity_signup_helper_favorite_cost);
        okButton = (Button) findViewById(R.id.activity_signup_helper_ok_button);
        toolbar = (Toolbar) findViewById(R.id.activity_signup_helper_toolbar);

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
                } else if(TextUtils.isEmpty(favoriteCostText.getText().toString())) {
                    favoriteCostText.setError("Empty");
                } else if(!(password.getText().toString().equals(confPass.getText().toString()))) {
                    password.setError("Not equals");
                    confPass.setError("Not equals");
                } else {
                    Helper helper = new Helper();
                    helper.setUsername(usernameText.getText().toString());
                    helper.setFavoriteCity(favoriteCityText.getText().toString());
                    helper.setFavoriteCost(Double.parseDouble(favoriteCostText.getText().toString()));
                    helper.setPassword(password.getText().toString());
                    HelperService.create(helper);

                    Intent intent = new Intent(SignupHelperActivity.this, MainActivity.class);
                    startActivity(intent);
                    SignupHelperActivity.this.finish();
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
                Intent intent = new Intent(SignupHelperActivity.this, LoginActivity.class);
                startActivity(intent);
                SignupHelperActivity.this.finish();
            }
        });
    }
}