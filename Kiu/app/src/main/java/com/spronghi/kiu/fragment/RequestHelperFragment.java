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
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.request.HelperRequest;

/**
 * Created by spronghi on 13/09/16.
 */
public class RequestHelperFragment extends ModelFragment<HelperRequest>{
    private TextView helperText;
    private TextView postText;
    private TextView titleText;
    private Button acceptButton;
    private Button refuseButton;

    private Toolbar toolbar;
    private HelperRequest request;

    @Override
    public void setModel(HelperRequest model) {
        request = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_request_helper, parent, false);

        titleText = (TextView) layout.findViewById(R.id.fragment_request_helper_title);
        postText = (TextView) layout.findViewById(R.id.fragment_request_helper_post);
        helperText = (TextView) layout.findViewById(R.id.fragment_request_helper_kiuer);
        acceptButton = (Button) layout.findViewById(R.id.fragment_request_helper_accept_button);
        refuseButton = (Button) layout.findViewById(R.id.fragment_request_helper_refuse_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_request_helper_toolbar);

        setupToolbar();
        setupView();

        return layout;
    }

    private void setupView() {
        titleText.setText(request.getMessage());
        helperText.setText(request.getSender().getUsername());
        postText.setText(request.getPost().getTitle());
        final FragmentManager manager = this.getFragmentManager();
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO accept and delete request
                //TO DO send to the helper an accepted notification
                //TO DO add the helper and the price to the post and update it
            }
        });
        refuseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO refuse and delete request
            }
        });
        helperText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<Kiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_KIUER);
                modelFragment.setModel(request.getSender());
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "view_sender")
                        .addToBackStack(null)
                        .commit();
            }
        });
        postText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_POST_HELPER);
                modelFragment.setModel(request.getPost());
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "view_post")
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
