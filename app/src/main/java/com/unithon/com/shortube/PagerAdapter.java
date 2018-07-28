package com.unithon.com.shortube;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int i) { // 화면에 보여줄 fragment를 반환

        switch (i){
            case 0:
                LeftFragment leftFragment = new LeftFragment();
                return leftFragment;
            case 1:
                RightFragment centerFragment = new RightFragment();
                return centerFragment;

        }
        return null;
    }

    @Override
    public int getCount() { // 사용되는 fragment가 몇개인지를 반환
        return numOfTabs;
    }
}
