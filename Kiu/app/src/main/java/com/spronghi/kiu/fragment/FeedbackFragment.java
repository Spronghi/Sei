package com.spronghi.kiu.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.spronghi.kiu.R;
import com.spronghi.kiu.http.PostKiuerService;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.runtime.CurrentUser;

/**
 * Created by spronghi on 03/10/16.
 */
public class FeedbackFragment  extends ModelFragment<PostKiuer>{
    private TextView userText;
    private TextView postText;
    private TextView titleText;
    private RatingBar ratingBar;
    private Button okButton;

    private Toolbar toolbar;
    private PostKiuer post;

    @Override
    public void setModel(PostKiuer model) {
        post = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_feedback, parent, false);

        titleText = (TextView) layout.findViewById(R.id.fragment_feedback_title);
        postText = (TextView) layout.findViewById(R.id.fragment_feedback_post);
        userText = (TextView) layout.findViewById(R.id.fragment_feedback_user);
        ratingBar = (RatingBar) layout.findViewById(R.id.fragment_feedback_rating_bar);
        okButton = (Button) layout.findViewById(R.id.fragment_feedback_ok_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_feedback_toolbar);

        setupToolbar();
        setupView();

        return layout;
    }

    private void setupView() {
        if(CurrentUser.isKiuer())
            userText.setText(post.getHelper().getUsername());
        else
            userText.setText(post.getKiuer().getUsername());

        final FragmentManager manager = this.getFragmentManager();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ratingBar.getRating() != 0) {
                    if (CurrentUser.isKiuer()) {
                        post.setToHelperFeedback(ratingBar.getRating());
                    } else {
                        post.setToKiuerFeedback(ratingBar.getRating());
                    }
                    PostKiuerService.update(post);
                    Toast.makeText(getContext(), R.string.successfully_rated, Toast.LENGTH_SHORT).show();
                    if(CurrentUser.isKiuer()){
                        ModelFragment<Kiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.LIST_USER_KIUER);
                        modelFragment.setModel(post.getKiuer());
                        manager.beginTransaction()
                                .replace(R.id.activity_main_frame_layout, modelFragment, "view_kiuer_post")
                                .addToBackStack(null)
                                .commit();
                    } else {
                        ModelFragment<Helper> modelFragment = FragmentFactory.getInstance(FragmentControl.LIST_USER_HELPER);
                        modelFragment.setModel(post.getHelper());
                        manager.beginTransaction()
                                .replace(R.id.activity_main_frame_layout, modelFragment, "view_helper_post")
                                .addToBackStack(null)
                                .commit();
                    }
                }
            }
        });

        userText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CurrentUser.isKiuer()){
                    ModelFragment<Helper> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_HELPER);
                    modelFragment.setModel(post.getHelper());
                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, modelFragment, "view_helper")
                            .addToBackStack(null)
                            .commit();
                } else {
                    ModelFragment<Kiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_KIUER);
                    modelFragment.setModel(post.getKiuer());
                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, modelFragment, "view_kiuer")
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        postText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_POST_KIUER);
                modelFragment.setModel(post);
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
