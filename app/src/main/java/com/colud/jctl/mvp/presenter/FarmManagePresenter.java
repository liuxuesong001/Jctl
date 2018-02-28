package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.mvp.contract.FarmManageContract;
import com.colud.jctl.mvp.model.FarmManageModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Jcty on 2017/3/11.
 */

public class FarmManagePresenter extends FarmManageContract.Presenter {


    public FarmManagePresenter(FarmManageContract.View view) {
        mView = view;
        mModel = new FarmManageModel();
    }

    @Override
    public void setFarmManageData(Map<String, String> map) {
        Subscription s = mModel.getFarmManageData(map).subscribe(new Subscriber<FarmManageBean>() {

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
            public void onNext(FarmManageBean data) {
                mView.onSucceed(data);
            }
        });
        addSubscribe(s);
    }

    @Override
    public void setFarmManageMore(Map<String, String> map) {
        Subscription s = mModel.getFarmManageData(map).subscribe(new Subscriber<FarmManageBean>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {
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
            public void onNext(FarmManageBean data) {
                mView.onSucceedMore(data);
            }
        });
        addSubscribe(s);
    }


}
