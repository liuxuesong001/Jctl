package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.FarmManageBean;

import java.util.Map;

import rx.Observable;

/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface FarmManageContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(FarmManageBean data);


        void onSucceedMore(FarmManageBean data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<FarmManageBean> getFarmManageData(Map<String, String> map);

        Observable<FarmManageBean> getFarmManageMore(Map<String, String> map);
    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setFarmManageData(Map<String, String> map);

        public abstract void setFarmManageMore(Map<String, String> map);
    }

}
