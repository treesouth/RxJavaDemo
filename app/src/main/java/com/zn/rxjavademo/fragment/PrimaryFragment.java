package com.zn.rxjavademo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import com.socks.library.KLog;
import com.zn.rxjavademo.R;
import com.zn.rxjavademo.adapter.PrimaryAdapter;
import com.zn.rxjavademo.domain.ImageInfoBean;
import com.zn.rxjavademo.http.Network;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.List;

public class PrimaryFragment extends BaseFragment {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.gridRv)
    RecyclerView gridRv;

    PrimaryAdapter adapter;
    Observer<List<ImageInfoBean>> observer;
    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        if (null == adapter) {
            adapter = new PrimaryAdapter();
        }
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);

        search(((AppCompatRadioButton)mRadioGroup.getChildAt(0)).getText().toString());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_primary, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnCheckedChanged({R.id.searchRb1, R.id.searchRb2, R.id.searchRb3, R.id.searchRb4})
    void onTagChecked(RadioButton searchRb, boolean checked) {
        if (checked) {
            unsubscribe();
            adapter.setImages(null);
            swipeRefreshLayout.setRefreshing(true);
            search(searchRb.getText().toString());
        }
    }

    private void search(String key) {
        KLog.e();
        subscription = Network.getZhuangbiApi()
                .search(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observer<? super List<ImageInfoBean>> getObserver() {

        if (null == observer) {
            observer = new Observer<List<ImageInfoBean>>() {
                @Override
                public void onCompleted() {
                    KLog.e();
                }

                @Override
                public void onError(Throwable e) {
                    KLog.e();
                    e.printStackTrace();
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(List<ImageInfoBean> images) {
                    KLog.e();
                    swipeRefreshLayout.setRefreshing(false);
                    adapter.setImages(images);
                }
            };
        }

        return observer;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_elementary;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_elementary;
    }

}
