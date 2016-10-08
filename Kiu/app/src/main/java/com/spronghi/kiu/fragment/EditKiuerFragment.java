package com.spronghi.kiu.fragment;

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

import com.spronghi.kiu.http.KiuerService;
import com.spronghi.kiu.model.Kiuer;


/**
 * Created by spronghi on 08/09/16.
 */
public class EditKiuerFragment extends ModelFragment<Kiuer>{
    private final static String TAG = "ViewKiuerFragment";
    private EditText usernameText;
    private EditText password;
    private EditText confPass;
    private EditText email;
    private EditText favoriteCityText;
    private Toolbar toolbar;
    private Button okButton;

    private Kiuer kiuer;

    @Override
    public void setModel(Kiuer kiuer){
        this.kiuer = kiuer;
    }
    private void setupView(){
        usernameText.setText(kiuer.getUsername());
        email.setText(kiuer.getEmail());
        favoriteCityText.setText(kiuer.getFavoriteCity());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_edit_kiuer, parent, false);

        usernameText = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_username);
        password = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_password);
        confPass = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_cpassword);
        email = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_email);
        favoriteCityText = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_favorite_city);
        okButton = (Button) layout.findViewById(R.id.fragment_edit_kiuer_ok_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_edit_kiuer_toolbar);

        setupToolbar();
        setupView();

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
                    kiuer.setUsername(usernameText.getText().toString());
                    kiuer.setPassword(password.getText().toString());
                    kiuer.setEmail(email.getText().toString());
                    kiuer.setFavoriteCity(favoriteCityText.getText().toString());
                    KiuerService.update(kiuer);

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