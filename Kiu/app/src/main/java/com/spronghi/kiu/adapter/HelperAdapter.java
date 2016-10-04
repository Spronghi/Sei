package com.spronghi.kiu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.PostKiuer;

import java.util.List;

/**
 * Created by spronghi on 03/10/16.
 */
public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.MyViewHolder>{
    private List<Helper> list;

    public HelperAdapter(List<Helper> list) {
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, city, cost;

        public MyViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.fragment_helper_row_username_text);
            city = (TextView) view.findViewById(R.id.fragment_helper_row_favorite_city_text);
            cost = (TextView) view.findViewById(R.id.fragment_helper_row_favorite_cost_text);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_helper_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Helper helper = list.get(position);
        holder.username.setText(helper.getUsername());
        holder.city.setText(helper.getFavoriteCity());
        holder.cost.setText(helper.getFavoriteCostString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
