package com.colud.jctl.adapters.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.colud.jctl.ui.fragment.NodeDataDetailsFragment;
import com.colud.jctl.ui.fragment.NodeManageFragment;

/**
 * Created by Jcty on 2017/4/5.
 */

public class NodeDetailsFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"节点管理", "节点数据详情"};

    public NodeDetailsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new NodeDataDetailsFragment();
        }
        return new NodeManageFragment();
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
