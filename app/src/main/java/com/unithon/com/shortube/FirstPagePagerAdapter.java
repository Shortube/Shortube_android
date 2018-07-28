package com.unithon.com.shortube;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FirstPagePagerAdapter extends FragmentStatePagerAdapter {


    public FirstPagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) { // 화면에 보여줄 fragment를 반환

        switch (i){
            case 0:
                FirstScreenFragment firstScreenFragment = new FirstScreenFragment();
                return firstScreenFragment;
            case 1:
                SecondScreenFragment secondScreenFragment = new SecondScreenFragment();
                return  secondScreenFragment;
            case 2:
                ThirdScreenFragment thirdScreenFragment = new ThirdScreenFragment();
                return thirdScreenFragment;

        }
        return null;
    }

    @Override
    public int getCount() { // 사용되는 fragment가 몇개인지를 반환
        return 3;
    }
}
