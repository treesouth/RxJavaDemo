package com.zn.rxjavademo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.socks.library.KLog;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.zn.rxjavademo.ProgressWheel;
import com.zn.rxjavademo.R;
import com.zn.rxjavademo.model.Contacter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zn on 17/2/13.
 * 操作符：Concat
 */

public class ConcatFragment extends RxFragment {
    private static final String LOCATION = "location:";
    private static final String NET = "net:";

    @BindView(R.id.view_load)
    ProgressWheel loadView;

    @BindView(R.id.lv_list)
    ListView lv_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_concat, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        concatDemo();
    }

    private void concatDemo() {
        Observable.concat(
                getDataFromLocation(),
                getDataFromNet()
        ).compose(this.<List<Contacter>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Contacter>>() {
                    @Override
                    public void call(List<Contacter> contacters) {
                        initPage(contacters);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        KLog.e(throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        KLog.d("onCompleted()");
                    }
                });
    }

    private void initPage(List<Contacter> contacters) {
        loadView.setVisibility(View.GONE);
        KLog.d(contacters.toString());
        lv_list.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.item_list, R.id.tv_text, contacters));
    }

    private Observable<List<Contacter>> getDataFromNet() {
        return Observable.create(new Observable.OnSubscribe<List<Contacter>>() {
            @Override
            public void call(Subscriber<? super List<Contacter>> subscriber) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ArrayList<Contacter> contacters = new ArrayList<>();
                contacters.add(new Contacter(NET + "Zeus"));
                contacters.add(new Contacter(NET + "Athena"));
                contacters.add(new Contacter(NET + "Prometheus"));
                // subscriber.onError(new Throwable("模拟出错"));
                subscriber.onNext(contacters);
                subscriber.onCompleted();
            }
        });
    }


    private Observable<List<Contacter>> getDataFromLocation() {
        return Observable.create(new Observable.OnSubscribe<List<Contacter>>() {
            @Override
            public void call(Subscriber<? super List<Contacter>> subscriber) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<Contacter> datas = new ArrayList<>();
                datas.add(new Contacter(LOCATION + "张三"));
                datas.add(new Contacter(LOCATION + "李四"));
                datas.add(new Contacter(LOCATION + "王五"));

                subscriber.onNext(datas);
                subscriber.onCompleted();
            }
        });
    }
}
