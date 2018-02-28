package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.NodeDataManageItem;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface NodeDataManageContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(NodeDataManageItem data);

        void onSucceedUpdate(NodeDataManageItem data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<NodeDataManageItem> getNodeManage(Map<String, String> map);

        Observable<NodeDataManageItem> getNodeUpdate(Map<String, String> map);


    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setNodeManage(Map<String, String> map);

        public abstract void setNodeUpdate(Map<String, String> map);


    }
}
