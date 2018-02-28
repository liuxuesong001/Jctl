package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.JsonBeanList;
import com.colud.jctl.bean.AddFarmItem;
import com.colud.jctl.mvp.contract.AddFarmContract;
import com.colud.jctl.mvp.model.AddFarmModel;
import com.colud.jctl.utils.ToastUtils;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 *
 * Created by Jcty on 2017/3/11.
 */

public class AddFarmPresenter extends AddFarmContract.Presenter {


    public AddFarmPresenter(AddFarmContract.View view) {
        mView = view;
        mModel = new AddFarmModel();
    }


    @Override
    public void setAddFarmata(Map<String, String> map) {
        Subscription s = mModel.getAddFarmData(map).subscribe(new Subscriber<AddFarmItem>() {

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
            public void onNext(AddFarmItem data) {
                if (data != null && data.getFlag() == 1) {
                    mView.onSucceed(data);
                } else {
                    ToastUtils.showLong(data.getMsg());
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
                mView.showDialog();
            }

            @Override
            public void onCompleted() {
                mView.hideDialog();
            }

            @Override
            public void onError(Throwable e) {
                mView.onFail(e.getMessage());
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