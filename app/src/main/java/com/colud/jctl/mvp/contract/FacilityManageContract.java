package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.FacilityItem;

import java.util.Map;

import rx.Observable;

/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface FacilityManageContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(FacilityItem data);

        void onSucceedMore(FacilityItem data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<FacilityItem> getFacilityManageData(Map<String, String> map);

        Observable<FacilityItem> getFacilityManageMore(Map<String, String> map);
    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setFacilityManageData(Map<String, String> map);

        public abstract void setFacilityManageMore(Map<String, String> map);
    }

}
