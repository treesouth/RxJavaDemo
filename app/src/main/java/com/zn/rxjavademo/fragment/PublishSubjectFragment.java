package com.zn.rxjavademo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.zn.rxjavademo.R;
import rx.subjects.PublishSubject;

/**
 * Created by zn on 17/2/13.
 *
 * 可连接的Subject
 * 与普通的Subject不同，在订阅时并不立即触发订阅事件，
 * 而是允许我们在任意时刻手动调用onNext(),onError(),onCompleted来触发事件
 *
 * 一定要用Subject.create()的方式创建并使用，不要用just(T)、from(T)、create(T)创建，否则会导致失效
 */

public class PublishSubjectFragment extends RxFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PublishSubject<String> publishSubject = PublishSubject.create();
        PublishSubjectTopFragment topFragment = new PublishSubjectTopFragment(publishSubject);
        PublishSubjectBottomFragment bottom_Fragment = new PublishSubjectBottomFragment(publishSubject);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_top, topFragment, "top")
                .replace(R.id.fl_bottom, bottom_Fragment, "bottom")
                .commit();
    }

}
