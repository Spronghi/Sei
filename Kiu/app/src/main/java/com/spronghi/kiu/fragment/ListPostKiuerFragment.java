package com.spronghi.kiu.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import com.spronghi.kiu.adapter.PostKiuerAdapter;
import com.spronghi.kiu.http.PostKiuerService;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.runtime.CurrentUser;

/**
 * Created by MatteoSemolaArena on 09/09/2016.
 */
public class ListPostKiuerFragment extends ModelFragment {
    public static final String TAG = ListPostKiuerFragment.class.getSimpleName();
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private List<PostKiuer> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PostKiuerAdapter mAdapter;
    SearchView searchView;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_post_list, parent, false);

        toolbar = (Toolbar) layout.findViewById(R.id.fragment_post_list_toolbar);
        fab = (FloatingActionButton) layout.findViewById(R.id.fragment_post_list_fab);
        recyclerView = (RecyclerView) layout.findViewById(R.id.fragment_post_list_recycler_view);

        setupToolbar();
        setupRecyclerView();
        setupFab();

        setListContent();

        return layout;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {}

    @Override
    public void setModel(Object model) {}

    private void setupToolbar(){
        toolbar.setLogo(R.mipmap.ic_logo);
        toolbar.setTitle(R.string.posts);
        toolbar.inflateMenu(R.menu.menu_mtoolbar_postlist);

        MenuItem searchItem = toolbar.getMenu().findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        if (searchItem != null) {
            searchView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Search clicked!");
                }
            });
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    Log.d(TAG, "Search closed!");
                    return false;
                }
            });
            searchView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Search click!");
                }
            });
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    setListContent();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.d(TAG, "Query text changed!" + newText);
                    return false;
                }
            });
            searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
                @Override
                public boolean onSuggestionSelect(int position) {
                    return false;
                }

                @Override
                public boolean onSuggestionClick(int position) {
                    return false;
                }
            });
        }
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }
    private void setupRecyclerView(){
        mAdapter = new PostKiuerAdapter(postList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        setListContent();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {        fab.setBackgroundTintList(ColorStateList.valueOf(R.color.accentColor));

                ModelFragment<PostKiuer> fragment = FragmentFactory.getInstance(FragmentControl.VIEW_POST_KIUER);
                fragment.setModel(postList.get(position));
                getFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onLongClick(View view, int position) {}
        }));
    }
    private void setupFab(){
        if(CurrentUser.isKiuer()){
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance(FragmentControl.CREATE_POST_KIUER))
                            .addToBackStack(null)
                            .commit();
                }
            });
        } else {
            fab.setVisibility(View.INVISIBLE);
            fab.setClickable(false);
        }

    }
    private void setListContent(){
        String city;
        if(TextUtils.isEmpty(searchView.getQuery())){
            city = CurrentUser.isKiuer() ? CurrentUser.getKiuer().getFavoriteCity() : CurrentUser.getHelper().getFavoriteCity();
        } else {
            city = searchView.getQuery().toString();
        }
        postList.clear();
        for(PostKiuer post :PostKiuerService.getAllByCity(city)) {
            if (post.isOpen()) {
                postList.add(post);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
    private void updateSubtitle(final String subtitle) {
        toolbar.setSubtitle(subtitle);
    }


    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ListPostKiuerFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ListPostKiuerFragment.ClickListener clickListener) {
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