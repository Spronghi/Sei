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
public class HistoricalPostAdapter extends RecyclerView.Adapter<HistoricalPostAdapter.MyViewHolder> {

    private List<PostKiuer> postsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView startdate, addresslocation;

        public MyViewHolder(View view) {
            super(view);
            //startdate = (TextView) view.findViewById(R.id.id_fragment_post_list_row_username_label);
            //addresslocation = (TextView) view.findViewById(R.id.id_fragment_post_list_row_addresslocation_label);
        }
    }


    public HistoricalPostAdapter(List<PostKiuer> postsList) {
        this.postsList = postsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_post_helper_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PostKiuer post = postsList.get(position);
        holder.startdate.setText(post.getStartDateString());
        holder.addresslocation.setText(post.getPlace().getAddressLocation());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

}
