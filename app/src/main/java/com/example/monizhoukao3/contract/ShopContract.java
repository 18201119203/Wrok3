package com.example.monizhoukao3.contract;

import com.example.monizhoukao3.bean.ShopBean;
import com.example.monizhoukao3.bean.ShopYiang;
import com.example.monizhoukao3.model.ShopModel;

import java.util.HashMap;

public interface ShopContract {


     abstract class conPresenter{
        public abstract void getshopData(HashMap<String,String> params);
        public abstract void getshopYiang(String id);
        public abstract void getCardData();

    }

    interface conModel{
         void getshopData(HashMap<String, String> params, ShopModel.getResponceDataCallback callback);
         void getshopYiang(String id, ShopModel.getResponceDataCallback callback);
         void getCardData(ShopModel.getResponceDataCallback callback);

    }
    interface conView{
         void onSuccess(Object shopData);
         void Frailure(String msg);
    }


}
