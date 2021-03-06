package com.spronghi.kiu.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.http.KiuingService;
import com.spronghi.kiu.http.PostKiuerService;
import com.spronghi.kiu.http.ToHelperRequestService;
import com.spronghi.kiu.http.ToKiuerRequestService;
import com.spronghi.kiu.kiuing.Kiuing;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.request.Request;
import com.spronghi.kiu.request.ToHelperRequest;
import com.spronghi.kiu.request.ToKiuerRequest;

/**
 * Created by spronghi on 13/09/16.
 */
public class RequestHelperFragment extends ModelFragment<ToHelperRequest>{
    private TextView helperText;
    private TextView postText;
    private TextView titleText;
    private Button acceptButton;
    private Button refuseButton;

    private Toolbar toolbar;
    private ToHelperRequest request;

    @Override
    public void setModel(ToHelperRequest model) {
        request = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_request_helper, parent, false);

        titleText = (TextView) layout.findViewById(R.id.fragment_request_helper_title);
        postText = (TextView) layout.findViewById(R.id.fragment_request_helper_post);
        helperText = (TextView) layout.findViewById(R.id.fragment_request_helper_kiuer);
        acceptButton = (Button) layout.findViewById(R.id.fragment_request_helper_accept_button);
        refuseButton = (Button) layout.findViewById(R.id.fragment_request_helper_refuse_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_request_helper_toolbar);

        setupToolbar();
        setupView();

        return layout;
    }

    private void setupView() {
        titleText.setText(request.getMessage());
        helperText.setText(request.getSender().getUsername());
        final FragmentManager manager = this.getFragmentManager();
        if(request.getType().equals(Request.REFUSE)){
            acceptButton.setVisibility(View.INVISIBLE);
            refuseButton.setVisibility(View.INVISIBLE);
            request.setSeen(true);
            ToHelperRequestService.update(request);
        } else if(request.getType().equals(Request.ACCEPT)){
            acceptButton.setVisibility(View.INVISIBLE);
            request.setSeen(true);
            ToHelperRequestService.update(request);

            refuseButton.setText("Go to the Kiuing");
            refuseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelFragment<Kiuing> fragment = FragmentFactory.getInstance(FragmentControl.KIUING);
                    fragment.setModel(KiuingService.getAllByPostKiuer(request.getPost()).iterator().next());
                    manager.beginTransaction().replace(R.id.activity_main_frame_layout, fragment).commit();
                }
            });
        }
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request.setSeen(true);
                ToHelperRequestService.update(request);
                request.getPost().setHelper(request.getAddressee());
                PostKiuerService.update(request.getPost());
                ToKiuerRequest toKiuerRequest = new ToKiuerRequest(request.getAddressee(), request.getSender(), request.getPost(), Request.ACCEPT);
                ToKiuerRequestService.create(toKiuerRequest);
                //TO DO open kiuing
                manager.beginTransaction().replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance(FragmentControl.LIST_REQUEST_HELPER)).commit();
            }
        });
        refuseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request.setSeen(true);
                ToHelperRequestService.update(request);
                ToKiuerRequest toKiuerRequest = new ToKiuerRequest(request.getAddressee(), request.getSender(), request.getPost(), Request.REFUSE);
                ToKiuerRequestService.create(toKiuerRequest);
                manager.beginTransaction().replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance(FragmentControl.LIST_REQUEST_HELPER)).commit();
            }
        });
        helperText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<Kiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_KIUER);
                modelFragment.setModel(request.getSender());
                manager.beginTransaction()
                        .replace(R.id.activity_main_frame_layout, modelFragment, "view_sender")
                        .addToBackStack(null)
                        .commit();
            }
        });
        postText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelFragment<PostKiuer> modelFragment = FragmentFactory.getInstance(FragmentControl.VIEW_POST_KIUER);
                modelFragment.setModel(request.getPost());
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
