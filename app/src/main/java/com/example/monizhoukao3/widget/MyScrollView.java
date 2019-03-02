package com.example.monizhoukao3.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

    public MyScrollView(Context context) {
        this(context,null);


    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (scrollviewListener!=null){
            scrollviewListener.OnScrollViewChage(this,l,t,oldl,oldt);
        }

    }

    public interface ScrollviewListener{
        void OnScrollViewChage(MyScrollView scrollView, int l, int t, int oldl, int oldt);

    }

    public ScrollviewListener scrollviewListener;

    public void setScrollviewListener(ScrollviewListener scrollviewListener){
        this.scrollviewListener = scrollviewListener;
    }



}
