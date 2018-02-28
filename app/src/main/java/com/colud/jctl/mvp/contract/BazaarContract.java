package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.BazaarItem;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface BazaarContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(BazaarItem data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    //	Flowable
    interface Model extends BaseModel {

        Observable<BazaarItem> getBazaarData(String uid);
    }

    abstract class Presenter extends BasePesenter<View, Model> {
        public abstract void setBazaaData(String uid);
    }

}
