package com.colud.jctl.adapters.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.colud.jctl.ui.fragment.BazaarDynaminFragment;
import com.colud.jctl.ui.fragment.NewDynamicFragment;


/**
 *
 * Created by Jcty on 2017/2/27.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"新闻动态", "市场动态"};

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new BazaarDynaminFragment();
        }
        return new NewDynamicFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
