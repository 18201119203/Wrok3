package com.example.monizhoukao3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.monizhoukao3.R;
import com.example.monizhoukao3.activity.ShopActivity;
import com.example.monizhoukao3.adapter.ShopDataAdapter;
import com.example.monizhoukao3.bean.ShopBean;
import com.example.monizhoukao3.bean.ShopYiang;
import com.example.monizhoukao3.contract.ShopContract;
import com.example.monizhoukao3.presenter.ShopPresenter;
import com.gyf.barlibrary.ImmersionBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import org.greenrobot.eventbus.EventBus;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentSelect extends Fragment implements ShopContract.conView,ShopDataAdapter.onClickitem  {


    private Unbinder bind;
    @BindView(R.id.rv)
    XRecyclerView rv;
    @BindView(R.id.but_name)
    Button but_name;
    @BindView(R.id.et_name)
    EditText et_name;
    private int page=1;
    private String count="5";
    private ShopPresenter shopPresenter;
    private ShopDataAdapter shopDataAdapter;
    private String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main,container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bind = ButterKnife.bind(this,view);
        initData();

    }

    private void initData() {
        shopPresenter = new ShopPresenter(this);
        shopDataAdapter = new ShopDataAdapter(getActivity());
        shopDataAdapter.setOnclickitem(this);
        rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rv.setAdapter(shopDataAdapter);
        rv.setPullRefreshEnabled(true);
        rv.setLoadingMoreEnabled(true);
        //刷新加载
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                initShopdata();
                rv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                initShopdata();
                rv.loadMoreComplete();

            }
        });

    }


    @OnClick(R.id.but_name)
    public void but(View view){
        page=1;
        initShopdata();
    }

    //向p层传值
    private void initShopdata() {
        String s = et_name.getText().toString();
        if ("".equals(s)){
            return;
        }
        HashMap<String,String> params = new HashMap<>();
        params.put("keyword",s);
        params.put("page",page+"");
        params.put("count",count);
        shopPresenter.getshopData(params);
    }

    private void initShopYiang(String id){
        shopPresenter.getshopYiang(id);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        ImmersionBar.with(this).destroy();
        shopPresenter.uBindView();
    }

    @Override
    public void onSuccess(Object shopData) {
        if (shopData instanceof ShopBean){
            ShopBean shopBean = (ShopBean) shopData;
            if (shopBean!=null){
                if (page==1){
                    shopDataAdapter.setList(shopBean.result);
                }else{
                    shopDataAdapter.addList(shopBean.result);
                }

            }
        }else if (shopData instanceof ShopYiang){

            ShopYiang shopBean = (ShopYiang) shopData;
            EventBus.getDefault().postSticky(shopBean);
            startActivity(new Intent(getActivity(),ShopActivity.class));
        }

    }

    @Override
    public void Frailure(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }
    @Override
    public void getId(String id) {
        this.id = id;
        initShopYiang(id);
    }
}
