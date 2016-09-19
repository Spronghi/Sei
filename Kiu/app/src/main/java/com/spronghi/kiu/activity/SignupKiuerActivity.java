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
import com.spronghi.kiu.setup.SetupView;

/**
 * Created by spronghi on 14/09/16.
 */
public class SignupKiuerActivity extends AppCompatActivity {
    private EditText usernameText;
    private EditText statusText;
    private EditText nameText;
    private EditText surnameText;
    private EditText birthText;
    private EditText telephoneText;
    private EditText favoriteCityText;
    private Toolbar toolbar;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_kiuer);

        usernameText = (EditText) findViewById(R.id.activity_signup_kiuer_username);
        statusText = (EditText) findViewById(R.id.activity_signup_kiuer_status);
        nameText = (EditText) findViewById(R.id.activity_signup_kiuer_name);
        surnameText = (EditText) findViewById(R.id.activity_signup_kiuer_surname);
        birthText = (EditText) findViewById(R.id.activity_signup_kiuer_birth);
        telephoneText = (EditText) findViewById(R.id.activity_signup_kiuer_telephone);
        favoriteCityText = (EditText) findViewById(R.id.activity_signup_kiuer_favorite_city);
        okButton = (Button) findViewById(R.id.activity_signup_kiuer_ok_button);
        toolbar = (Toolbar) findViewById(R.id.activity_signup_kiuer_toolbar);

        SetupView.setEditTextForDate(birthText, this);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(nameText.getText().toString())) {
                    nameText.setError("Empty");
                } else if(TextUtils.isEmpty(surnameText.getText().toString())) {
                    surnameText.setError("Empty");
                } else if(TextUtils.isEmpty(statusText.getText().toString())) {
                    statusText.setError("Empty");
                } else if(TextUtils.isEmpty(usernameText.getText().toString())) {
                    usernameText.setError("Empty");
                } else if(TextUtils.isEmpty(birthText.getText().toString())) {
                    birthText.setError("Empty");
                } else if(TextUtils.isEmpty(telephoneText.getText().toString())) {
                    telephoneText.setError("Empty");
                } else if(TextUtils.isEmpty(favoriteCityText.getText().toString())) {
                    favoriteCityText.setError("Empty");
                } else {
                    Kiuer kiuer = new Kiuer();
                    kiuer.setName(nameText.getText().toString());
                    kiuer.setSurname(surnameText.getText().toString());
                    kiuer.setProfileStatus(statusText.getText().toString());
                    kiuer.setUsername(usernameText.getText().toString());
                    kiuer.setBirth(birthText.getText().toString());
                    kiuer.setTelephone(telephoneText.getText().toString());
                    kiuer.setFavoriteCity(favoriteCityText.getText().toString());
                    kiuer.setPassword("a");
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
