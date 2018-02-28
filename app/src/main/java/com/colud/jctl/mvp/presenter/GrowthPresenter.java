package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.GRItem;
import com.colud.jctl.mvp.contract.GrowthContract;
import com.colud.jctl.mvp.model.GrowthModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class GrowthPresenter extends GrowthContract.Presenter {


    public GrowthPresenter(GrowthContract.View view) {
        mView = view;
        mModel = new GrowthModel();
    }


    //	Publisher
    //	Subscriber
    //	Subscription
    //	Processor
    //Subscriber

    @Override
    public void setGR(Map<String, String> map) {
        Subscription s = mModel.getGR(map).subscribe(new Subscriber<GRItem>() {

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
            public void onNext(GRItem data) {
                if (data.getFlag().equals("1"))
                    mView.onSucceed(data);
            }
        });
        addSubscribe(s);
    }
}