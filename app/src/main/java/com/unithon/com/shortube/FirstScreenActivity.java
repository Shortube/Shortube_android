package com.unithon.com.shortube;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        ViewPager viewPager = (ViewPager)findViewById(R.id.firstScreenVpg);
        viewPager.setAdapter(new FirstPagePagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
    }
}
