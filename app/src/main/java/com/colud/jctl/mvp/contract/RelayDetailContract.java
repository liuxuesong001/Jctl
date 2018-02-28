package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.bean.RelayDetailItem;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface RelayDetailContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceedInfo(RelayDetailItem data);

        void onSucceedRelayUpdate(FarmManageBean data);

        void onSucceedRelayDelete(RelayDetailItem data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<RelayDetailItem> getRelayData(Map<String, String> map);

        Observable<RelayDetailItem> getRelayDelete(Map<String, String> map);

        Observable<FarmManageBean> getRelayUpdate(Map<String, String> map);


    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setRelayData(Map<String, String> map);

        public abstract void setRelayUpdate(Map<String, String> map);

        public abstract void setRelayDelete(Map<String, String> map);

    }
}
