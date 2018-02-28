package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.IconItem;

import java.util.Map;

import rx.Observable;

/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface IconContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(IconItem data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {
        //		Observable<String> getIconData(File file);
        Observable<IconItem> getIconData(Map<String, String> map);
    }

    abstract class Presenter extends BasePesenter<View, Model> {
        //		public abstract void setIconData(File file);
        public abstract void setIconData(Map<String, String> map);
    }

}
