package com.spronghi.kiu.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.spronghi.kiu.R;

import com.spronghi.kiu.model.Helper;


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
        password.setText(helper.getPassword());
        password.setText(helper.getPassword());
        confPass.setText(helper.getPassword());
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