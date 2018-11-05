package com.kunall17.marsplayassignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private final Context context;
    private final List<String> urlList;

    public ImageAdapter(Context context, List<String> urlList) {
        this.urlList = urlList;
        this.context = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_layout, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        final String pictureUrl = urlList.get(position);
        Glide.with(context).load(pictureUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }
}
