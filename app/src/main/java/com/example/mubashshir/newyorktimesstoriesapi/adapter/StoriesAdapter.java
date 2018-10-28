package com.example.mubashshir.newyorktimesstoriesapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mubashshir.newyorktimesstoriesapi.R;
import com.example.mubashshir.newyorktimesstoriesapi.model.Story;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Story> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title=(TextView)view.findViewById(R.id.title);
            imageView=(ImageView)view.findViewById(R.id.imguser);
        }
    }


    public StoriesAdapter(Context mContext, List<Story> articles) {
        this.mContext = mContext;
        this.albumList = articles;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.story_layout_type, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Story story= albumList.get(position);

        holder.title.setText(story.getHeadline());

        String storyimage = story.getImage();
        if (!TextUtils.isEmpty(storyimage)) {
            try {
                Picasso.with(mContext).load(story.getImage())
                        .error(R.drawable.ic_launcher_background)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(holder.imageView);
            }catch (Exception e){
                Log.d("Image error**********",e.getMessage());
            }
        }


    }



    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
