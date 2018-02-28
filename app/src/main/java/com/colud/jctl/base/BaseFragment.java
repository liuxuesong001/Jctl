package com.colud.jctl.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.colud.jctl.utils.BarStatusHeightUtil;

import butterknife.ButterKnife;

/**
 * BaseFragment
 * Created by Jcty on 2017/3/1.
 */

public abstract class BaseFragment<P extends BasePesenter> extends Fragment implements UIInitF {

    protected FragmentActivity mActivity;
    protected P mPresenter;
    protected View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        //        if (isRegisterEventBus()){
        //            EventBus.getDefault().register(this);
        //        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //        AppManager.newInstance().removectivity(getActivity());
        if (mPresenter != null) {
            onCreatePresenter().unSubscribe();
        }
        //        if (isRegisterEventBus())
        //        {
        //            EventBus.getDefault().unregister(this);
        //        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (BarStatusHeightUtil.checkDeviceHasNavigationBar(getActivity())) {
            BarStatusHeightUtil.hideBottomUIMenu(getActivity());
        }
        if (rootView == null) {
            rootView = inflater.inflate(getContentViewId(), container, false);
            ButterKnife.bind(this, rootView);
            initViews();
            addListener();
            initData();
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);

            }
        }
        return rootView;
    }

    protected abstract P onCreatePresenter();

//    public abstract void onEvent(MyEvent eventData);

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    //    protected boolean isRegisterEventBus() {
    //        return false;
    //    }

}
