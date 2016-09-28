package com.spronghi.kiu.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.spronghi.kiu.R;
import com.spronghi.kiu.adapter.DividerItemDecoration;
import com.spronghi.kiu.adapter.RequestKiuerAdapter;
import com.spronghi.kiu.request.ToKiuerRequest;
import com.spronghi.kiu.request.RequestChecker;

/**
 * Created by spronghi on 13/09/16.
 */
public class ListRequestKiuerFragment extends ModelFragment {
    public static final String TAG = ListRequestKiuerFragment.class.getSimpleName();
    private Toolbar toolbar;

    private List<ToKiuerRequest> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private RequestKiuerAdapter adapter;



    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_post_list, parent, false);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_post_list_toolbar);
        setupToolbar();

        recyclerView = (RecyclerView) layout.findViewById(R.id.fragment_post_list_recycler_view);

        adapter = new RequestKiuerAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        populateList();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ModelFragment<ToKiuerRequest> fragment = FragmentFactory.getInstance(FragmentControl.VIEW_REQUEST_KIUER);
                fragment.setModel(list.get(position));
                getFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).addToBackStack(null).commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return layout;

    }
    private void populateList(){
        list.clear();
        RequestChecker checker = new RequestChecker();
        List<ToKiuerRequest> newRequests = checker.checkForKiuerRequest();
        for(ToKiuerRequest request : newRequests){
            list.add(request);
        }

    }
    private void updateSubtitle(final String subtitle) {
        toolbar.setSubtitle(subtitle);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {}

    @Override
    public void setModel(Object model) {

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
        private ListRequestKiuerFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ListRequestKiuerFragment.ClickListener clickListener) {
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
}
