package com.colud.jctl.adapters.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.colud.jctl.ui.fragment.CameraFragment;
import com.colud.jctl.ui.fragment.NodeFragment;

/**
 * Created by Jcty on 2017/4/5.
 */

public class NodeFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"节点", "摄像头"};

    public NodeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new CameraFragment();
        }
        return new NodeFragment();
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
