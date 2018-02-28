package com.colud.jctl.ui.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.colud.jctl.adapters.fragment.NewDynamicAdapter;
import com.colud.jctl.base.BaseFragment;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.bean.SplashBnBean;
import com.colud.jctl.counst.Constants;
import com.colud.jctl.counst.Counts;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.ui.activity.NewDatailActivity;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.colud.jctl.counst.Counts.BROADCAST_NEWS;


/**
 * 新闻动态页
 * Created by Jcty on 2017/3/15.
 */

public class NewDynamicFragment extends BaseFragment  {

    @BindView(R.id.new_fragment_layout)
    RecyclerView rv;

    private LinearLayoutManager layoutManager;

    private  NewDynamicAdapter mAdapter;



    private List<SplashBnBean.NewsBean> mListInfo = new ArrayList<>();

    private List<SplashBnBean.NewsBean> showList = new ArrayList<>();

    private List<SplashBnBean.NewsBean>newList;

    private NewsReceiver mReceiver;

    public static NewDynamicFragment getInstance(Object pageName) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.ARGS, (Serializable) pageName);
        NewDynamicFragment pageFragment = new NewDynamicFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_newdynamic;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReceiver= new NewsReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_NEWS);
        BroadCastManager.getInstance().registerReceiver(getActivity(),mReceiver,filter);
    }

    @Override
    public void initViews() {

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        mAdapter = new NewDynamicAdapter(getActivity(), null, true);
        rv.setAdapter(mAdapter);

    }

    @Override
    public void addListener() {
        mAdapter.setOnItemClickListener(new OnItemClickListener<SplashBnBean.NewsBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, SplashBnBean.NewsBean data, int position) {
                //				Intent intent=new Intent(getActivity(), NewInfoDetailActivity.class);
                //				intent.putExtra("url",data.getUrl());
                //				startActivity(intent);
                Intent intent = new Intent(getActivity(), NewDatailActivity.class);
                intent.putExtra("infoBean", data);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {


        //        List<SplashBnBean.NewsBean> newList = (List<SplashBnBean.NewsBean>) JctlApplication.getCache().getAsObject(KeyConstants.KAY_NEWINFO);
        //        if (newList != null && newList.size() > 0) {
        //            mListInfo.clear();
        //            if (newList.size() >= 2) {
        //                showList = newList.subList(0, 2);
        //                //				mAdapter.setNewData(showList);
        //            } else {
        //                showList = newList;
        //            }
        //            for (SplashBnBean.NewsBean dataInfo : showList) {
        //                mListInfo.add(dataInfo);
        //            }
        //        }
    }

    @Override
    protected BasePesenter onCreatePresenter() {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setNewsData(List<SplashBnBean.NewsBean> newList){
        //        List<SplashBnBean.NewsBean> newList = mMainActivity.getNewsData();
        if (newList != null && newList.size() > 0) {
            mListInfo.clear();
            if (newList.size() >= 2) {
                showList = newList.subList(0, 2);
                if (mAdapter!=null)
                {
                    mAdapter.setNewData(showList);
                }
            } else {
                showList = newList;
            }
            for (SplashBnBean.NewsBean dataInfo : showList) {
                mListInfo.add(dataInfo);
            }
        }
    }

    public void setData(List<SplashBnBean.NewsBean> data) {
        if (mAdapter!=null && data!=null)
        {

        }
    }
    //    @Override
    //    public void onAttachFragment(Fragment childFragment) {
    //        mIMutualListener = (IMutualListener)childFragment;
    //        super.onAttachFragment(childFragment);
    //    }
    //
    //    @Override
    //    public void actActivity(int type,Object str)
    //    {
    //
    //    }
    //
    //    @Override
    //    public void fragmentData(int type,Object str,Fragment fragment) {
    //        switch (type)
    //        {
    //            case TYPE_BANDSNEWS:
    //                if (str!= null ) {
    //                    SplashBnBean sp =(SplashBnBean) str;
    //                    if (sp.getNews().size() > 0) {
    //                        if (sp.getNews().size() >= 2) {
    //                            showList = sp.getNews().subList(0, 2);
    //                            mAdapter.setNewData(showList);
    //                        } else {
    //                            showList = sp.getNews();
    //                        }
    //                        for (SplashBnBean.NewsBean dataInfo : showList) {
    //                            mListInfo.add(dataInfo);
    //                        }
    //                    }
    //                }
    //                break;
    //            default:
    //        }
    //
    //    }


    /**
     * 接受广播并处理
     */
    class NewsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //收到广播后的处理
            String action = intent.getAction();
            switch (action)
            {
                case Counts.BROADCAST_NEWS:
                    List<SplashBnBean.NewsBean> newList = (List<SplashBnBean.NewsBean>) intent.getSerializableExtra("news");
                    if (newList != null && newList.size() > 0) {
                        mListInfo.clear();
                        if (newList.size() >= 2) {
                            showList = newList.subList(0, 2);
                            mAdapter.setNewData(showList);
                        } else {
                            showList = newList;
                        }
                        for (SplashBnBean.NewsBean dataInfo : showList) {
                            mListInfo.add(dataInfo);
                        }
                    }
                    break;
                default:
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastManager.getInstance().unregisterReceiver(getActivity(),mReceiver);
    }


}