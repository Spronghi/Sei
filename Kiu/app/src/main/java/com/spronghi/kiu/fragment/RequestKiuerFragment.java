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
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.request.KiuerRequest;

/**
 * Created by spronghi on 13/09/16.
 */
public class RequestKiuerFragment extends ModelFragment<KiuerRequest> {
    private TextView helperText;
    private TextView postText;
    private TextView titleText;
    private Button acceptButton;
    private Button refuseButton;

    private Toolbar toolbar;
    private KiuerRequest request;

    @Override
    public void setModel(KiuerRequest model) {
        request = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_request_kiuer, parent, false);

        titleText = (TextView) layout.findViewById(R.id.fragment_request_kiuer_title);
        postText = (TextView) layout.findViewById(R.id.fragment_request_kiuer_post);
        helperText = (TextView) layout.findViewById(R.id.fragment_request_kiuer_helper);
        acceptButton = (Button) layout.findViewById(R.id.fragment_request_kiuer_accept_button);
        refuseButton = (Button) layout.findViewById(R.id.fragment_request_kiuer_refuse_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_request_kiuer_toolbar);

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
                ModelFragment<Helper> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_HELPER);
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
                ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_POST_KIUER);
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
