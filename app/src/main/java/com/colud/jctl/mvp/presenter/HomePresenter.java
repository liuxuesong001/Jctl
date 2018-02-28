package com.colud.jctl.mvp.presenter;


import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.bean.SplashBnBean;
import com.colud.jctl.bean.UserItem;
import com.colud.jctl.bean.VersionUpdateBean;
import com.colud.jctl.mvp.contract.HomeContract;
import com.colud.jctl.mvp.model.HomeModel;
import com.colud.jctl.utils.ToastUtils;
import com.google.gson.JsonParseException;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;

/**
 *
 * Created by Jcty on 2017/3/11.
 */

public class HomePresenter extends HomeContract.Presenter {


    public HomePresenter(HomeContract.View view) {
        mView = view;
        mModel = new HomeModel();
    }
    @Override
    public void setBannerNew() {
        Subscription subscrie = mModel.getBannerNew().subscribe(new Subscriber<SplashBnBean>() {

            @Override
            public void onStart() {
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
            public void onNext(SplashBnBean data) {
                if (data.getFlag() == 1)
                {
                    mView.onSucceedBannerNew(data);
                }
            }
        });
        addSubscribe(subscrie);
    }
    @Override
    public void setSingleId(Map<String,String>map) {
        Subscription s = mModel.getHomeData(map).subscribe(new Subscriber<UserItem>() {

            @Override
            public void onStart() {
                mView.showDialog();
            }

            @Override
            public void onCompleted() {
                mView.hideDialog();

            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof JsonParseException) {
                    mView.onFailureLogin("数据异常", e);
                } else if (e instanceof IOException) {
                    mView.onFailureLogin("网络异常", e);
                } else if (e instanceof TimeoutException) {
                    mView.onFailureLogin("网络异常", e);
                } else {
                    mView.onFail(e.getMessage());
                }
                onCompleted();
                KLog.e(e.getMessage());
            }

            @Override
            public void onNext(UserItem data) {
                if (data != null && 1 == data.getFlag()) {
                    mView.onSucceedSingleId(data);
                } else {
                    ToastUtils.showLong(data.getMsg());
                    JctlApplication.getCache().remove(KeyConstants.USER_ITEM);
                    KLog.d(data.getMsg());
                }
            }
        });
        addSubscribe(s);
    }

    @Override
    public void setRegLogin(Map<String, String> map) {
        Subscription s = mModel.getRegLogin(map).subscribe(new Subscriber<UserItem>() {

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
            public void onNext(UserItem userItem) {
                mView.onSucceedRegLogin(userItem);
            }
        });
        addSubscribe(s);
    }

    @Override
    public void setUpdateV(Map<String, String> map) {
        Subscription subscription = mModel.getUpdateV(map)
                .subscribe(new Subscriber<VersionUpdateBean>() {

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
                    public void onNext(VersionUpdateBean item) {
                        if (item.getFlag() == 1) {
                            mView.onSucceedUpdate(item);
                        }
                        //                        else {
                        //							ToastUtils.showLong(item.getMsg());
                        //						}
                    }
                });
        addSubscribe(subscription);
    }

}
