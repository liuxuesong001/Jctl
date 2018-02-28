package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.bean.RelayDetailItem;
import com.colud.jctl.mvp.contract.RelayDetailContract;
import com.colud.jctl.mvp.model.RelayDetailModel;
import com.colud.jctl.utils.ToastUtils;
import com.google.gson.JsonParseException;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class RelayDetailPresenter extends RelayDetailContract.Presenter {


    public RelayDetailPresenter(RelayDetailContract.View view) {
        mView = view;
        mModel = new RelayDetailModel();
    }


    @Override
    public void setRelayData(Map<String, String> map) {
        Subscription s = mModel.getRelayData(map).subscribe(new Subscriber<RelayDetailItem>() {

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
                KLog.e(e.getMessage());
                onCompleted();
            }

            @Override
            public void onNext(RelayDetailItem data) {
                if (data != null && data.getFlag() == 1) {
                    mView.onSucceedInfo(data);
                } else {
                    ToastUtils.showLong(data.getMsg());
                }

            }
        });
        addSubscribe(s);
    }

    @Override
    public void setRelayUpdate(Map<String, String> map) {
        Subscription s = mModel.getRelayUpdate(map).subscribe(new Subscriber<FarmManageBean>() {

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
                if (data.getFlag() == 1) {
                    mView.onSucceedRelayUpdate(data);
                } else {
                    data.getMsg();
                }
            }
        });
        addSubscribe(s);
    }

    @Override
    public void setRelayDelete(Map<String, String> map) {
        Subscription s = mModel.getRelayDelete(map).subscribe(new Subscriber<RelayDetailItem>() {

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
            public void onNext(RelayDetailItem data) {
                if (data.getFlag() == 1) {
                    mView.onSucceedRelayDelete(data);
                } else {
                    data.getMsg();
                }
            }
        });
        addSubscribe(s);
    }
}