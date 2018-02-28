package com.colud.jctl.mvp.contract;


import android.content.Context;

import com.baidu.location.BDLocation;
import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.WeatherHome;

import rx.Observable;

/**
 * Splash契约类
 * Created by Jcty on 2017/3/11.
 */

public interface SplashContract {

    /**
     * 负责View界面的方法
     */
    interface View extends BaseView {

        void onSucceedLocation(BDLocation bdLocation);

        void onSuccedTemp(WeatherHome homeTemp);

        void onFail(String err);

        void onFailure(String err, Throwable e);

        void onLocatedFail(BDLocation bdLocation);



    }

    /**
     * 负责数据业务处理
     */
    interface Model extends BaseModel {

        Observable<BDLocation> setLocation();

        Observable<BDLocation> locateLastKnown();

        Observable<WeatherHome> setHomeTemp();



    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void getLocation(Context context);

        public abstract void locateLastKnown(Context context);

        public abstract void getHomeTemp();

    }

}
