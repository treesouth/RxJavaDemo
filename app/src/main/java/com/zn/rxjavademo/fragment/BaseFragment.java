package com.zn.rxjavademo.fragment;

import android.app.AlertDialog;
import android.support.annotation.Nullable;
import butterknife.OnClick;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.zn.rxjavademo.R;
import rx.Subscription;

/**
 * Created by zn on 17/2/13.
 */

public class BaseFragment extends RxFragment {

    protected Subscription subscription;

    @Nullable
    @OnClick(R.id.tipBt)
    void tip() {
        if (-1 != getDialogRes() && -1 != getTitleRes()) {
            new AlertDialog.Builder(getActivity())
                    .setTitle(getTitleRes())
                    .setView(getActivity().getLayoutInflater().inflate(getDialogRes(), null))
                    .show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    protected int getDialogRes() {
        return -1;
    }

    protected int getTitleRes() {
        return -1;
    }
}
