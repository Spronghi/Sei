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
import com.spronghi.kiu.http.HelperService;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.runtime.CurrentUser;

/**
 * Created by spronghi on 06/10/16.
 */
public class UserPostHelperFragment extends ModelFragment<Helper> {
    private final static String TAG = "ViewHelperFragment";
    private TextView closedText;
    private TextView openText;
    private Toolbar toolbar;

    private Helper helper;

    @Override
    public void setModel(Helper model) { helper= model; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_user_post, parent, false);

        openText = (TextView) layout.findViewById(R.id.fragment_user_post_open_text);
        closedText = (TextView) layout.findViewById(R.id.fragment_user_post_closed_text);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_user_list_toolbar);

        final FragmentManager manager = this.getFragmentManager();
        openText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<Helper> modelFragment = FragmentFactory.getInstance(FragmentControl.LIST_HELPER_USER_OPEN);
                modelFragment.setModel(helper);
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "list_post_helper")
                        .addToBackStack(null)
                        .commit();

            }
        });

        closedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<Helper> modelFragment = FragmentFactory.getInstance(FragmentControl.LIST_HELPER_USER_CLOSED);
                modelFragment.setModel(helper);
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "list_post_helper")
                        .addToBackStack(null)
                        .commit();

            }
        });

        setupToolbar();

        return layout;
    }

    private void setupToolbar() {
        final FragmentManager manager = this.getFragmentManager();
        toolbar.setTitle(R.string.posts);
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