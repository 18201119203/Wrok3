package com.example.monizhoukao3.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.lib_netwrok.utils.SpUtils;
import com.example.monizhoukao3.CartCallback.NotifyNum;
import com.example.monizhoukao3.R;
import com.example.monizhoukao3.adapter.ShopCartAdapter;
import com.example.monizhoukao3.api.Api;
import com.example.monizhoukao3.bean.ShopCartBean;
import com.example.monizhoukao3.contract.ShopContract;
import com.example.monizhoukao3.presenter.ShopPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentCardShop extends Fragment implements ShopContract.conView,NotifyNum {

    @BindView(R.id.rv)
    XRecyclerView rv;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    private Unbinder bind;
    private ShopCartAdapter shopCartAdapter;
    private ShopCartBean shopCartBean;
    private ShopPresenter shopPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragmentshop,container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
        initpresenter();
    }

    @Override
    public void onSuccess(Object shopData) {

        if (shopData instanceof ShopCartBean){
            shopCartBean = (ShopCartBean) shopData;

            for (ShopCartBean.ResultBean resultBean : shopCartBean.result) {
                resultBean.ischelick = false;
            }
            shopCartAdapter = new ShopCartAdapter(getActivity(), shopCartBean.result, this);
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv.setPullRefreshEnabled(true);
            rv.setLoadingMoreEnabled(false);
            rv.setAdapter(shopCartAdapter);
            rv.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    initpresenter();
                    rv.refreshComplete();
                }

                @Override
                public void onLoadMore() {
                    rv.loadMoreComplete();
                }
            });
        }
    }


    protected void initpresenter() {
        shopPresenter = new ShopPresenter(this);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopCartBean!=null){
                    if (checkbox.isChecked()){
                        for (ShopCartBean.ResultBean resultBean : shopCartBean.result) {
                            resultBean.ischelick=true;
                        }
                    }else{
                        for (ShopCartBean.ResultBean resultBean : shopCartBean.result) {
                            resultBean.ischelick=false;
                        }
                    }
                    shopCartAdapter.notifyDataSetChanged();
                    zPrice();
                }
            }
        });
        shopPresenter.getCardData();
    }

    @Override
    public void Frailure(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }

    @Override
    public void notifynum() {
        zPrice();
    }

    @Override
    public void isCheck(boolean che) {
        checkbox.setChecked(che);
    }

    private void zPrice(){
        double price=0;
        for (ShopCartBean.ResultBean resultBean : shopCartBean.result) {
            if (resultBean.ischelick){
                price += resultBean.num * resultBean.price;
            }
        }
        checkbox.setText("¥:" + price);
    }
}
