package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.NodeManageItem;

import java.util.Map;

import rx.Observable;

/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface NodeManageContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(NodeManageItem data);

        void onSucceedMore(NodeManageItem data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<NodeManageItem> getNodeManageData(Map<String, String> map);

        Observable<NodeManageItem> getNodeManageMore(Map<String, String> map);
    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setNodeManageData(Map<String, String> map);

        public abstract void setNodeManageMore(Map<String, String> map);
    }

}
