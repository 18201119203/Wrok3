package com.example.monizhoukao3.bean;

import java.util.List;

public class ShopBean {

    public String message;
    public String status;
    public List<shopData> result;
    public class shopData{
        public String commodityName;
        public String commodityId;
        public String masterPic;
        public String price;

    }


}
