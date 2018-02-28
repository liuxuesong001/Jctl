package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.GRItem;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface GrowthContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(GRItem data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    //	Flowable
    interface Model extends BaseModel {

        Observable<GRItem> getGR(Map<String, String> map);
    }

    abstract class Presenter extends BasePesenter<View, Model> {
        public abstract void setGR(Map<String, String> map);
    }

}
