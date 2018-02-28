package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.NewInfoItem;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface NewDynamicContract {

    interface View extends BaseView {


        void onSucceedNewInfo(NewInfoItem data);

        void onFailure(String err, Throwable e);

        void onFail(String err);
    }

    interface Model extends BaseModel {

        Observable<NewInfoItem> getNewDynamicData();

    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setNewDynamicData();

    }
}
