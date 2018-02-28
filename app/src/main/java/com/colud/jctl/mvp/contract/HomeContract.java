package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.SplashBnBean;
import com.colud.jctl.bean.UserItem;
import com.colud.jctl.bean.VersionUpdateBean;

import java.util.Map;

import rx.Observable;

/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface HomeContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceedSingleId(UserItem data);

        void onSucceedRegLogin(UserItem userItem);

        void onSucceedUpdate(VersionUpdateBean data);

        void onSucceedBannerNew(SplashBnBean data);

        void onFailure(String err, Throwable e);

        void onFailureLogin(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<UserItem> getHomeData(Map<String,String>map);

        Observable<UserItem> getRegLogin(Map<String, String> map);

        Observable<SplashBnBean> getBannerNew();

        Observable<VersionUpdateBean> getUpdateV(Map<String, String> map);


    }

    abstract class Presenter extends BasePesenter<View, Model> {


        public abstract void setBannerNew();

        public abstract void setSingleId(Map<String,String>map);

        public abstract void setRegLogin(Map<String, String> map);

        public abstract void setUpdateV(Map<String, String> map);

    }

}
