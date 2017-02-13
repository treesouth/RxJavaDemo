package com.zn.rxjavademo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.socks.library.KLog;
import com.zn.rxjavademo.R;

/**
 * Created by zn on 17/2/13.
 */

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_buffer)
    void btn_buffer() {
        open(new BufferFragment());
    }

    @OnClick(R.id.btn_checkbox_state_update)
    void btn_checkbox_state_update() {
        open(new CheckBoxUpdateFragment());
    }

    @OnClick(R.id.btn_concat)
    void btn_concat() {
        open(new ConcatFragment());
    }

    @OnClick(R.id.btn_text_change)
    void btn_text_change() {
        open(new DebounceFragment());
    }

    @OnClick(R.id.btn_loop)
    void btn_loop() {
        open(new LoopFragment());
    }

    @OnClick(R.id.btn_not_more_click)
    void btn_not_more_click() {
        open(new NotMoreClickFragment());
    }

    @OnClick(R.id.btn_timer)
    void btn_timer() {
        open(new TimerFragment());
    }

    @OnClick(R.id.btn_zip)
    void btn_zip() {
        open(new ZipFragment());
    }

    @OnClick(R.id.btn_publish)
    void btn_publish() {
        open(new PublishSubjectFragment());
    }

    @OnClick(R.id.btn_reuse_subscriber)
    void btn_reuse_subscriber() {
        open(new ReuseSubscriberFragment());
    }

    @OnClick(R.id.btn_combineLatest)
    void btn_combineLatest() {
        open(new CombineLatestFragment());
    }

    @OnClick(R.id.btn_token)
    void btn_token() {
        open(new TokenFragment());
    }

    @OnClick(R.id.btn_primary)
    void btn_primary() {
        open(new PrimaryFragment());
    }

    /**
     * 开启新的Fragment
     */
    private void open(Fragment fragment) {
        final String tag = fragment.getClass().toString();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.main_content, fragment, tag)
                .commit();
    }

}
