package com.example.monizhoukao3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.monizhoukao3.CartCallback.NotifyNum;
import com.example.monizhoukao3.R;
import com.example.monizhoukao3.bean.ShopCartBean;
import com.example.monizhoukao3.widget.AddMinusView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;


public class ShopCartAdapter extends XRecyclerView.Adapter<ShopCartAdapter.ViewHolder> {

    private List<ShopCartBean.ResultBean> list;
    private Context context;
    private NotifyNum notifyNum;
    public ShopCartAdapter(Context context, List<ShopCartBean.ResultBean> list, NotifyNum notifyNum) {
        this.notifyNum = notifyNum;
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {

        holder.checkbox.setChecked(list.get(i).ischelick);
        holder.price.setText(list.get(i).price+"");
        holder.title.setText(list.get(i).commodityName);
        Glide.with(context).load(list.get(i).pic).into(holder.iv_product);
        holder.addminusView.setnum(list.get(i).count+"");
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.get(i).ischelick = holder.checkbox.isChecked();
                notifyDataSetChanged();
                boolean che=true;
                for (ShopCartBean.ResultBean resultBean : list) {
                    if (!resultBean.ischelick){
                        che = false;
                    }
                    if (notifyNum!=null){
                        notifyNum.isCheck(che);
                    }

                }
                if (notifyNum!=null){
                    notifyNum.notifynum();
                }

            }
        });
        holder.addminusView.onClickbut(new AddMinusView.getCallback() {
            @Override
            public void getnum(int j) {
                list.get(i).num = j;
                if (notifyNum!=null){
                    notifyNum.notifynum();
                }
            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(i);
                notifyDataSetChanged();
                holder.sm.quickClose();

            }
        });



    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkbox;
        private ImageView iv_product;
        private TextView title;
        private TextView price;
        private AddMinusView addminusView;
        private Button del;
        private SwipeMenuLayout sm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
            iv_product = itemView.findViewById(R.id.iv_product);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            addminusView = itemView.findViewById(R.id.addminusView);
            del = itemView.findViewById(R.id.del);
            sm = itemView.findViewById(R.id.sm);
        }
    }
}
