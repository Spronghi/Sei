package com.spronghi.kiu.activity;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.spronghi.kiu.R;

/**
 * Created by matte on 13/06/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView signupLabel;
    private CheckBox rememberMe;
    private RadioButton kiuerRadioButton;
    private RadioButton helperRadioButton;
    private ScrollView mScrollView;
    private Button loginButton;

    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mScrollView = (ScrollView) findViewById(R.id.login_activity_scrollview);

        signupLabel = (TextView) findViewById(R.id.link_login);
        usernameEditText = (EditText)findViewById(R.id.usernameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        kiuerRadioButton = (RadioButton)findViewById(R.id.login_activity_radiobutton_kiuer);
        helperRadioButton = (RadioButton) findViewById(R.id.login_activity_radiobutton_helper);
        loginButton = (Button) findViewById(R.id.loginBtn);

        if (savedInstanceState!=null){
            onRestoreInstanceState(savedInstanceState);
        }

        kiuerRadioButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mScrollView.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                kiuerRadioButton.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.accentColor)));
                helperRadioButton.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.primaryTextColor)));
                signupLabel.setTextColor(getResources().getColor(R.color.accentColor));
                loginButton.setTextColor(getResources().getColor(R.color.primaryColor));
                rememberMe.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.accentColor)));
                usernameEditText.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.accentColor)));
                passwordEditText.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.accentColor)));

                //CurrentUser.setConsumerFlag(true);
            }
        });

        helperRadioButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mScrollView.setBackgroundColor(getResources().getColor(R.color.accentColor));
                helperRadioButton.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.primaryColor)));
                kiuerRadioButton.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.primaryTextColor)));
                signupLabel.setTextColor(getResources().getColor(R.color.primaryColor));
                loginButton.setTextColor(getResources().getColor(R.color.accentColor));
                rememberMe.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.primaryColor)));
                usernameEditText.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.primaryColor)));
                passwordEditText.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.primaryColor)));
                //CurrentUser.setConsumerFlag(false);
            }
        });

        try {
            prefs = getSharedPreferences("UserData", 0);
            usernameEditText.setText(prefs.getString("username",""));
        } catch (Exception e) {

        }
        rememberMe = (CheckBox)findViewById(R.id.checkbox_text);

        rememberMe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is rememberMe checked?
                if (((CheckBox) v).isChecked()) {

                    prefs = getSharedPreferences("UserData", 0);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("username", usernameEditText.getText().toString());
                    editor.commit();
                }

            }
        });
    }

    /*public void loginBtnClicked(View view){
        Context context = getApplicationContext();
        String text;
        try {
            text = context.getString(R.string.opSuccessfully);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            //toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
            toast.show();
            /*
            Consumer consumer = Login.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            if(consumer != null){
                loggati e carica la home
            }

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            this.finish();
        } catch (Exception e) {
            text = context.getString(R.string.loginFailed);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            e.printStackTrace();
        }

    }

    public void signupClicked(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("usernameET", usernameEditText.getText().toString());
        outState.putString("passwordET", passwordEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        usernameEditText.setText(savedInstanceState.getString("usernameET"));
        passwordEditText.setText(savedInstanceState.getString("passwordET"));
    }
}
