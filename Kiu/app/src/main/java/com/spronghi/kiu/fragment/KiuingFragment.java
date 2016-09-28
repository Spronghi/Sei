package com.spronghi.kiu.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.adapter.DividerItemDecoration;
import com.spronghi.kiu.adapter.KiuingOpearationAdapter;
import com.spronghi.kiu.kiuing.Kiuing;
import com.spronghi.kiu.kiuing.KiuingOperation;
import com.spronghi.kiu.runtime.CurrentUser;
import com.spronghi.kiu.setup.DateFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spronghi on 14/09/16.
 */
public class KiuingFragment extends ModelFragment<Kiuing>{
    private TextView titleText;
    private Button refreshButton;
    private List<KiuingOperation> operationList = new ArrayList<>();

    private RecyclerView recyclerView;
    private KiuingOpearationAdapter adapter;
    private Toolbar toolbar;
    private Kiuing kiuing;

    @Override
    public void setModel(Kiuing model) {
        kiuing = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_kiuing, parent, false);

        titleText = (TextView) layout.findViewById(R.id.fragment_kiuing_title);
        refreshButton = (Button) layout.findViewById(R.id.fragment_kiuing_refresh_button);
        toolbar = (Toolbar) layout.findViewById(R.id.fragment_kiuing_toolbar);

        recyclerView = (RecyclerView) layout.findViewById(R.id.fragment_kiuing_recycler_view);

        Log.d("kiuing", kiuing.toString());
        setupList();
        setupToolbar();
        setupView();

        return layout;
    }

    private void setupView() {
        String minutes = DateFormatter.minusNow(kiuing.getPost().getStartDate());
        String started = DateFormatter.minusNow(kiuing.getPost().getStartDate(), kiuing.getPost().getDuration());

        if(started.equals(DateFormatter.AFTER)){
            titleText.setText(getResources().getString(R.string.kiuiung_started));
            for(KiuingOperation operation : kiuing.getOperationList()){
                operationList.add(operation);
            }
            adapter.notifyDataSetChanged();
        } else if(minutes.equals(DateFormatter.BEFORE)){
            titleText.setText(getResources().getString(R.string.kiuiung_finished));
        } else {
            titleText.setText(minutes + " " + getResources().getString(R.string.minutes_for_begin));
        }
        refreshButton.setEnabled(true);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String minutes = DateFormatter.minusNow(kiuing.getPost().getStartDate());
                if(minutes.equals(DateFormatter.BEFORE)){
                    titleText.setText(getResources().getString(R.string.kiuiung_started));
                    operationList.clear();
                    for(KiuingOperation operation : kiuing.getOperationList()){
                        operationList.add(operation);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    titleText.setText(minutes + " "+getResources().getString(R.string.minutes_for_begin));
                }
            }
        });
    }

    private void setupList(){
        adapter = new KiuingOpearationAdapter(operationList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        final FragmentManager manager = this.getFragmentManager();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(!(CurrentUser.isKiuer())) {
                    ConfirmDialog dialog = new ConfirmDialog ();
                    dialog.show(manager.beginTransaction(), "dialog");
                }
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
        private KiuingFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final KiuingFragment.ClickListener clickListener) {
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
                            // TO DO send the requesto to the kiuer
                        }
                    })
                    .create();
        }
    }
}
