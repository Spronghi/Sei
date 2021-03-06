package com.spronghi.kiu.fragment.create_post_kiuer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.spronghi.kiu.R;
import com.spronghi.kiu.fragment.FragmentControl;
import com.spronghi.kiu.fragment.FragmentFactory;
import com.spronghi.kiu.fragment.ModelFragment;
import com.spronghi.kiu.http.HelperService;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.runtime.CurrentUser;
import com.spronghi.kiu.setup.SetupView;

/**
 * Created by spronghi on 09/09/16.
 */
public class CreatePostKiuerFragment extends ModelFragment<PostKiuer> {
    private PostKiuer post;

    private Toolbar toolbar;
    private Button button;

    @Override
    public void setModel(PostKiuer model) {
        post = model;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_create_post, parent, false);

        button = (Button) layout.findViewById(R.id.fragment_create_post_title_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_create_post_toolbar);

        setupView();
        setupToolbar();

        final FragmentManager manager = this.getFragmentManager();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.CREATE_POST_KIUER_PLACE);
                modelFragment.setModel(post);
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "create_post_place")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return layout;
    }
    private void setupView(){
        if(post==null)
            post = new PostKiuer();

        post.setHelper(HelperService.get(3));
        post.setKiuer(CurrentUser.getKiuer());
        post.setOpen(true);
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
