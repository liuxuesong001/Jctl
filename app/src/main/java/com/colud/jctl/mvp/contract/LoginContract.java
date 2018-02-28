package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.UserItem;

import java.util.Map;

import rx.Observable;

/**
 * LoginContract契约类
 * Created by Jcty on 2017/3/11.
 */

public interface LoginContract {

    /**
     * 负责View界面的方法
     */
    interface View extends BaseView {

        void showDialog();

        void onSucceed(UserItem userItem);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    /**
     * 负责数据业务处理
     */
    interface Model extends BaseModel {


        Observable<UserItem> userData(Map<String,String> map);

    }

    abstract class Presenter extends BasePesenter<View, Model> {


        public abstract void getUserData(Map<String,String> map);


    }

}
