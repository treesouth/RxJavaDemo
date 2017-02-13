package com.zn.rxjavademo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.zn.rxjavademo.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import java.util.List;

/**
 * Created by zn on 17/2/13.
 *
 * 操作符：Buffer
 */

public class BufferFragment extends RxFragment {
    @BindView(R.id.btn_buffer_count)
    Button btn_buffer_count;

    @BindView(R.id.btn_buffer_count_skip)
    Button btn_buffer_count_skip;

    @BindView(R.id.et_input)
    EditText et_input;

    @BindView(R.id.tv_output)
    TextView tv_output;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buffer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        demo_buffer_count();
    }

    /**
     * 每统计到3次点击事件，发送一次数据
     */
    private void demo_buffer_count() {
        RxView.clicks(btn_buffer_count).buffer(3).compose(this.<List<Void>>bindToLifecycle()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Void>>() {
            @Override
            public void call(List<Void> voids) {
                Toast.makeText(BufferFragment.this.getActivity(), R.string.des_demo_buffer_count, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *
     * 操作符Observable.compose()
     *
     * Transformer实际上就是一个Func1<Observable<T>, Observable<R>>，
     * 换言之就是：可以通过它将一种类型的Observable转换成另一种类型的Observable，和调用一系列的内联操作符是一模一样的。
     */
    @OnClick(R.id.btn_buffer_count_skip)
    void demo_buffer_count_skip() {
        tv_output.setText("");
        char[] cs = et_input.getText().toString().trim().toCharArray();
        Character[] chs = new Character[cs.length];
        for (int i = 0; i < chs.length; i++) {
            chs[i] = cs[i];
        }

        Observable.from(chs).buffer(2, 3).compose(this.<List<Character>>bindToLifecycle()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Character>>() {

            @Override
            public void call(List<Character> characters) {
                tv_output.setText(tv_output.getText() + characters.toString());
            }
        });

    }
}
