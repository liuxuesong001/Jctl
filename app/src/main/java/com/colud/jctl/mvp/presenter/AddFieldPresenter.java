package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.JsonBeanList;
import com.colud.jctl.bean.AddFieldData;
import com.colud.jctl.bean.AddFieldItem;
import com.colud.jctl.mvp.contract.AddFieldContract;
import com.colud.jctl.mvp.model.AddFieldModel;
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

public class AddFieldPresenter extends AddFieldContract.Presenter {


    public AddFieldPresenter(AddFieldContract.View view) {
        mView = view;
        mModel = new AddFieldModel();
    }


    @Override
    public void setAddField(String uid) {
        Subscription s = mModel.getAddField(uid).subscribe(new Subscriber<AddFieldItem>() {

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
                KLog.e(e.getMessage());
            }

            @Override
            public void onNext(AddFieldItem data) {
                if (data != null && "1".equals(data.getFlag())) {
                    mView.onSucceedUserId(data);
                } else {
                    ToastUtils.showLong(data.getMsg());
                }

            }
        });
        addSubscribe(s);
    }

    @Override
    public void setAddData(Map<String, String> map) {
        Subscription s = mModel.getAddData(map).subscribe(new Subscriber<AddFieldData>() {

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
                KLog.e(e.getMessage());
            }

            @Override
            public void onNext(AddFieldData data) {
                if (data != null && "1".equals(data.getFlag())) {
                    mView.onSucceedAdd(data);
                }

            }
        });
        addSubscribe(s);
    }

    @Override
    public void setAddRessJson(JsonBeanList address) {
        Subscription s = mModel.getAddRessJson(address).subscribe(new Subscriber<JsonBeanList>() {

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
            public void onNext(JsonBeanList data) {
                mView.onSucceedJson(data);
            }
        });
        addSubscribe(s);
    }
}