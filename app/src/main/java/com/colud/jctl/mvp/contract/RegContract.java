package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.FindBean;
import com.colud.jctl.bean.RegItem;
import com.colud.jctl.bean.SmsItem;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface RegContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(RegItem data);

        void onSucceedSms(SmsItem data);

        void onSucceedFind(FindBean data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<RegItem> getRegData(
                Map<String, String> map
        );

        Observable<SmsItem> getSmsData(Map<String, String> map);

        Observable<FindBean> getFindData(Map<String, String> map);

    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setRegData(
                Map<String, String> map);

        public abstract void setSmsData(
                Map<String, String> map);

        public abstract void setFindData(
                Map<String, String> map);

    }
}
