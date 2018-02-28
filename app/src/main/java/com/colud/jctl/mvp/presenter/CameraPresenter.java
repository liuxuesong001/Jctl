package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.CameraBean;
import com.colud.jctl.mvp.contract.CameraContract;
import com.colud.jctl.mvp.model.CameraModel;
import com.colud.jctl.utils.ToastUtils;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class CameraPresenter extends CameraContract.Presenter {


    public CameraPresenter(CameraContract.View view) {
        mView = view;
        mModel = new CameraModel();
    }


    @Override
    public void setCameraData() {
        Subscription s = mModel.getCameraData().subscribe(new Subscriber<CameraBean>() {

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
            public void onNext(CameraBean data) {
                if (data != null) {
                    mView.onSucceed(data);
                } else {
                    ToastUtils.showLong("暂无摄像头信息");
                }
            }
        });
        addSubscribe(s);
    }
}