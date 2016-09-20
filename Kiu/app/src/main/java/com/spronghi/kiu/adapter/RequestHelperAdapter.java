package com.spronghi.kiu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.request.ToHelperRequest;

import java.util.List;

/**
 * Created by spronghi on 13/09/16.
 */
public class RequestHelperAdapter extends RecyclerView.Adapter<RequestHelperAdapter.MyViewHolder> {
    private List<ToHelperRequest> list;

    public RequestHelperAdapter(List<ToHelperRequest> list) {
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sender, message;

        public MyViewHolder(View view) {
            super(view);
            sender = (TextView) view.findViewById(R.id.fragment_request_row_sender_text);
            message = (TextView) view.findViewById(R.id.fragment_request_row_message_text);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_request_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ToHelperRequest request = list.get(position);
        holder.sender.setText(request.getSender().getUsername());
        holder.message.setText(request.getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
