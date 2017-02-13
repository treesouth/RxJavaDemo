package com.zn.rxjavademo.http.api;

import com.zn.rxjavademo.domain.BeautyResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GankApi {
    @GET("data/福利/{number}/{page}")
    Observable<BeautyResult> getBeauties(@Path("number") int number, @Path("page") int page);
}
