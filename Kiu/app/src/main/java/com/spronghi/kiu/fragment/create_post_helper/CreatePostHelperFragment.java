package com.spronghi.kiu.fragment.create_post_helper;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spronghi.kiu.R;
import com.spronghi.kiu.fragment.FragmentFactory;
import com.spronghi.kiu.fragment.ModelFragment;
import com.spronghi.kiu.model.PostHelper;
import com.spronghi.kiu.runtime.CurrentUser;
import com.spronghi.kiu.setup.SetupView;

/**
 * Created by spronghi on 10/09/16.
 */
public class CreatePostHelperFragment extends ModelFragment<PostHelper>{
    private PostHelper post;

    private EditText titleText;
    private EditText cityText;
    private Toolbar toolbar;
    private Button button;

    @Override
    public void setModel(PostHelper model) {
        post=model;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_create_post_title_city, parent, false);

        titleText = (EditText) layout.findViewById(R.id.fragment_create_post_title_city_title_text);
        cityText = (EditText) layout.findViewById(R.id.fragment_create_post_title_city_city_text);
        button = (Button) layout.findViewById(R.id.fragment_create_post_title_city_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_create_post_title_city_toolbar);

        setupToolbar();
        setupView();


        SetupView.clearEditText(titleText);
        SetupView.clearEditText(cityText);

        final FragmentManager manager = this.getFragmentManager();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleText.getText().toString().equals("") || titleText.getText().toString().equals(getResources().getString(R.string.ask_for_title))) {
                    Toast.makeText(getActivity(), R.string.ask_for_title, Toast.LENGTH_SHORT).show();
                } else if (cityText.getText().toString().equals("") || cityText.getText().toString().equals(getResources().getString(R.string.ask_for_status))) {
                    Toast.makeText(getActivity(), R.string.ask_for_status, Toast.LENGTH_SHORT).show();
                } else {
                    post.setTitle(titleText.getText().toString());
                    post.setCity(cityText.getText().toString());
                    ModelFragment<PostHelper> modelFragment = FragmentFactory.getInstance("StartCreatePostHelperFragment");
                    modelFragment.setModel(post);
                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, modelFragment, "create_post_start_date")
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return layout;
    }

    private void setupView(){
        if(post==null)
            post = new PostHelper();

        post.setHelper(CurrentUser.getHelper());
        post.setOpen(true);
    }

    private void setupToolbar(){
        final FragmentManager manager = this.getFragmentManager();
        toolbar.setTitle(R.string.title);

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
