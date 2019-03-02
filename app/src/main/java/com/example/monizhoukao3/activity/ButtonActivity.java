package com.example.monizhoukao3.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.monizhoukao3.R;
import com.example.monizhoukao3.fragment.FragmentCardShop;
import com.example.monizhoukao3.fragment.FragmentOrder;
import com.example.monizhoukao3.fragment.FragmentSelect;
import com.gyf.barlibrary.ImmersionBar;

public class ButtonActivity extends AppCompatActivity {

    private ViewPager viewpage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_select:
                    viewpage.setCurrentItem(0);
                    return true;
                case R.id.navigation_cardshop:
                    viewpage.setCurrentItem(1);
                    return true;
                case R.id.navigation_order:
                    viewpage.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        getSupportActionBar().hide();
        //沉浸式
        ImmersionBar.with(this).init();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewpage = findViewById(R.id.viewpage);
        viewpage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new FragmentSelect();
                    case 1:
                        return new FragmentCardShop();
                    case 2:
                        return new FragmentOrder();

                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });


        viewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_select);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_cardshop);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_order);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

}
