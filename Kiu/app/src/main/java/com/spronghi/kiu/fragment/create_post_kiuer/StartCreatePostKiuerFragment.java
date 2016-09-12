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
import android.widget.Toast;

import com.spronghi.kiu.R;
import com.spronghi.kiu.fragment.FragmentFactory;
import com.spronghi.kiu.fragment.ModelFragment;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.setup.DateFormatter;
import com.spronghi.kiu.setup.SetupView;

import java.util.Calendar;


/**
 * Created by spronghi on 09/09/16.
 */
public class StartCreatePostKiuerFragment extends ModelFragment<PostKiuer>{
    private PostKiuer post;

    private EditText startDateText;
    private EditText startHourText;
    private Toolbar toolbar;
    private Button button;

    @Override
    public void setModel(PostKiuer model) {
        post=model;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_create_start_date, parent, false);

        startDateText = (EditText) layout.findViewById(R.id.fragment_create_start_date_text);
        startHourText = (EditText) layout.findViewById(R.id.fragment_create_start_hour_text);
        button = (Button) layout.findViewById(R.id.fragment_create_start_date_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_create_start_date_duration_toolbar);

        setupToolbar();

        SetupView.setEditTextForDate(startDateText, getActivity());
        SetupView.setEditTextForTime(startHourText, getActivity());

        final FragmentManager manager = this.getFragmentManager();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startDateText.getText().toString().equals("") || startDateText.getText().toString().equals(getResources().getString(R.string.ask_for_start_date))) {
                    Toast.makeText(getActivity(), R.string.ask_for_start_date, Toast.LENGTH_SHORT).show();
                } else if (startHourText.getText().toString().equals("") || startHourText.getText().toString().equals(getResources().getString(R.string.ask_for_start_hour))) {
                    Toast.makeText(getActivity(), R.string.ask_for_start_hour, Toast.LENGTH_SHORT).show();
                } else if (Calendar.getInstance().getTime().after(DateFormatter.parseDate(startDateText.getText().toString()))){
                    post.setStartDate(startDateText.getText().toString()+" "+startHourText.getText().toString());

                    ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance("CostCreatePostKiuerFragment");
                    modelFragment.setModel(post);
                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, modelFragment, "create_cost")
                            .addToBackStack(null)
                            .commit();
                } else if(Calendar.getInstance().getTime().after(DateFormatter.parseDate(startDateText.getText().toString()))){
                    Toast.makeText(getActivity(), R.string.start_date_before_today, Toast.LENGTH_SHORT).show();
                } else {
                    post.setStartDate(startDateText.getText().toString()+" "+startHourText.getText().toString());

                    ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance("CostCreatePostKiuerFragment");
                    modelFragment.setModel(post);
                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, modelFragment, "create_cost")
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
