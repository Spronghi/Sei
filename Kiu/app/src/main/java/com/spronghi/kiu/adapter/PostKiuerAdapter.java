package com.spronghi.kiu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.spronghi.kiu.R;
import com.spronghi.kiu.model.PostKiuer;

/**
 * Created by MatteoSemolaArena on 09/09/2016.
 */
public class PostKiuerAdapter extends RecyclerView.Adapter<PostKiuerAdapter.MyViewHolder> {
    private List<PostKiuer> postsList;

    public PostKiuerAdapter(List<PostKiuer> postsList) {
        this.postsList = postsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, startDate, cost, location;

        public MyViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.fragment_post_kiuer_row_title_text);
            startDate = (TextView) view.findViewById(R.id.fragment_post_kiuer_row_start_text);
            cost = (TextView) view.findViewById(R.id.fragment_post_kiuer_row_cost_text);
            location = (TextView) view.findViewById(R.id.fragment_post_kiuer_row_location_text);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_post_kiuer_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PostKiuer post = postsList.get(position);
        holder.username.setText(post.getTitle());
        holder.startDate.setText("Start: "+post.getStartDateString());
        holder.cost.setText("Cost: "+post.getCostString()+"€");
        holder.location.setText(post.getPlace().getCity()+", "+post.getPlace().getLocation());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

}
