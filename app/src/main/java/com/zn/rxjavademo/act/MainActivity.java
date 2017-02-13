package com.zn.rxjavademo.act;

import android.os.Bundle;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import com.zn.rxjavademo.R;
import com.zn.rxjavademo.fragment.MainFragment;

public class MainActivity extends RxFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, new MainFragment(), MainFragment.class.getName())
                .commit();
    }
}
