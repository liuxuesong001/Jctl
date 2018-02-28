package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.JsonBeanList;
import com.colud.jctl.bean.FarmDetailItem;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface FarmDetailContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(FarmDetailItem data);

        void onSucceedJson(JsonBeanList data);

        void onFailure(String err, Throwable e);

        void onSucceedUpdate(FarmDetailItem data);

        void onSucceedDelete(FarmDetailItem data);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<FarmDetailItem> getFarmData(Map<String, String> map);

        Observable<FarmDetailItem> getDeleteData(String id);

        Observable<FarmDetailItem> getFarmDataUpdate(Map<String, String> map);

        Observable<JsonBeanList> getAddRessJson(JsonBeanList list);

    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setFarmData(Map<String, String> map);

        public abstract void setDeleteData(String uid);

        public abstract void setAddRessJson(JsonBeanList list);

        public abstract void setFarmDataUpdate(Map<String, String> map);

    }
}
