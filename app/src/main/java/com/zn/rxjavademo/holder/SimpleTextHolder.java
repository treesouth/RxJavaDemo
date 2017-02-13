package com.zn.rxjavademo.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.zn.rxjavademo.R;

/**
 * Created by wang on 2016/4/11.
 */
public class SimpleTextHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.log)
    TextView log;

    public TextView getLog() {
        return log;
    }

    public SimpleTextHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
