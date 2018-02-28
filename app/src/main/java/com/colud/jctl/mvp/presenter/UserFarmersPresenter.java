package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.FarmersBean;
import com.colud.jctl.mvp.contract.UserFarmersContract;
import com.colud.jctl.mvp.model.UserFarmersModel;
import com.colud.jctl.utils.ToastUtils;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class UserFarmersPresenter extends UserFarmersContract.Presenter {


    public UserFarmersPresenter(UserFarmersContract.View view) {
        mView = view;
        mModel = new UserFarmersModel();
    }


    @Override
    public void setUserFarmers(Map<String, String> map) {
        Subscription s = mModel.getUserFarmers(map).subscribe(new Subscriber<FarmersBean>() {

            @Override
            public void onStart() {
                super.onStart();
                mView.showDialog();
            }

            @Override
            public void onCompleted() {
                mView.hideDialog();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof JsonParseException) {
                    mView.onFailure("数据异常", e);
                } else if (e instanceof IOException) {
                    mView.onFailure("网络异常", e);
                } else if (e instanceof TimeoutException) {
                    mView.onFailure("网络异常", e);
                } else {
                    mView.onFail(e.getMessage());
                }
                onCompleted();
            }

            @Override
            public void onNext(FarmersBean data) {
                if (data.getFlag() == 1) {
                    mView.onSucceed(data);
                } else {
                    ToastUtils.showLong(data.getMsg());
                }
            }
        });
        addSubscribe(s);

    }
}