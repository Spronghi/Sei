package com.spronghi.kiu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.spronghi.kiu.R;
import com.spronghi.kiu.model.PostHelper;

/**
 * Created by MatteoSemolaArena on 09/09/2016.
 */
public class PostHelperAdapter extends RecyclerView.Adapter<PostHelperAdapter.MyViewHolder> {
    private List<PostHelper> postsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, startdate, enddate, city;

        public MyViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.id_fragment_post_list_row_username_label);
            startdate = (TextView) view.findViewById(R.id.id_fragment_post_list_row_addresslocation_label);
            enddate = (TextView) view.findViewById(R.id.id_fragment_post_list_row_enddate_label);
            city = (TextView) view.findViewById(R.id.id_fragment_post_list_row_city_label);
        }
    }


    public PostHelperAdapter(List<PostHelper> postsList) {
        this.postsList = postsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_post_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PostHelper post = postsList.get(position);
        holder.username.setText(post.getHelper().getUsername());
        holder.startdate.setText(post.getStartDateString());
        holder.enddate.setText(post.getEndDateString());
        holder.city.setText(post.getCity());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
}
