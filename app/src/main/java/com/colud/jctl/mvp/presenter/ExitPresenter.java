package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.ExitItem;
import com.colud.jctl.bean.VersionUpdateBean;
import com.colud.jctl.mvp.contract.ExitContract;
import com.colud.jctl.mvp.model.ExitModel;
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

public class ExitPresenter extends ExitContract.Presenter {


    public ExitPresenter(ExitContract.View view) {
        mView = view;
        mModel = new ExitModel();
    }


    @Override
    public void setExit() {
        Subscription subscription = mModel.getExit()
                .subscribe(new Subscriber<ExitItem>() {

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
                    public void onNext(ExitItem item) {
                        if (item.getFlag() == 1) {
                            mView.onSucceed(item);
                        } else {
                            item.getMsg();
                        }
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void setUpdateV(Map<String, String> map) {
        Subscription subscription = mModel.getUpdateV(map)
                .subscribe(new Subscriber<VersionUpdateBean>() {

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
                    public void onNext(VersionUpdateBean item) {
                        if (item.getFlag() == 1) {
                            mView.onSucceedUpdate(item);
                        } else {
                            ToastUtils.showLong(item.getMsg());
                        }
                    }
                });
        addSubscribe(subscription);
    }
}