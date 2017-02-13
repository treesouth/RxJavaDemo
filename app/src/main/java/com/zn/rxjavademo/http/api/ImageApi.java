package com.zn.rxjavademo.http.api;

import com.zn.rxjavademo.domain.ImageInfoBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import java.util.List;

public interface ImageApi {
    @GET("search")
    Observable<List<ImageInfoBean>> search(@Query("q") String query);
}
