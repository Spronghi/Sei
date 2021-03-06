package com.spronghi.kiu.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.spronghi.kiu.R;

import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.runtime.CurrentUser;
import com.spronghi.kiu.setup.DoubleFormatter;


/**
 * Created by spronghi on 08/09/16.
 */
public class ViewPostKiuerFragment extends ModelFragment<PostKiuer>{
    private TextView kiuerText;
    private TextView cityText;
    private TextView addressText;
    private TextView locationText;
    private TextView startText;
    private TextView durationText;
    private TextView costText;
    private Button requestButton;

    private Toolbar toolbar;
    private PostKiuer postKiuer;



    @Override
    public void setModel(PostKiuer model) {
        postKiuer = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_view_post_kiuer, parent, false);

        kiuerText = (TextView) layout.findViewById(R.id.fragment_view_post_kiuer_kiuer);
        cityText = (TextView) layout.findViewById(R.id.fragment_view_post_kiuer_city);
        addressText = (TextView) layout.findViewById(R.id.fragment_view_post_kiuer_address);
        locationText = (TextView) layout.findViewById(R.id.fragment_view_post_kiuer_location);
        startText = (TextView) layout.findViewById(R.id.fragment_view_post_kiuer_start);
        durationText = (TextView) layout.findViewById(R.id.fragment_view_post_kiuer_duration);
        costText = (TextView) layout.findViewById(R.id.fragment_view_post_kiuer_cost);
        requestButton = (Button) layout.findViewById(R.id.fragment_view_post_kiuer_request_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_view_post_kiuer_toolbar);

        setupToolbar();
        setupView();

        if (!(CurrentUser.isKiuer())){
            if(postKiuer.isOpen()){
                setupRequestButton();
            } else if(postKiuer.getToHelperFeedback() == 0 && CurrentUser.getHelper().getUsername().equals(postKiuer.getHelper().getUsername())){
                setupRateHelperButton();
            }
        } else if(postKiuer.getToKiuerFeedback() == 0 && !(postKiuer.isOpen()) && CurrentUser.getKiuer().getUsername().equals(postKiuer.getKiuer().getUsername())){
            setupRateKiuerButton();
        }


        return layout;
    }

    private void setupView() {
        double costPreview = (postKiuer.getCost()/60)*postKiuer.getDuration();
        kiuerText.setText(postKiuer.getKiuer().getUsername());
        cityText.setText(postKiuer.getPlace().getCity());
        addressText.setText(postKiuer.getPlace().getAddress());
        locationText.setText(postKiuer.getPlace().getLocation());
        startText.setText("Starts on " + postKiuer.getStartDateString());
        durationText.setText(Integer.toString(postKiuer.getDuration()) + " minutes");
        costText.setText(DoubleFormatter.format(costPreview) + "€");
        final FragmentManager manager = this.getFragmentManager();

        kiuerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<Kiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_KIUER);
                modelFragment.setModel(postKiuer.getKiuer());
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "view_kiuer")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    private void setupRequestButton(){
        requestButton.setVisibility(View.VISIBLE);
        requestButton.setClickable(true);
        final FragmentManager manager = this.getFragmentManager();
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.SEND_REQUEST_HELPER);
                modelFragment.setModel(postKiuer);
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "send_request")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    private void setupRateKiuerButton(){
        requestButton.setVisibility(View.VISIBLE);
        requestButton.setClickable(true);
        requestButton.setText(getContext().getString(R.string.rate_it));
        final FragmentManager manager = this.getFragmentManager();
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.FEEDBACK);
                modelFragment.setModel(postKiuer);
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "send_request")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void setupRateHelperButton(){
        requestButton.setVisibility(View.VISIBLE);
        requestButton.setClickable(true);
        requestButton.setText(getContext().getString(R.string.rate_it));
        final FragmentManager manager = this.getFragmentManager();
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.FEEDBACK);
                modelFragment.setModel(postKiuer);
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "send_request")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void setupToolbar() {
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