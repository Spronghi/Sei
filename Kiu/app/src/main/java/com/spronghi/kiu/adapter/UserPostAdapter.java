package com.spronghi.kiu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.runtime.CurrentUser;

import java.util.List;

/**
 * Created by spronghi on 20/09/16.
 */
public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.MyViewHolder> {
    private List<PostKiuer> postsList;
    private boolean isKiuer;

    public UserPostAdapter(List<PostKiuer> postsList, boolean isKiuer) {
        this.postsList = postsList;
        this.isKiuer = isKiuer;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView startDate, cost, location;
        public RatingBar bar;

        public MyViewHolder(View view) {
            super(view);
            bar = (RatingBar) view.findViewById(R.id.fragment_user_post_row_rating_bar);
            startDate = (TextView) view.findViewById(R.id.fragment_user_post_row_start_text);
            cost = (TextView) view.findViewById(R.id.fragment_user_post_row_cost_text);
            location = (TextView) view.findViewById(R.id.fragment_user_post_row_location_text);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user_post_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PostKiuer post = postsList.get(position);
        if(isKiuer){
            holder.bar.setRating(post.getToKiuerFeedback());
        } else {
            holder.bar.setRating(post.getToHelperFeedback());
        }
        holder.startDate.setText("Start: "+post.getStartDateString());
        holder.cost.setText("Cost: "+post.getCostString()+"€");
        holder.location.setText(post.getPlace().getCity()+", "+post.getPlace().getLocation());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

}
