package com.zn.rxjavademo.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zn.rxjavademo.R;

public class ImageInfoHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_grid_image)
    ImageView image;
    @BindView(R.id.item_grid_description)
    TextView description;

    public ImageView getImage() {
        return image;
    }

    public TextView getDescription() {
        return description;
    }

    public ImageInfoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
