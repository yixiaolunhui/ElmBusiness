package com.dalong.elmbusiness.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * tab
 * Created by dalong on 2016/12/27.
 */

public class TabFragmentAdapter extends FragmentPagerAdapter {

    //fragment列表
    private List<Fragment> mFragments;
    //tab名的列表
    private List<String> mTitles;

    public TabFragmentAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {

        return mTitles.get(position % mTitles.size());
    }
}
