package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.ExitItem;
import com.colud.jctl.bean.VersionUpdateBean;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface ExitContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(ExitItem data);

        void onSucceedUpdate(VersionUpdateBean data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<ExitItem> getExit();

        Observable<VersionUpdateBean> getUpdateV(Map<String, String> map);

    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setExit();

        public abstract void setUpdateV(Map<String, String> map);

    }
}
