package com.zn.rxjavademo.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.socks.library.KLog;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.zn.rxjavademo.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zn on 17/2/13.
 * 操作符：Debounce
 */

public class DebounceFragment extends RxFragment {
    @BindView(R.id.et_search)
    EditText et_search;

    @BindView(R.id.iv_x)
    ImageView iv_x;

    @BindView(R.id.lv_list)
    ListView lv_list;
    private ArrayAdapter<String> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debounce, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchKeyWordDemo();
    }

    /**
     * 搜索关键字提醒Demo
     */
    private void searchKeyWordDemo() {
        RxTextView.textChangeEvents(et_search).debounce(300, TimeUnit.MILLISECONDS)
                //debounce:每次文本更改后有300毫秒的缓冲时间，默认在computation调度器
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<TextViewTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        KLog.d("filter 线程::" + Thread.currentThread().getName());
                        boolean filter = !TextUtils.isEmpty(textViewTextChangeEvent.text());
                        if (!filter && mAdapter != null) {
                            //操作UI，这里必须在主线程完成
                            mAdapter.clear();
                            mAdapter.notifyDataSetChanged();
                        }
                        return filter;
                    }
                }).switchMap(new Func1<TextViewTextChangeEvent, Observable<List<String>>>() {
            @Override
            public Observable<List<String>> call(TextViewTextChangeEvent textViewTextChangeEvent) {
                return getKeyWordFormNet(textViewTextChangeEvent.text().toString().trim()).subscribeOn(Schedulers.io
                        ());//io线程
            }
        }).observeOn(AndroidSchedulers.mainThread())  //触发后回到Android主线程调度器
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        initPage(strings);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        KLog.e("异常：" + throwable.getMessage());
                    }
                });
    }


    private void initPage(List<String> keyWords) {
        KLog.d("data::" + keyWords.toString());
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_list, R.id.tv_text, keyWords);
            lv_list.setAdapter(mAdapter);
            lv_list.setOnItemClickListener(itemClick());
        } else {
            mAdapter.clear();
            mAdapter.addAll(keyWords);
            mAdapter.notifyDataSetChanged();
        }
    }

    private AdapterView.OnItemClickListener itemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DebounceFragment.this.getActivity(), "搜索:" + mAdapter.getItem(position), Toast
                        .LENGTH_SHORT).show();
            }
        };
    }


    /**
     * 模拟网路接口获取匹配到的关键字列表
     */
    private Observable<List<String>> getKeyWordFormNet(final String key) {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {

                KLog.d("线程::" + Thread.currentThread().getName());

                SystemClock.sleep(1000);
                //这里是网络请求操作...
                List<String> datas = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    datas.add("KeyWord:" + key + i);
                }
                subscriber.onNext(datas);
                subscriber.onCompleted();
            }
        });
    }

    @OnClick(R.id.iv_x)
    void clear() {
        et_search.setText("");
    }
}
