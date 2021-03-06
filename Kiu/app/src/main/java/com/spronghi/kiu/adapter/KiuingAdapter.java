package com.spronghi.kiu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.kiuing.Kiuing;
import com.spronghi.kiu.kiuing.KiuingOperation;
import com.spronghi.kiu.kiuing.KiuingUtil;
import com.spronghi.kiu.setup.DateFormatter;

import java.util.List;

/**
 * Created by spronghi on 28/09/16.
 */
public class KiuingAdapter extends RecyclerView.Adapter<KiuingAdapter.MyViewHolder> {
    private List<Kiuing> operationList;
    private Context context;

    public KiuingAdapter(List<Kiuing> operationList, Context context) {
        this.operationList = operationList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView message, location;

        public MyViewHolder(View view) {
            super(view);
            message = (TextView) view.findViewById(R.id.fragment_kiuing_row_text);
            location = (TextView) view.findViewById(R.id.fragment_kiuing_row_location);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_kiuing_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.location.setText(operationList.get(position).getPost().getPlace().getLocation());

        if(KiuingUtil.isStarted(operationList.get(position))) {
            holder.message.setText(context.getResources().getString(R.string.kiuiung_started));
        } else if(KiuingUtil.isFinished(operationList.get(position))){
            holder.message.setText(context.getResources().getString(R.string.kiuiung_finished));
        } else {
            holder.message.setText(KiuingUtil.minutesToStart(operationList.get(position)) + " " + context.getResources().getString(R.string.minutes_for_begin));
        }
    }

    @Override
    public int getItemCount() {
        return operationList.size();
    }

}
