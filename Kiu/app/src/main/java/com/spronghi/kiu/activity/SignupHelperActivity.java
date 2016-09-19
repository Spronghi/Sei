package com.spronghi.kiu.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.spronghi.kiu.R;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.setup.SetupView;

/**
 * Created by spronghi on 14/09/16.
 */
public class SignupHelperActivity  extends AppCompatActivity {
    private EditText usernameText;
    private EditText statusText;
    private EditText nameText;
    private EditText surnameText;
    private EditText birthText;
    private EditText telephoneText;
    private EditText favoriteCityText;
    private EditText favoriteCostText;
    private Button okButton;
    private Toolbar toolbar;

    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_helper);

        usernameText = (EditText) findViewById(R.id.activity_signup_helper_username);
        statusText = (EditText) findViewById(R.id.activity_signup_helper_status);
        nameText = (EditText) findViewById(R.id.activity_signup_helper_name);
        surnameText = (EditText) findViewById(R.id.activity_signup_helper_surname);
        birthText = (EditText) findViewById(R.id.activity_signup_helper_birth);
        telephoneText = (EditText) findViewById(R.id.activity_signup_helper_telephone);
        favoriteCityText = (EditText) findViewById(R.id.activity_signup_helper_favorite_city);
        favoriteCostText = (EditText) findViewById(R.id.activity_signup_helper_favorite_cost);
        okButton = (Button) findViewById(R.id.activity_signup_helper_ok_button);
        toolbar = (Toolbar) findViewById(R.id.activity_signup_helper_toolbar);

        helper= new Helper();

        SetupView.setEditTextForDate(birthText, this);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO create helper
                Intent intent = new Intent(SignupHelperActivity.this, MainActivity.class);
                startActivity(intent);
                SignupHelperActivity.this.finish();
            }
        });

        setupToolbar();
    }

    private void setupHelper(){
        //TO DO take the value and populate the helper

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
