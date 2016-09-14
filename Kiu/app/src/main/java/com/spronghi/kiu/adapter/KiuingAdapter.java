package com.spronghi.kiu.adapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.kiuing.Kiuing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spronghi on 14/09/16.
 */
public class KiuingAdapter extends RecyclerView.Adapter<KiuingAdapter.MyViewHolder>{
    private List<String> list;
    private Map<String, Boolean> requestMap;

    public KiuingAdapter(Kiuing kiuing) {
        if(kiuing==null){
            list = new ArrayList<>();
            requestMap = new HashMap<>();
        } else {
            list = kiuing.getOperationList();
            requestMap = kiuing.getKiuingMap();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView message;

        public MyViewHolder(View view) {
            super(view);
            message = (TextView) view.findViewById(R.id.fragment_kiuing_row_text);

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
        if(requestMap.get(list.get(position))) {
            holder.message.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_black_24dp, 0, 0, 0);
        } else {
            holder.message.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_clear_black_24dp, 0, 0, 0);
        }
        holder.message.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
