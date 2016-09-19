package com.spronghi.kiu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.spronghi.kiu.R;
import com.spronghi.kiu.adapter.DividerItemDecoration;
import com.spronghi.kiu.adapter.PostKiuerAdapter;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.request.HelperRequest;
import com.spronghi.kiu.request.Request;
import com.spronghi.kiu.runtime.CurrentUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spronghi on 13/09/16.
 */
public class SendRequestKiuerFragment extends ModelFragment<Helper>{
    private TextView titleText;
    private Button sendButton;

    private RecyclerView recyclerView;
    private PostKiuerAdapter adapter;
    private List<PostKiuer> list = new ArrayList<>();

    private Toolbar toolbar;
    private PostKiuer post;
    private Helper helper;

    @Override
    public void setModel(Helper model) {
        helper = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_send_request_kiuer, parent, false);

        titleText = (TextView) layout.findViewById(R.id.fragment_send_request_kiuer_title);
        sendButton = (Button) layout.findViewById(R.id.fragment_send_request_kiuer_send_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_send_request_kiuer_toolbar);
        
        recyclerView = (RecyclerView) layout.findViewById(R.id.fragment_send_request_kiuer_recycler_view);

        //TO DO get all the kiuer open posts

        setupToolbar();
        setupView();

        setupOpenPostList();

        return layout;
    }

    private void setupView() {
        final FragmentManager manager = this.getFragmentManager();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelperRequest request = new HelperRequest(CurrentUser.getKiuer(), helper, post, Request.SEND);
                //TO DO send request to kiuer
            }
        });
    }
    private void setupOpenPostList(){
        adapter = new PostKiuerAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        list.clear();
        // TO DO populate list

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                post = list.get(position);
                sendButton.setEnabled(true);
                Toast.makeText(getActivity(), getResources().getString(R.string.post_selected) ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
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

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private SendRequestKiuerFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SendRequestKiuerFragment.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
