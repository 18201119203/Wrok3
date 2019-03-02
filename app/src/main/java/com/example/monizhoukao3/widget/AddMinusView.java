package com.example.monizhoukao3.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monizhoukao3.R;


public class AddMinusView extends LinearLayout {
    public AddMinusView(Context context) {
        this(context,null);
    }

    public AddMinusView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddMinusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.add_minus_layout, this, true);
        initView(view);
    }

    private TextView minus;
    private EditText et_num;
    private TextView add;
    private int num=1;

    private void initView(View view) {
        minus = view.findViewById(R.id.minus);
        et_num = view.findViewById(R.id.et_num);
        add = view.findViewById(R.id.add);

        minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num<=1){
                    Toast.makeText(getContext(),"不能再减了",Toast.LENGTH_SHORT).show();
                }else{
                    num--;
                    et_num.setText(num+"");
                    if (getcallback!=null){
                        getcallback.getnum(num);
                    }
                }
            }
        });

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                et_num.setText(num+"");
                if (getcallback!=null){
                    getcallback.getnum(num);
                }
            }
        });

        et_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length()==0){
                    return;
                }
                num = Integer.parseInt(et_num.getText().toString());
                if (num<1){
                    Toast.makeText(getContext(),"不小于1",Toast.LENGTH_SHORT).show();
                    num=1;
                }
                if (getcallback!=null){
                    getcallback.getnum(num);
                }
            }
        });

    }

    public void setnum(String num){
        et_num.setText(num);
    }
    private getCallback getcallback;

    public void onClickbut(getCallback getcallback){
        this.getcallback = getcallback;
    }

    public interface getCallback{
        void getnum(int i);
    }



}
