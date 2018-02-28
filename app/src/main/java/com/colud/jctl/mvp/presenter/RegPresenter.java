package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.FindBean;
import com.colud.jctl.bean.RegItem;
import com.colud.jctl.bean.SmsItem;
import com.colud.jctl.mvp.contract.RegContract;
import com.colud.jctl.mvp.model.RegModel;
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

public class RegPresenter extends RegContract.Presenter {


    public RegPresenter(RegContract.View view) {
        mView = view;
        mModel = new RegModel();
    }


    @Override
    public void setRegData(Map<String, String> map) {
        Subscription s = mModel.getRegData(map)
                .subscribe(new Subscriber<RegItem>() {

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
                    public void onNext(RegItem item) {
                        mView.onSucceed(item);
                    }
                });
        addSubscribe(s);
    }

    @Override
    public void setSmsData(Map<String, String> map) {
        Subscription s = mModel.getSmsData(map)
                .subscribe(new Subscriber<SmsItem>() {

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
                    public void onNext(SmsItem item) {
                        if (item.getFlag().equals("1")) {
                            mView.onSucceedSms(item);
                        } else {
                            ToastUtils.showLong(item.getMsg());
                        }
                    }
                });
        addSubscribe(s);
    }

    @Override
    public void setFindData(Map<String, String> map) {
        Subscription s = mModel.getFindData(map)
                .subscribe(new Subscriber<FindBean>() {

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
                    public void onNext(FindBean item) {
                        if (item.getFlag() == 1) {
                            mView.onSucceedFind(item);
                        } else {
                            ToastUtils.showLong(item.getMsg());
                        }
                    }
                });
        addSubscribe(s);
    }
}