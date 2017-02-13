package com.zn.rxjavademo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.zn.rxjavademo.R;
import com.zn.rxjavademo.domain.ImageInfoBean;
import com.zn.rxjavademo.holder.ImageInfoHolder;

import java.util.List;


public class MapAdapter extends RecyclerView.Adapter<ImageInfoHolder> {
    @Override
    public ImageInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ImageInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageInfoHolder holder, int position) {

        Glide.with(holder.itemView.getContext()).load(images.get(position).image_url).into(holder.getImage());
        holder.getDescription().setText(images.get(position).description);
    }

    @Override
    public int getItemCount() {
        return null == images ? 0 : images.size();
    }

    private List<ImageInfoBean> images;

    public void setImages(List<ImageInfoBean> images) {
        this.images = images;
        notifyDataSetChanged();
    }
}
