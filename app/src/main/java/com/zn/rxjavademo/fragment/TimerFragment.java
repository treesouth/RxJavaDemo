package com.zn.rxjavademo.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.zn.rxjavademo.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;

/**
 * Created by zn on 17/2/13.
 * <p>
 * 定时器
 *
 * 操作符：timer
 */

public class TimerFragment extends RxFragment {

    @BindView(R.id.iv_welcome)
    ImageView iv_welcome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        starTimer();
    }


    private void starTimer() {
        Observable.timer(3000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Long>bindToLifecycle())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        // Glide.with(TimerFragment.this).load("http://static.zuchecdn
                        // .com/wap/newversion/images/20151225fanli_app.jpg").crossFade().into(iv_welcome);
                        iv_welcome.setVisibility(View.VISIBLE);
                        ObjectAnimator
                                .ofFloat(iv_welcome, "alpha", 0.0F, 1.0F)
                                .setDuration(500)
                                .start();
                    }
                });
    }
}
