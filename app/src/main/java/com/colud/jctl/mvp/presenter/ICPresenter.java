package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.ICItem;
import com.colud.jctl.mvp.contract.ICContract;
import com.colud.jctl.mvp.model.ICModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class ICPresenter extends ICContract.Presenter {


    public ICPresenter(ICContract.View view) {
        mView = view;
        mModel = new ICModel();
    }

    @Override
    public void setICItem(String state, Map<String, String> map, double max, double min) {
        Subscription s = mModel.getICItem(state, map, max, min).subscribe(new Subscriber<ICItem>() {

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
                mView.onFail(e.getMessage());
                onCompleted();
            }

            @Override
            public void onNext(ICItem data) {
                if (data != null && "1".equals(data.getFlag())) {
                    mView.onSucceed(data);
                }
            }
        });
        addSubscribe(s);
    }


    //	Publisher
    //	Subscriber
    //	Subscription
    //	Processor
    //Subscriber


}