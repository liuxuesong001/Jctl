package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.mvp.contract.AddRelayContract;
import com.colud.jctl.mvp.model.AddRelayModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class AddRelayPresenter extends AddRelayContract.Presenter {


    public AddRelayPresenter(AddRelayContract.View view) {
        mView = view;
        mModel = new AddRelayModel();
    }


    @Override
    public void setAddRelay(Map<String, String> map) {
        Subscription s = mModel.getAddRelay(map).subscribe(new Subscriber<FarmManageBean>() {

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
                    mView.onSucceedAddRelay(data);
                } else {
                    data.getMsg();
                }
            }
        });
        addSubscribe(s);
    }

    @Override
    public void setAddFarmList(Map<String, String> map) {
        Subscription s = mModel.getRelayFarmList(map).subscribe(new Subscriber<FarmManageBean>() {

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
                    mView.onSucceedFarmList(data);
                } else {
                    data.getMsg();
                }
            }
        });
        addSubscribe(s);
    }
}