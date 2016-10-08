package com.spronghi.kiu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.spronghi.kiu.R;

import com.spronghi.kiu.activity.MainActivity;
import com.spronghi.kiu.http.HelperService;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.runtime.CurrentUser;


/**
 * Created by spronghi on 08/09/16.
 */
public class EditHelperFragment extends ModelFragment<Helper>{
    private final static String TAG = "ViewHelperFragment";
    private EditText usernameText;
    private EditText password;
    private EditText confPass;
    private EditText email;
    private EditText favoriteCityText;
    private EditText favoriteCostText;
    private Button okButton;
    private Toolbar toolbar;

    private Helper helper;

    @Override
    public void setModel(Helper helper){
        this.helper = helper;
    }
    private void setupView(){
        usernameText.setText(helper.getUsername());
        favoriteCityText.setText(helper.getFavoriteCity());
        favoriteCostText.setText(helper.getFavoriteCostString());
        email.setText(helper.getEmail());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_edit_helper, parent, false);

        usernameText = (EditText) layout.findViewById(R.id.fragment_edit_helper_username);
        password = (EditText) layout.findViewById(R.id.fragment_edit_helper_password);
        confPass = (EditText) layout.findViewById(R.id.fragment_edit_helper_cpassword);
        email = (EditText) layout.findViewById(R.id.fragment_edit_helper_email);
        favoriteCityText = (EditText) layout.findViewById(R.id.fragment_edit_helper_favorite_city);
        favoriteCostText = (EditText) layout.findViewById(R.id.fragment_edit_helper_favorite_cost);
        okButton = (Button) layout.findViewById(R.id.fragment_edit_helper_ok_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_edit_helper_toolbar);

        setupView();
        setupToolbar();

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
                    helper.setUsername(usernameText.getText().toString());
                    helper.setFavoriteCity(favoriteCityText.getText().toString());
                    helper.setFavoriteCost(Double.parseDouble(favoriteCostText.getText().toString().replace("€/h","").replace(",",".")));
                    helper.setPassword(password.getText().toString());
                    helper.setEmail(email.getText().toString());
                    HelperService.update(helper);

                    getFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance(FragmentControl.LIST_POST_KIUER)).addToBackStack(null).commit();

                }
            }
        });

        return layout;
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