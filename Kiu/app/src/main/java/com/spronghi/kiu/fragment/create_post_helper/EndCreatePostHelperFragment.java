package com.spronghi.kiu.fragment.create_post_helper;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.spronghi.kiu.R;
import com.spronghi.kiu.fragment.FragmentFactory;
import com.spronghi.kiu.fragment.ModelFragment;
import com.spronghi.kiu.model.PostHelper;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.setup.DateFormatter;
import com.spronghi.kiu.setup.SetupView;

import java.util.Calendar;

/**
 * Created by spronghi on 10/09/16.
 */
public class EndCreatePostHelperFragment extends ModelFragment<PostHelper>{
    private PostHelper post;

    private EditText endDateText;
    private EditText endHourText;

    private Button createButton;
    private Button previewButton;

    @Override
    public void setModel(PostHelper model) {
        post=model;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_create_end_date, parent, false);

        endDateText = (EditText) layout.findViewById(R.id.fragment_create_end_date_text);
        endHourText = (EditText) layout.findViewById(R.id.fragment_create_end_hour_text);
        createButton = (Button) layout.findViewById(R.id.fragment_create_end_date_create_button);
        previewButton = (Button) layout.findViewById(R.id.fragment_create_end_date_preview_button);

        endDateText.setText(getResources().getString(R.string.ask_for_end_date));
        endHourText.setText(getResources().getString(R.string.ask_for_end_hour));

        SetupView.setEditTextForDate(endDateText, getActivity());
        SetupView.setEditTextForTime(endHourText, getActivity());

        final FragmentManager manager = this.getFragmentManager();
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (endDateText.getText().toString().equals("") || endDateText.getText().toString().equals(getResources().getString(R.string.ask_for_end_date))) {
                    Toast.makeText(getActivity(), R.string.ask_for_end_date, Toast.LENGTH_SHORT).show();
                } else if (endHourText.getText().toString().equals("") || endHourText.getText().toString().equals(getResources().getString(R.string.ask_for_end_hour))) {
                    Toast.makeText(getActivity(), R.string.ask_for_end_hour, Toast.LENGTH_SHORT).show();
                } else if (Calendar.getInstance().getTime().after(DateFormatter.parseDate(endDateText.getText().toString()))){
                    post.setEndDate(endDateText.getText().toString()+" "+endHourText.getText().toString());

                    //TO DO create post helper
                } else if(Calendar.getInstance().getTime().after(DateFormatter.parseDate(endDateText.getText().toString()))){
                    Toast.makeText(getActivity(), R.string.start_date_before_today, Toast.LENGTH_SHORT).show();
                } else {
                    post.setEndDate(endDateText.getText().toString()+" "+endHourText.getText().toString());

                    //TO DO create post helper
                }
            }
        });
        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (endDateText.getText().toString().equals("") || endDateText.getText().toString().equals(getResources().getString(R.string.ask_for_end_date))) {
                    Toast.makeText(getActivity(), R.string.ask_for_end_date, Toast.LENGTH_SHORT).show();
                } else if (endHourText.getText().toString().equals("") || endHourText.getText().toString().equals(getResources().getString(R.string.ask_for_end_hour))) {
                    Toast.makeText(getActivity(), R.string.ask_for_end_hour, Toast.LENGTH_SHORT).show();
                } else if (Calendar.getInstance().getTime().after(DateFormatter.parseDate(endDateText.getText().toString()))) {
                    post.setEndDate(endDateText.getText().toString() + " " + endHourText.getText().toString());
                    post.setCost(post.getHelper().getFavoriteCost());
                    ModelFragment<PostHelper> modelFragment = FragmentFactory.getInstance("ViewPostHelperFragment");
                    modelFragment.setModel(post);
                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, modelFragment, "view_post_end_date")
                            .addToBackStack(null)
                            .commit();
                } else if (Calendar.getInstance().getTime().after(DateFormatter.parseDate(endDateText.getText().toString()))) {
                    Toast.makeText(getActivity(), R.string.start_date_before_today, Toast.LENGTH_SHORT).show();
                } else {
                    post.setEndDate(endDateText.getText().toString() + " " + endHourText.getText().toString());

                    ModelFragment<PostHelper> modelFragment = FragmentFactory.getInstance("ViewPostHelperFragment");
                    modelFragment.setModel(post);
                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, modelFragment, "view_post_end_date")
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return layout;
    }
}
