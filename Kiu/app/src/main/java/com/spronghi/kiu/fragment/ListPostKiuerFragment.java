package com.spronghi.kiu.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.spronghi.kiu.adapter.PostKiuerAdapter;
import com.spronghi.kiu.model.PostKiuer;

/**
 * Created by MatteoSemolaArena on 09/09/2016.
 */
public class ListPostKiuerFragment extends ModelFragment {
    public static final String TAG = ListPostKiuerFragment.class.getSimpleName();
    private boolean isListEmpty=true;
    private Toolbar mToolbar;

    private List<PostKiuer> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PostKiuerAdapter mAdapter;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        final View layout = inflater.inflate(R.layout.fragment_post_list, parent, false);
        //Get the reference to the toolbar

        mToolbar = (Toolbar) layout.findViewById(R.id.fragment_post_list_toolbar);

        mToolbar.setLogo(R.mipmap.ic_launcher);
        mToolbar.setTitle(R.string.posts);
        mToolbar.inflateMenu(R.menu.menu_mtoolbar_postlist);

        //We get the reference to the SearchView
        MenuItem searchItem = mToolbar.getMenu().findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
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
                    //setListContent(query);
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
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });


        recyclerView = (RecyclerView) layout.findViewById(R.id.fragment_post_list_rvpost);

        mAdapter = new PostKiuerAdapter(postList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

       /* if(isListEmpty) {
            postList.clear();
            List<PostKiuer> list = PostKiuerService.getAll();
            for (PostKiuer ann : list) {
                if (ann.isOpen()) {
                    postList.add(ann);
                }
            }
        } else {
            isListEmpty = true;
        }

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PostKiuer post = postList.get(position);
                SelectedAnnouncement.set(post);
                Fragment fragment = new HelperViewFragment();
                FragmentManager fragmentmanager = getFragmentManager();
                fragmentmanager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
                //postList.clear();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        return layout;

    }
    /*private void setListContent(String city){
        postList.clear();
        for(PostKiuer announcement : AnnouncementService.getAll()) {
            if (announcement.isOpen() && announcement.getPlace().getCity().toLowerCase().equals(city.toLowerCase())) {
                postList.add(announcement);
            }
        }

        mAdapter.notifyDataSetChanged();
    }*/

    /**
     * Utility method to display the subtitle
     *
     * @param subtitle The subtitle to be displayed
     */
    private void updateSubtitle(final String subtitle) {
        mToolbar.setSubtitle(subtitle);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    @Override
    public void setModel(Object model) {

    }

    /**
     * RecyclerView doesnâ€™t have OnItemClickListener
     * method too to identify item click.
     * You need to write your own class extending RecyclerView.OnItemTouchListener.
     */


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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }


}