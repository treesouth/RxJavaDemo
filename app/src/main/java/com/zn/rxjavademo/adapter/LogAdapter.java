package com.zn.rxjavademo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zn.rxjavademo.R;
import com.zn.rxjavademo.holder.SimpleTextHolder;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<SimpleTextHolder> {

    private List<String> mLogs;

    public LogAdapter(List<String> logs) {
        this.mLogs = logs;
    }

    @Override
    public SimpleTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_log, parent, false);
        return new SimpleTextHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleTextHolder holder, int position) {

        holder.getLog().setText(mLogs.get(position));
    }

    @Override
    public int getItemCount() {
        return null == mLogs ? 0 : mLogs.size();
    }


}
