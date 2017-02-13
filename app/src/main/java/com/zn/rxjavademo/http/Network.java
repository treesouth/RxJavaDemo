// (c)2016 Flipboard Inc, All Rights Reserved.

package com.zn.rxjavademo.http;

import com.socks.library.KLog;
import com.zn.rxjavademo.http.api.GankApi;
import com.zn.rxjavademo.http.api.TokenApi;
import com.zn.rxjavademo.http.api.ZhuangbiApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Network {
    private static ZhuangbiApi zhuangbiApi;
    private static GankApi gankApi;
    private static TokenApi fakeApi;

    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static ZhuangbiApi getZhuangbiApi() {
        if (zhuangbiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(buildOkHttpClient())
                    .baseUrl("http://zhuangbi.info/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            zhuangbiApi = retrofit.create(ZhuangbiApi.class);
        }
        return zhuangbiApi;
    }

    public static GankApi getGankApi() {
        if (gankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(buildOkHttpClient())
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }

    public static TokenApi getTokenApi() {
        if (fakeApi == null) {
            fakeApi = new TokenApi();
        }
        return fakeApi;
    }

    private static OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request request = chain.request();
                                KLog.e("requst = " + request.body());
                                return chain.proceed(request);
                            }
                        }).build();
    }
}
