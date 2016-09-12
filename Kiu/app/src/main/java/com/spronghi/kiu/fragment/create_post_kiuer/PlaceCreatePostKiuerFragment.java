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
import com.spronghi.kiu.model.Place;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.setup.SetupView;

/**
 * Created by spronghi on 09/09/16.
 */
public class PlaceCreatePostKiuerFragment extends ModelFragment<PostKiuer>{
    private PostKiuer post;

    private EditText cityText;
    private EditText locationText;
    private EditText addressText;
    private Toolbar toolbar;

    private Button button;

    @Override
    public void setModel(PostKiuer model) {
        post=model;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_create_place, parent, false);

        cityText = (EditText) layout.findViewById(R.id.fragment_create_city_text);
        locationText = (EditText) layout.findViewById(R.id.fragment_create_location_text);
        addressText = (EditText) layout.findViewById(R.id.fragment_create_address_text);
        button = (Button) layout.findViewById(R.id.fragment_create_place_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_create_place_toolbar);

        setupToolbar();
        SetupView.clearEditText(cityText);
        SetupView.clearEditText(addressText);
        SetupView.clearEditText(locationText);

        final FragmentManager manager = this.getFragmentManager();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cityText.getText().toString().equals("") || cityText.getText().toString().equals(getResources().getString(R.string.ask_for_city))) {
                    Toast.makeText(getActivity(), R.string.ask_for_city, Toast.LENGTH_SHORT).show();
                } else if (locationText.getText().toString().equals("") || locationText.getText().toString().equals(getResources().getString(R.string.ask_for_location))) {
                    Toast.makeText(getActivity(), R.string.ask_for_location, Toast.LENGTH_SHORT).show();
                } else if (addressText.getText().toString().equals("") || addressText.getText().toString().equals(getResources().getString(R.string.ask_for_address))) {
                    Toast.makeText(getActivity(), R.string.ask_for_address, Toast.LENGTH_SHORT).show();
                } else {
                    Place place = new Place();
                    place.setCity(cityText.getText().toString());
                    place.setLocation(locationText.getText().toString());
                    place.setAddress(addressText.getText().toString());
                    post.setPlace(place);

                    ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance("StartCreatePostKiuerFragment");
                    modelFragment.setModel(post);
                    manager.beginTransaction()
                            .replace(R.id.activity_main_frame_layout, modelFragment, "create_post_start_date_duration")
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
