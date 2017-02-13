package com.zn.rxjavademo.http.api;

import com.zn.rxjavademo.domain.ImageInfoBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import java.util.List;

/**
 * Created by wang on 2016/4/5.
 */
public interface ImageApi {
    @GET("search")
    Observable<List<ImageInfoBean>> search(@Query("q") String query);
}
