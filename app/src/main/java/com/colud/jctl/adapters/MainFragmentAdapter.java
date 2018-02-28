package com.colud.jctl.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Author: SherlockShi
 * Date:   2016-11-01 17:38
 * Description:
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private List<String> mTabList;

    private Context mContext;

    private List<Fragment> mFragments;


    public MainFragmentAdapter(FragmentManager fm, List<String> tabList, Context context, List<Fragment> fragments) {
        super(fm);
        mTabList = tabList;
        mContext = context;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }


}
