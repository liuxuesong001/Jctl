package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.EarlyItme;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface EarlyContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(EarlyItme data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<EarlyItme> setEarlyData(String uid);
    }

    abstract class Presenter extends BasePesenter<View, Model> {
        public abstract void getEarlyData(String uid);
    }

}
