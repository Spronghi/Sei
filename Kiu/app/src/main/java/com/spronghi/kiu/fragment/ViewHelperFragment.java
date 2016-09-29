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
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.spronghi.kiu.R;

import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.runtime.CurrentUser;


/**
 * Created by spronghi on 08/09/16.
 */
public class ViewHelperFragment extends ModelFragment<Helper>{
    private final static String TAG = "ViewHelperFragment";
    private ImageView actionIcon;
    private TextView usernameText;
    private TextView email;
    private TextView favoriteCityText;
    private TextView favoriteCostText;
    private RatingBar feedbackBar;
    private LinearLayout ratingLinearLayout;
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
        email = (TextView) layout.findViewById(R.id.fragment_view_helper_email);
        favoriteCityText = (TextView) layout.findViewById(R.id.fragment_view_helper_favorite_city);
        favoriteCostText = (TextView) layout.findViewById(R.id.fragment_view_helper_favorite_cost);
        feedbackBar = (RatingBar) layout.findViewById(R.id.fragment_view_helper_ratingbar);
        actionIcon = (ImageView) layout.findViewById(R.id.fragment_view_helper_action_icon);
        ratingLinearLayout = (LinearLayout) layout.findViewById(R.id.fragment_view_helper_feedback_linearlayout);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_view_helper_toolbar);


        ratingLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO
            }
        });

        setupToolbar();
        setupView();

        return layout;
    }

    private void setupView(){
        usernameText.setText(helper.getUsername());
        email.setText(helper.getEmail());
        favoriteCityText.setText(helper.getFavoriteCity());
        favoriteCostText.setText(helper.getFavoriteCostString());
        //feedbackBar.setRating(QUALCOSA);

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