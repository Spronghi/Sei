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
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.setup.SetupView;

/**
 * Created by spronghi on 08/09/16.
 */
public class EditKiuerFragment extends ModelFragment<Kiuer>{
    private final static String TAG = "ViewKiuerFragment";
    private EditText usernameText;
    private EditText statusText;
    private EditText nameText;
    private EditText surnameText;
    private EditText birthText;
    private EditText telephoneText;
    private EditText favoriteCityText;
    private EditText passwordText;
    private Toolbar toolbar;
    private Button okButton;

    private Kiuer kiuer;

    @Override
    public void setModel(Kiuer kiuer){
        this.kiuer = kiuer;
    }
    private void setupView(){
        usernameText.setText(kiuer.getUsername());
        favoriteCityText.setText(kiuer.getFavoriteCity());
        passwordText.setText(kiuer.getPassword());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_edit_kiuer, parent, false);

        usernameText = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_username);
        statusText = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_status);
        nameText = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_name);
        surnameText = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_surname);
        birthText = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_birth);
        telephoneText = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_telephone);
        favoriteCityText = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_favorite_city);
        passwordText = (EditText) layout.findViewById(R.id.fragment_edit_kiuer_password);
        okButton = (Button) layout.findViewById(R.id.fragment_edit_kiuer_ok_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_edit_kiuer_toolbar);

        setupToolbar();
        setupView();

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
