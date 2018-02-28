package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.FieldManageItem;
import com.colud.jctl.mvp.contract.FieldManageContract;
import com.colud.jctl.mvp.model.FieldManageModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Jcty on 2017/3/11.
 */

public class FieldManagePresenter extends FieldManageContract.Presenter {


    public FieldManagePresenter(FieldManageContract.View view) {
        mView = view;
        mModel = new FieldManageModel();
    }


    @Override
    public void setFieldManageData(Map<String, String> map) {
        Subscription s = mModel.getFieldManageData(map).subscribe(new Subscriber<FieldManageItem>() {

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
            public void onNext(FieldManageItem data) {
                mView.onSucceed(data);
            }
        });
        addSubscribe(s);
    }

    @Override
    public void setFieldManageMore(Map<String, String> map) {
        Subscription s = mModel.getFieldManageData(map).subscribe(new Subscriber<FieldManageItem>() {

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
            public void onNext(FieldManageItem data) {
                mView.onSucceedMore(data);
            }
        });
        addSubscribe(s);
    }
}
