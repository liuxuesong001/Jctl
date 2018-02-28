package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.FacilityItem;
import com.colud.jctl.mvp.contract.FacilityManageContract;
import com.colud.jctl.mvp.model.FacilityManageModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Jcty on 2017/3/11.
 */

public class FacilityManagePresenter extends FacilityManageContract.Presenter {


    public FacilityManagePresenter(FacilityManageContract.View view) {
        mView = view;
        mModel = new FacilityManageModel();
    }


    @Override
    public void setFacilityManageData(Map<String, String> map) {
        Subscription s = mModel.getFacilityManageData(map).subscribe(new Subscriber<FacilityItem>() {

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
            public void onNext(FacilityItem data) {
                mView.onSucceed(data);
            }
        });
        addSubscribe(s);
    }

    @Override
    public void setFacilityManageMore(Map<String, String> map) {
        Subscription s = mModel.getFacilityManageData(map).subscribe(new Subscriber<FacilityItem>() {

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
            public void onNext(FacilityItem data) {
                mView.onSucceedMore(data);
            }
        });
        addSubscribe(s);
    }
}
