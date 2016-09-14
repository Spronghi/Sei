package com.spronghi.kiu.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.PostHelper;
import com.spronghi.kiu.runtime.CurrentUser;

/**
 * Created by spronghi on 10/09/16.
 */
public class ViewPostHelperFragment extends ModelFragment<PostHelper>{
    private TextView titleText;
    private TextView helperText;
    private TextView cityText;
    private TextView startText;
    private TextView endText;
    private TextView costText;
    private Button requestButton;

    private Toolbar toolbar;
    private PostHelper post;



    @Override
    public void setModel(PostHelper model) {
        post = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_view_post_helper, parent, false);

        titleText = (TextView) layout.findViewById(R.id.fragment_view_post_helper_title);
        helperText = (TextView) layout.findViewById(R.id.fragment_view_post_helper_helper);
        cityText = (TextView) layout.findViewById(R.id.fragment_view_post_helper_city);
        startText = (TextView) layout.findViewById(R.id.fragment_view_post_helper_start);
        endText = (TextView) layout.findViewById(R.id.fragment_view_post_helper_end);
        costText = (TextView) layout.findViewById(R.id.fragment_view_post_helper_cost);
        requestButton = (Button) layout.findViewById(R.id.fragment_view_post_helper_request_button);

        toolbar = (Toolbar) layout.findViewById(R.id.fragment_view_post_helper_toolbar);

        setupToolbar();
        setupView();
        if(CurrentUser.isKiuer())
            setupRequestButton();
        return layout;
    }

    private void setupView(){
        titleText.setText(post.getTitle());
        helperText.setText(post.getHelper().getUsername());
        cityText.setText(post.getCity());
        startText.setText("Starts on "+post.getStartDateString());
        endText.setText("Ends on "+post.getEndDateString());
        costText.setText(post.getCostString()+"€/h");

        final FragmentManager manager = this.getFragmentManager();
        helperText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<Helper> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_HELPER);
                modelFragment.setModel(post.getHelper());
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "view_helper")
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
                ModelFragment<Helper> modelFragment = FragmentFactory.getInstance(FragmentControl.SEND_REQUEST_KIUER);
                modelFragment.setModel(post.getHelper());
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "send_request")
                        .addToBackStack(null)
                        .commit();
            }
        });
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
