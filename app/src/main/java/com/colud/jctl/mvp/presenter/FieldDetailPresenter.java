package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.AddFieldData;
import com.colud.jctl.bean.FieldDetailItem;
import com.colud.jctl.mvp.contract.FieldDetailContract;
import com.colud.jctl.mvp.model.FieldDetailModel;
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

public class FieldDetailPresenter extends FieldDetailContract.Presenter {


    public FieldDetailPresenter(FieldDetailContract.View view) {
        mView = view;
        mModel = new FieldDetailModel();
    }


    @Override
    public void setFieldData(Map<String, String> map) {
        Subscription s = mModel.getFieldData(map).subscribe(new Subscriber<FieldDetailItem>() {

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
            public void onNext(FieldDetailItem data) {
                if (data != null && "1".equals(data.getFlag())) {
                    mView.onSucceed(data);
                } else {
                    KLog.d(data.getMsg());
                }
            }
        });
        addSubscribe(s);
    }

    @Override
    public void setDeleteData(Map<String, String> map) {
        Subscription s = mModel.getDeleteData(map).subscribe(new Subscriber<FieldDetailItem>() {

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
            public void onNext(FieldDetailItem data) {
                if (data != null && "1".equals(data.getFlag())) {
                    mView.onSucceedDelete(data);
                } else {
                    ToastUtils.showLong(data.getMsg());
                }

            }
        });
        addSubscribe(s);
    }

    @Override
    public void setFieldDataUpdate(Map<String, String> map) {
        Subscription s = mModel.getFieldDataUpdate(map).subscribe(new Subscriber<AddFieldData>() {

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
                    mView.onSucceedUpdate(data);
                } else {
                    ToastUtils.showLong(data.getMsg());
                }

            }
        });
        addSubscribe(s);

    }
}