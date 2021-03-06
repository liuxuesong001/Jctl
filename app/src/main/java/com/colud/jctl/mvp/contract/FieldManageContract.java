package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.FieldManageItem;

import java.util.Map;

import rx.Observable;

/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface FieldManageContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(FieldManageItem data);

        void onSucceedMore(FieldManageItem data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<FieldManageItem> getFieldManageData(Map<String, String> map);

        Observable<FieldManageItem> getFieldManageMore(Map<String, String> map);
    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setFieldManageData(Map<String, String> map);

        public abstract void setFieldManageMore(Map<String, String> map);
    }

}
