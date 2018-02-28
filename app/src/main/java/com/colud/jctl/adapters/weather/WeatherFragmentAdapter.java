package com.colud.jctl.adapters.weather;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jcty on 2017/3/15.
 */

public class WeatherFragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private int[] tabIconIds = {R.mipmap.jctl_launcher};
    private Context context;

    public WeatherFragmentAdapter(Context context, FragmentManager manager) {
        super(manager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public View getTabView(int position, ViewGroup parent) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.adapter_weather_img, parent, false);
        ImageView img = (ImageView) view.findViewById(R.id.tab_icon);
        img.setImageResource(tabIconIds[position]);
        return view;
    }
}
