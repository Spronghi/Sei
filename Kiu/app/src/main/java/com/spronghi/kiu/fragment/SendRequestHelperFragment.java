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
import com.spronghi.kiu.request.KiuerRequest;
import com.spronghi.kiu.request.Request;
import com.spronghi.kiu.runtime.CurrentUser;

/**
 * Created by spronghi on 13/09/16.
 */
public class SendRequestHelperFragment extends ModelFragment<PostKiuer>{
    private TextView titleText;
    private Button sendButton;

    private Toolbar toolbar;
    private PostKiuer post;

    @Override
    public void setModel(PostKiuer model) {
        post = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_send_request_helper, parent, false);

        titleText = (TextView) layout.findViewById(R.id.fragment_send_request_helper_title);
        sendButton = (Button) layout.findViewById(R.id.fragment_send_request_helper_send_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_send_request_helper_toolbar);

        setupToolbar();
        setupView();

        return layout;
    }

    private void setupView() {
        titleText.setText(titleText.getText()+" " + post.getKiuer().getUsername());

        final FragmentManager manager = this.getFragmentManager();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KiuerRequest request = new KiuerRequest(CurrentUser.getHelper(), post.getKiuer(), post,Request.SEND);
                //TO DO send request to kiuer
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
