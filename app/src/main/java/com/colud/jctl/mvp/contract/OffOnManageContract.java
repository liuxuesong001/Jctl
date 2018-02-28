package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.NodeCollectionCycle;
import com.colud.jctl.bean.OffOnItemManage;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface OffOnManageContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceedHand(OffOnItemManage data);

        void onSucceedHandlerResult(OffOnItemManage data);

        void onSucceedAll(NodeCollectionCycle data);

        void onSucceedState(OffOnItemManage data);

        void onFailure(String err, Throwable e);

        void onFailureHandlerResult(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<OffOnItemManage> getOffOnData(String nodeMac, String status);

        Observable<NodeCollectionCycle> setNodeCycle(Map<String, String> map);

        Observable<OffOnItemManage> getHandlerResult(Map<String, String> map);

    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setOffOnItem(String nodeMac, String status);

        public abstract void getNodeCycle(Map<String, String> map);

        public abstract void getHandlerResult(Map<String, String> map);


    }
}
