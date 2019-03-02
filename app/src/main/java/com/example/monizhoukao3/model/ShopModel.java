package com.example.monizhoukao3.model;

import android.annotation.SuppressLint;

import com.example.lib_netwrok.network.RetrofitUtils;
import com.example.lib_netwrok.network.RxUtils;
import com.example.monizhoukao3.api.Api;
import com.example.monizhoukao3.api.ApiService;
import com.example.monizhoukao3.bean.ShopBean;
import com.example.monizhoukao3.bean.ShopCartBean;
import com.example.monizhoukao3.bean.ShopYiang;
import com.example.monizhoukao3.contract.ShopContract;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

public class ShopModel implements ShopContract.conModel {


    @SuppressLint("CheckResult")
    @Override
    public void getshopData(HashMap<String, String> params, final getResponceDataCallback callback) {
        RetrofitUtils.getInstance().createService(ApiService.class)
                .getShopData(Api.SHOPDATA,params)
                .compose(RxUtils.<ShopBean>schdulers())
                .subscribe(new Consumer<ShopBean>() {
                    @Override
                    public void accept(ShopBean shopBean) throws Exception {

                        if (callback!=null){
                            callback.Success(shopBean);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (callback!=null){
                            callback.Frailure("网络不稳定,请稍后重试!");
                        }

                    }
                });

    }

    @SuppressLint("CheckResult")
    @Override
    public void getshopYiang(String id, final getResponceDataCallback callback) {

        RetrofitUtils.getInstance().createService(ApiService.class)
                .getShopXiangData(Api.SHOPXIANG,id)
                .compose(RxUtils.<ShopYiang>schdulers())
                .subscribe(new Consumer<ShopYiang>() {
                    @Override
                    public void accept(ShopYiang shopYiang) throws Exception {

                        if (callback!=null){
                            callback.Success(shopYiang);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Frailure("网络不稳定,请稍后重试!");
                        }
                    }
                });

    }

    @SuppressLint("CheckResult")
    @Override
    public void getCardData(final getResponceDataCallback callback) {
        RetrofitUtils.getInstance().createService(ApiService.class)
                .getCardData(Api.CARDSHOP)
                .compose(RxUtils.<ShopCartBean>schdulers())
                .subscribe(new Consumer<ShopCartBean>() {
                    @Override
                    public void accept(ShopCartBean shopCartBean) throws Exception {
                        if (callback!=null){
                            callback.Success(shopCartBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Frailure("网络不稳定,请稍后重试!");
                        }
                    }
                });
    }

    public interface getResponceDataCallback{
        void Success(Object res);
        void Frailure(String msg);
    }

}
