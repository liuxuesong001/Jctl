package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.NodeItem;

import java.util.Map;

import rx.Observable;

/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface NodeContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(NodeItem data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<NodeItem> getNodeData(Map<String, String> map);
    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setNodeData(Map<String, String> map);
    }

}
