package com.spronghi.kiu.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.runtime.CurrentUser;
import com.spronghi.kiu.setup.SetupView;

/**
 * Created by spronghi on 08/09/16.
 */
public class ViewKiuerFragment extends ModelFragment<Kiuer> {
    private ImageView actionIcon;
    private TextView usernameText;
    private TextView statusText;
    private TextView nameAndSurnameText;
    private TextView birthText;
    private TextView telephoneText;
    private TextView favoriteCityText;
    private TextView feedbackText;
    private Toolbar toolbar;
    private Kiuer kiuer;

    @Override
    public void setModel(Kiuer kiuer){
        this.kiuer = kiuer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_view_kiuer, parent, false);

        usernameText = (TextView) layout.findViewById(R.id.fragment_view_kiuer_username);
        statusText = (TextView) layout.findViewById(R.id.fragment_view_kiuer_status);
        nameAndSurnameText = (TextView) layout.findViewById(R.id.fragment_view_kiuer_name_and_surname);
        birthText = (TextView) layout.findViewById(R.id.fragment_view_kiuer_birth);
        telephoneText = (TextView) layout.findViewById(R.id.fragment_view_kiuer_telephone);
        favoriteCityText = (TextView) layout.findViewById(R.id.fragment_view_kiuer_favorite_city);
        feedbackText = (TextView) layout.findViewById(R.id.fragment_view_kiuer_feedback);
        actionIcon = (ImageView) layout.findViewById(R.id.fragment_view_kiuer_action_icon);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_view_kiuer_toolbar);

        setupToolbar();
        setupView();

        return layout;
    }

    private void setupView(){
        usernameText.setText(kiuer.getUsername());
        favoriteCityText.setText(kiuer.getFavoriteCity());

        final FragmentManager manager = this.getFragmentManager();

        if(CurrentUser.isKiuer() && CurrentUser.getKiuer().getUsername().equals(kiuer.getUsername())){
            actionIcon.setImageResource(R.mipmap.ic_mode_edit_white_24dp);
            actionIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ModelFragment<Kiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.EDIT_KIUER);
                    modelFragment.setModel(kiuer);
                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, modelFragment, "edit_kiuer")
                            .addToBackStack(null)
                            .commit();
                }
            });
        }

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