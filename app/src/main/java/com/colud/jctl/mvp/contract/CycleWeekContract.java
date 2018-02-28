package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.CycleWeekBean;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface CycleWeekContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(CycleWeekBean data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        //		Observable<CycleWeekBean> getCycleWeekData(Map<String,String>date, Map<String,String[]>map);
        //		Observable<CycleWeekBean> getCycleWeekData(Map<String,String>date, ArrayList<String> data);
        Observable<CycleWeekBean> getCycleWeekData(String cycle, Map<String, String> date, String[] week);
    }

    abstract class Presenter extends BasePesenter<View, Model> {

        //		public abstract void setCycleWeekData(Map<String,String>date, Map<String,String[]>map);
        //		public abstract void setCycleWeekData(Map<String,String>date,  ArrayList<String>data);
        public abstract void setCycleWeekData(String cycle, Map<String, String> date, String[] week);
    }

}
