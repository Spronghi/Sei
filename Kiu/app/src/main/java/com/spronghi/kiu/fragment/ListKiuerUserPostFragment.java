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

import com.spronghi.kiu.R;
import com.spronghi.kiu.adapter.DividerItemDecoration;
import com.spronghi.kiu.adapter.UserPostAdapter;
import com.spronghi.kiu.http.PostKiuerService;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.model.PostKiuer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spronghi on 20/09/16.
 */
public class ListKiuerUserPostFragment extends ModelFragment<Kiuer> {
    public static final String TAG = ListKiuerUserPostFragment.class.getSimpleName();
    private Toolbar toolbar;

    private List<PostKiuer> openPostList = new ArrayList<>();
    private List<PostKiuer> closedPostList = new ArrayList<>();
    private RecyclerView openRecyclerView;
    private RecyclerView closedRecyclerView;
    private UserPostAdapter openAdapter;
    private UserPostAdapter closedAdapter;
    private Kiuer kiuer;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_user_list, parent, false);

        toolbar = (Toolbar) layout.findViewById(R.id.fragment_user_list_toolbar);
        setupToolbar();

        openRecyclerView = (RecyclerView) layout.findViewById(R.id.fragment_user_list_open_recycler_view);
        setOpenRecyclerView();

        closedRecyclerView = (RecyclerView) layout.findViewById(R.id.fragment_user_list_closed_recycler_view);
        setClosedRecyclerView();

        populateLists();

        return layout;

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
    private void setOpenRecyclerView(){
        openAdapter = new UserPostAdapter(openPostList, true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        openRecyclerView.setLayoutManager(mLayoutManager);
        openRecyclerView.setItemAnimator(new DefaultItemAnimator());
        openRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        openRecyclerView.setAdapter(openAdapter);

        openRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), openRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ModelFragment<PostKiuer> fragment = FragmentFactory.getInstance(FragmentControl.VIEW_POST_KIUER);
                fragment.setModel(openPostList.get(position));
                getFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).addToBackStack(null).commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void populateLists(){
        openPostList.clear();
        closedPostList.clear();

        List<PostKiuer> list = PostKiuerService.getAllByKiuer(kiuer);
        for(PostKiuer post : list){
            if(post.isOpen()){
                openPostList.add(post);
            } else {
                closedPostList.add(post);
            }
        }
    }

    private void setClosedRecyclerView(){
        closedAdapter = new UserPostAdapter(closedPostList, true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        closedRecyclerView.setLayoutManager(mLayoutManager);
        closedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        closedRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        closedRecyclerView.setAdapter(closedAdapter);


        closedRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), closedRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ModelFragment<PostKiuer> fragment = FragmentFactory.getInstance(FragmentControl.VIEW_POST_KIUER);
                fragment.setModel(closedPostList.get(position));
                getFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).addToBackStack(null).commit();
            }

            @Override
            public void onLongClick(View view, int position) {}
        }));
    }


    private void updateSubtitle(final String subtitle) {
        toolbar.setSubtitle(subtitle);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {}

    @Override
    public void setModel(Kiuer model) {
        kiuer = model;
    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ListKiuerUserPostFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ListKiuerUserPostFragment.ClickListener clickListener) {
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
