package com.example.monizhoukao3.api;

import com.example.monizhoukao3.bean.ShopBean;
import com.example.monizhoukao3.bean.ShopCartBean;
import com.example.monizhoukao3.bean.ShopYiang;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {

    @GET
    Observable<ShopBean> getShopData(@Url String url, @QueryMap HashMap<String,String> params);
    @GET
    Observable<ShopYiang> getShopXiangData(@Url String url, @Query("commodityId") String id);
    @GET
    Observable<ShopCartBean> getCardData(@Url String url);


}
