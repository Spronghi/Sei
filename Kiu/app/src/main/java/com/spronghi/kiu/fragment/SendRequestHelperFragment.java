package com.spronghi.kiu.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.http.ToKiuerRequestService;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.request.ToKiuerRequest;
import com.spronghi.kiu.request.Request;
import com.spronghi.kiu.runtime.CurrentUser;

/**
 * Created by spronghi on 13/09/16.
 */
public class SendRequestHelperFragment extends ModelFragment<PostKiuer>{
    private TextView titleText;
    private Button sendButton;

    private Toolbar toolbar;
    private PostKiuer post;
    private static FragmentManager manager;
    private static ToKiuerRequest request;
    @Override
    public void setModel(PostKiuer model) {
        post = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_send_request_helper, parent, false);

        titleText = (TextView) layout.findViewById(R.id.fragment_send_request_helper_title);
        sendButton = (Button) layout.findViewById(R.id.fragment_send_request_helper_send_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_send_request_helper_toolbar);

        setupToolbar();
        setupView();

        return layout;
    }

    private void setupView() {
        titleText.setText(titleText.getText().toString()+" " + post.getKiuer().getUsername());

        manager = this.getFragmentManager();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request = new ToKiuerRequest(CurrentUser.getHelper(), post.getKiuer(), post,Request.SEND);
                ConfirmDialog dialog = new ConfirmDialog ();
                dialog.show(manager.beginTransaction(), "dialog");
            }
        });
    }
    private void setupToolbar() {
        manager = this.getFragmentManager();

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
    public static class ConfirmDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("Kiu")
                    .setMessage("Do you want to send it?")
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .setPositiveButton(android.R.string.yes,  new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToKiuerRequestService.create(request);

                            manager.beginTransaction()
                                    .replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance(FragmentControl.LIST_POST_KIUER), "edit_helper")
                                    .addToBackStack(null)
                                    .commit();
                        }
                    })
                    .create();
        }
    }
}
