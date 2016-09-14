package com.spronghi.kiu.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.runtime.CurrentUser;
import com.spronghi.kiu.setup.SetupView;

/**
 * Created by spronghi on 08/09/16.
 */
public class ViewHelperFragment extends ModelFragment<Helper>{
    private final static String TAG = "ViewHelperFragment";
    private ImageView actionIcon;
    private TextView usernameText;
    private TextView statusText;
    private TextView nameAndSurnameText;
    private TextView birthText;
    private TextView telephoneText;
    private TextView favoriteCityText;
    private TextView favoriteCostText;
    private TextView feedbackText;
    private Toolbar toolbar;

    private Helper helper;

    @Override
    public void setModel(Helper helper){
        this.helper = helper;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_view_helper, parent, false);

        usernameText = (TextView) layout.findViewById(R.id.fragment_view_helper_username);
        statusText = (TextView) layout.findViewById(R.id.fragment_view_helper_status);
        nameAndSurnameText = (TextView) layout.findViewById(R.id.fragment_view_helper_name_and_surname);
        birthText = (TextView) layout.findViewById(R.id.fragment_view_helper_birth);
        telephoneText = (TextView) layout.findViewById(R.id.fragment_view_helper_telephone);
        favoriteCityText = (TextView) layout.findViewById(R.id.fragment_view_helper_favorite_city);
        favoriteCostText = (TextView) layout.findViewById(R.id.fragment_view_helper_favorite_cost);
        feedbackText = (TextView) layout.findViewById(R.id.fragment_view_helper_feedback);
        actionIcon = (ImageView) layout.findViewById(R.id.fragment_view_helper_action_icon);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_view_helper_toolbar);

        setupToolbar();
        setupView();

        return layout;
    }

    private void setupView(){
        usernameText.setText(helper.getUsername());
        statusText.setText(helper.getProfileStatus());
        nameAndSurnameText.setText(helper.getName()+" "+helper.getSurname());
        birthText.setText(helper.getBirthString());
        telephoneText.setText(helper.getTelephone());
        favoriteCityText.setText(helper.getFavoriteCity());
        favoriteCostText.setText(helper.getFavoriteCostString());
        feedbackText.setText(helper.getFeedback() == 0 ? "No Feedback" : Float.toString(helper.getFeedback()));

        final FragmentManager manager = this.getFragmentManager();

        if(!(CurrentUser.isKiuer()) && CurrentUser.getHelper().getUsername().equals(helper.getUsername())){
            actionIcon.setImageResource(R.mipmap.ic_mode_edit_white_24dp);
            actionIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ModelFragment<Helper> modelFragment = FragmentFactory.getInstance(FragmentControl.EDIT_HELPER);
                    modelFragment.setModel(helper);
                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, modelFragment, "edit_helper")
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
