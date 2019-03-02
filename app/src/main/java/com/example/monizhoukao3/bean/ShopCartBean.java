package com.example.monizhoukao3.bean;

import java.util.List;

public class ShopCartBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {

        public int num;
        public boolean ischelick;
        public int commodityId;
        public String commodityName;
        public int count;
        public String pic;
        public double price;

    }
}
