package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.CycleUpdateBean;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface CycleContract {

    interface View extends BaseView {

        void showDialog();

        //		void onSucceedLocation(NodeCollectionCycle data);

        void onSucceedCycleUpdate(CycleUpdateBean data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        //		Observable<NodeCollectionCycle> setCycleData(Map<String,String>map);

        Observable<CycleUpdateBean> setCycleUpdate(Map<String, String> map);
    }

    abstract class Presenter extends BasePesenter<View, Model> {

        //		public abstract void getCycleData(Map<String,String>map);

        public abstract void getCycleUpdate(Map<String, String> map);
    }

}
