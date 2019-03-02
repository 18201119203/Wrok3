package com.example.monizhoukao3.presenter;


import com.example.monizhoukao3.contract.ShopContract;
import com.example.monizhoukao3.model.ShopModel;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class ShopPresenter extends ShopContract.conPresenter{

    private ShopContract.conView view;
    private WeakReference<ShopContract.conView> wr;
    private ShopModel shopModel;

    public ShopPresenter(ShopContract.conView view) {
        wr =  new WeakReference<>(view);
        this.view = wr.get();
        shopModel = new ShopModel();

    }
    @Override
    public void getshopData(HashMap<String, String> params) {

        shopModel.getshopData(params, new ShopModel.getResponceDataCallback() {
            @Override
            public void Success(Object res) {
                if (view!=null){
                    view.onSuccess(res);
                }
            }
            @Override
            public void Frailure(String msg) {
                if (view!=null){
                    view.Frailure(msg);
                }
            }
        });
    }

    @Override
    public void getshopYiang(String id) {

        shopModel.getshopYiang(id, new ShopModel.getResponceDataCallback() {
            @Override
            public void Success(Object res) {
                if (view!=null){
                    view.onSuccess(res);
                }
            }
            @Override
            public void Frailure(String msg) {
                if (view!=null){
                    view.Frailure(msg);
                }
            }
        });


    }

    @Override
    public void getCardData() {
        shopModel.getCardData( new ShopModel.getResponceDataCallback() {
            @Override
            public void Success(Object res) {
                if (view!=null){
                    view.onSuccess(res);
                }
            }
            @Override
            public void Frailure(String msg) {
                if (view!=null){
                    view.Frailure(msg);
                }
            }
        });
    }


    public void uBindView(){
        if (view!=null){
            wr.clear();
            wr=null;
            view=null;
        }
    }


}
