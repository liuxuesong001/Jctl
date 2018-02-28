package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.AddFieldData;
import com.colud.jctl.bean.FieldDetailItem;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface FieldDetailContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(FieldDetailItem data);

        void onSucceedUpdate(AddFieldData data);

        void onSucceedDelete(FieldDetailItem data);

        void onFail(String err);

        void onFailure(String err, Throwable e);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<FieldDetailItem> getFieldData(Map<String, String> map);

        Observable<FieldDetailItem> getDeleteData(Map<String, String> map);

        Observable<AddFieldData> getFieldDataUpdate(Map<String, String> map);


    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setFieldData(Map<String, String> map);

        public abstract void setDeleteData(Map<String, String> map);

        public abstract void setFieldDataUpdate(Map<String, String> map);

    }
}
