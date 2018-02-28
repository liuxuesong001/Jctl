package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.LastNodeDetailsBean;
import com.colud.jctl.mvp.contract.LastNodeDatailsContract;
import com.colud.jctl.mvp.model.LastNodeDatailsModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class LastNodeDatailsPresenter extends LastNodeDatailsContract.Presenter {


    public LastNodeDatailsPresenter(LastNodeDatailsContract.View view) {
        mView = view;
        mModel = new LastNodeDatailsModel();
    }


    @Override
    public void setLastNodeDetails(Map<String, String> map) {
        Subscription s = mModel.getLastNodeDetails(map).subscribe(new Subscriber<LastNodeDetailsBean>() {

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
            public void onNext(LastNodeDetailsBean data) {
                if (data.getFlag() == 1) {
                    mView.onSucceed(data);
                } else {
                    data.getMsg();
                }
            }
        });
        addSubscribe(s);
    }
}