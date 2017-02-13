package com.zn.rxjavademo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.zn.rxjavademo.R;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * Created by zn on 17/2/13.
 */

public class PublishSubjectBottomFragment extends RxFragment {


    @BindView(R.id.tv_result)
    TextView tv_result;

    private  PublishSubject<String> publishSubject;

    public PublishSubjectBottomFragment() {
    }

    public PublishSubjectBottomFragment(PublishSubject<String> publishSubject) {
        this.publishSubject = publishSubject;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish_bottom, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        publishSubject.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                tv_result.setText(s);
            }
        });
    }
}
