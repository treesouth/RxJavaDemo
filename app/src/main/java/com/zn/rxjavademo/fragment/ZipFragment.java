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
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zn on 17/2/13.
 *
 * 操作符：zip
 *
 * Zip操作符返回一个Observable，它使用这个函数按顺序结合两个或多个Observables发射的数据项，
 * 然后它发射这个函数返回的结果。它按照严格的顺序应用这个函数。
 * 它只发射与发射数据项最少的那个Observable一样多的数据。
 */

public class ZipFragment extends RxFragment {

    @BindView(R.id.view_load)
    ProgressWheel loadView;

    @BindView(R.id.lv_list)
    ListView lv_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zip, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getContactData();
    }

    private void getContactData() {
        Observable.zip(
                queryContactsFromLocation(),
                queryContactsForNet(),
                new Func2<List<Contacter>, List<Contacter>, List<Contacter>>() {
                    @Override
                    public List<Contacter> call(List<Contacter> contacters, List<Contacter> contacters2) {
                        contacters.addAll(contacters2);
                        return contacters;
                    }
                }
        ).compose(this.<List<Contacter>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Contacter>>() {
                    @Override
                    public void call(List<Contacter> contacters) {
                        initPage(contacters);
                    }
                });
    }

    private void initPage(List<Contacter> contacters) {
        loadView.setVisibility(View.GONE);
        KLog.d(contacters.toString());
        lv_list.setAdapter(new ArrayAdapter<Contacter>(getActivity(), R.layout.item_list, R.id.tv_text, contacters));
    }


    /**
     * 模拟网络联系人列表
     */
    private Observable<List<Contacter>> queryContactsForNet() {
        return Observable.create(new Observable.OnSubscribe<List<Contacter>>() {
            @Override
            public void call(Subscriber<? super List<Contacter>> subscriber) {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ArrayList<Contacter> contacters = new ArrayList<>();
                contacters.add(new Contacter("net:Zeus"));
                contacters.add(new Contacter("net:Athena"));
                contacters.add(new Contacter("net:Prometheus"));
                subscriber.onNext(contacters);
                subscriber.onCompleted();
            }
        });
    }

    /**
     * 模拟手机本地联系人查询
     */
    private Observable<List<Contacter>> queryContactsFromLocation() {
        return Observable.create(new Observable.OnSubscribe<List<Contacter>>() {
            @Override
            public void call(Subscriber<? super List<Contacter>> subscriber) {

                ArrayList<Contacter> contacters = new ArrayList<>();
                contacters.add(new Contacter("location:张三"));
                contacters.add(new Contacter("location:李四"));
                contacters.add(new Contacter("location:王五"));
                subscriber.onNext(contacters);
                subscriber.onCompleted();
            }
        });
    }
}
