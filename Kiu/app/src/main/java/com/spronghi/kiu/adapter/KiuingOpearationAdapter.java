package com.spronghi.kiu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.kiuing.KiuingOperation;

import java.util.List;

/**
 * Created by spronghi on 14/09/16.
 */
public class KiuingOpearationAdapter extends RecyclerView.Adapter<KiuingOpearationAdapter.MyViewHolder>{
    private List<KiuingOperation> operationList;

    public KiuingOpearationAdapter(List<KiuingOperation> operationList) {
        this.operationList = operationList;
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
                .inflate(R.layout.fragment_kiuing_operation_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(operationList.get(position).isDone()) {
            holder.message.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_black_24dp, 0, 0, 0);
        } else {
            holder.message.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_clear_black_24dp, 0, 0, 0);
        }
        holder.message.setText(operationList.get(position).getOperationMessage());
    }

    @Override
    public int getItemCount() {
        return operationList.size();
    }
}
