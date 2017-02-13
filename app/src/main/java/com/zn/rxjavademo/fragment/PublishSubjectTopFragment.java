package com.zn.rxjavademo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.zn.rxjavademo.R;
import rx.subjects.PublishSubject;

/**
 * Created by zn on 17/2/13.
 */

public class PublishSubjectTopFragment extends RxFragment {


    @BindView(R.id.et_input)
    EditText et_input;

    private PublishSubject<String> publishSubject;

    public PublishSubjectTopFragment() {
    }

    public PublishSubjectTopFragment(PublishSubject<String> publishSubject) {
        this.publishSubject = publishSubject;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish_top, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.btn_send)
    void sendToBottom() {
        String result = et_input.getText().toString().trim();
        publishSubject.onNext(result);
    }

}
