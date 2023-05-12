package com.example.serviciicutab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout= findViewById(R.id.baraTab);
        TabItem tabDet= findViewById(R.id.detailing);
        TabItem tabFar= findViewById(R.id.faruri);
        TabItem tabCar= findViewById(R.id.caroserie);
        TabItem tabMot= findViewById(R.id.motor);
        TabItem tabCer= findViewById(R.id.ceramica);
        ViewPager viewPager= findViewById(R.id.viewpager);

        PagerAdapter pagerAdapter= new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount()
        );

        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}