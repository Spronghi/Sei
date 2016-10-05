package com.spronghi.kiu.fragment.create_post_kiuer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.spronghi.kiu.R;
import com.spronghi.kiu.fragment.FragmentControl;
import com.spronghi.kiu.fragment.FragmentFactory;
import com.spronghi.kiu.fragment.ModelFragment;
import com.spronghi.kiu.http.PostKiuerService;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.setup.DoubleFormatter;
import com.spronghi.kiu.setup.SetupView;

/**
 * Created by spronghi on 09/09/16.
 */
public class CostCreatePostKiuerFragment extends ModelFragment<PostKiuer> {
    private PostKiuer post;

    private EditText costText;
    private EditText durationText;
    private RadioButton radioButton;
    private Button previewButton;
    private Button createButton;
    private Toolbar toolbar;

    @Override
    public void setModel(PostKiuer model) {
        post=model;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_create_cost_duration, parent, false);

        costText = (EditText) layout.findViewById(R.id.fragment_create_cost_text);
        durationText = (EditText) layout.findViewById(R.id.fragment_create_cost_duration_text);
        previewButton = (Button) layout.findViewById(R.id.fragment_create_preview_button);
        createButton = (Button) layout.findViewById(R.id.fragment_create_create_button);
        radioButton = (RadioButton) layout.findViewById(R.id.fragment_create_cost_radio_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_create_cost_toolbar);

        setupToolbar();
        SetupView.setConstantEditText(costText,"€/h");
        SetupView.setConstantEditText(durationText," minutes");

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(costText.getText().toString())){
                    costText.setError("Empty");
                } else if(TextUtils.isEmpty(durationText.getText().toString())){
                    durationText.setError("Empty");
                } else {
                    if(!(durationText.getText().toString().contains(" minutes")))
                        durationText.setText(durationText.getText().toString() + " minutes");
                    double cost = (Double.parseDouble(costText.getText().toString().replace("€/h",""))/60)*(Integer.parseInt(durationText.getText().toString().replace(" minutes", "")));
                    radioButton.setText(DoubleFormatter.format(cost)+"€");
                }
            }
        });
        final FragmentManager manager = this.getFragmentManager();
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(costText.getText().toString())){
                    costText.setError("Empty");
                } else if(TextUtils.isEmpty(durationText.getText().toString())){
                    durationText.setError("Empty");
                } else {
                    post.setCost(Double.parseDouble(costText.getText().toString().replace("€/h","")));
                    post.setDuration(Integer.parseInt(durationText.getText().toString().replace(" minutes", "")));

                    PostKiuerService.create(post);
                    Toast.makeText(getActivity(), R.string.post_created, Toast.LENGTH_SHORT).show();

                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance(FragmentControl.LIST_POST_KIUER), "view_post_kiuer")
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(costText.getText().toString())){
                    costText.setError("Empty");
                } else if(TextUtils.isEmpty(durationText.getText().toString())){
                    durationText.setError("Empty");
                } else {
                    post.setCost(Double.parseDouble(costText.getText().toString().replace("€/h","")));
                    post.setDuration(Integer.parseInt(durationText.getText().toString().replace(" minutes", "")));

                    ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_POST_KIUER);
                    modelFragment.setModel(post);
                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, modelFragment, "view_post_kiuer")
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return layout;
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
