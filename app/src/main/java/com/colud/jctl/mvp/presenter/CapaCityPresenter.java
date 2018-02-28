package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.CapaCityItem;
import com.colud.jctl.mvp.contract.CapaCityContract;
import com.colud.jctl.mvp.model.CapaCityModel;
import com.colud.jctl.utils.ToastUtils;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class CapaCityPresenter extends CapaCityContract.Presenter {


    public CapaCityPresenter(CapaCityContract.View view) {
        mView = view;
        mModel = new CapaCityModel();
    }


    //	Publisher
    //	Subscriber
    //	Subscription
    //	Processor
    //Subscriber

    @Override
    public void setCapaCity(String uid) {
        Subscription s = mModel.getCapaCity(uid).subscribe(new Subscriber<CapaCityItem>() {

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
            public void onNext(CapaCityItem data) {
                if (data != null && "1".equals(data.getFlag())) {
                    mView.onSucceed(data);
                } else {
                    ToastUtils.showLong(data.getMsg());
                }
            }
        });
        addSubscribe(s);
    }
}