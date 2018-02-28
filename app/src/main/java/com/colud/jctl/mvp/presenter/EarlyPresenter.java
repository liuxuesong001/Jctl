package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.EarlyItme;
import com.colud.jctl.mvp.contract.EarlyContract;
import com.colud.jctl.mvp.model.EarlyModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class EarlyPresenter extends EarlyContract.Presenter {


    public EarlyPresenter(EarlyContract.View view) {
        mView = view;
        mModel = new EarlyModel();
    }


    @Override
    public void getEarlyData(String uid) {
        Subscription s = mModel.setEarlyData(uid).subscribe(new Subscriber<EarlyItme>() {

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
            public void onNext(EarlyItme bazaarItem) {
                mView.onSucceed(bazaarItem);
            }
        });
        addSubscribe(s);
    }
}