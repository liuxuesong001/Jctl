package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.JsonBeanList;
import com.colud.jctl.bean.AddFarmItem;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface AddFarmContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(AddFarmItem data);

        void onFailure(String err, Throwable e);

        void onSucceedJson(JsonBeanList data);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<AddFarmItem> getAddFarmData(Map<String, String> map);

        Observable<JsonBeanList> getAddRessJson(JsonBeanList list);
    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setAddFarmata(Map<String, String> map);

        public abstract void setAddRessJson(JsonBeanList list);
    }
}
