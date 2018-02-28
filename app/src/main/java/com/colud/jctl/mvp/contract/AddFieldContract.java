package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.JsonBeanList;
import com.colud.jctl.bean.AddFieldData;
import com.colud.jctl.bean.AddFieldItem;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface AddFieldContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceedUserId(AddFieldItem data);

        void onSucceedAdd(AddFieldData data);

        void onSucceedJson(JsonBeanList data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<AddFieldItem> getAddField(String uid);

        Observable<AddFieldData> getAddData(Map<String, String> map);

        Observable<JsonBeanList> getAddRessJson(JsonBeanList list);
    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setAddField(String uid);

        public abstract void setAddData(Map<String, String> map);

        public abstract void setAddRessJson(JsonBeanList list);
    }
}
