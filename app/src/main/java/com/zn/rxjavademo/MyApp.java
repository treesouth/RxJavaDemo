package com.zn.rxjavademo;

import android.app.Application;
import com.socks.library.KLog;

/**
 * Created by zn on 17/2/13.
 */

public class MyApp extends Application {
    private static MyApp mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        KLog.init(true, "Zn");
    }

    public static Application getApplaction(){
        return mApp;
    }
}
