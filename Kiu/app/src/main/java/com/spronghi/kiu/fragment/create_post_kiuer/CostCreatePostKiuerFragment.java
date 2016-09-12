package com.spronghi.kiu.fragment.create_post_kiuer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.spronghi.kiu.R;
import com.spronghi.kiu.fragment.FragmentFactory;
import com.spronghi.kiu.fragment.ModelFragment;
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
                if (costText.getText().toString().equals("") || costText.getText().toString().equals(getResources().getString(R.string.ask_for_cost))) {
                    Toast.makeText(getActivity(), R.string.ask_for_city, Toast.LENGTH_SHORT).show();
                } else if (durationText.getText().toString().equals("") || durationText.getText().toString().equals(getResources().getString(R.string.ask_for_duration))) {
                    Toast.makeText(getActivity(), R.string.ask_for_duration, Toast.LENGTH_SHORT).show();
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
                if (costText.getText().toString().equals("") || costText.getText().toString().equals(getResources().getString(R.string.ask_for_cost))) {
                    Toast.makeText(getActivity(), R.string.ask_for_city, Toast.LENGTH_SHORT).show();
                } else if (durationText.getText().toString().equals("") || durationText.getText().toString().equals(getResources().getString(R.string.ask_for_duration))) {
                    Toast.makeText(getActivity(), R.string.ask_for_duration, Toast.LENGTH_SHORT).show();
                } else {
                    post.setCost(Double.parseDouble(costText.getText().toString().replace("€/h","")));
                    post.setDuration(Integer.parseInt(durationText.getText().toString().replace(" minutes", "")));

                    //TO DO create the post on database
                }
            }
        });

        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (costText.getText().toString().equals("") || costText.getText().toString().equals(getResources().getString(R.string.ask_for_cost))) {
                    Toast.makeText(getActivity(), R.string.ask_for_cost, Toast.LENGTH_SHORT).show();
                } else {
                    post.setCost(Double.parseDouble(costText.getText().toString().replace("€/h","")));
                    post.setDuration(Integer.parseInt(durationText.getText().toString().replace(" minutes", "")));

                    ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance("ViewPostKiuerFragment");
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
