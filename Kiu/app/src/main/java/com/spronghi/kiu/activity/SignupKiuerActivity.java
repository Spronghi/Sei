package com.spronghi.kiu.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.spronghi.kiu.R;
import com.spronghi.kiu.model.Kiuer;

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

    private Kiuer kiuer;

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

        kiuer = new Kiuer();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO create kiuer
                //TO DO load MainActivity
            }
        });

        setupKiuer();
        setupToolbar();
    }
    private void setupKiuer(){
        //TO DO take the value and populate the kiuer
    }

    private void setupToolbar(){
        final FragmentManager manager = this.getFragmentManager();

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
                manager.popBackStack();
            }
        });
    }
}
