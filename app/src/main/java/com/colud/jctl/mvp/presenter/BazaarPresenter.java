package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.BazaarItem;
import com.colud.jctl.mvp.contract.BazaarContract;
import com.colud.jctl.mvp.model.BazaarModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import rx.Subscription;
import rx.Subscriber;


/**
 * Created by Jcty on 2017/3/11.
 */

public class BazaarPresenter extends BazaarContract.Presenter {


    public BazaarPresenter(BazaarContract.View view) {
        mView = view;
        mModel = new BazaarModel();
    }


    @Override
    public void setBazaaData(String uid) {
        Subscription s = mModel.getBazaarData(uid).subscribe(new Subscriber<BazaarItem>() {

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
            public void onNext(BazaarItem bazaarItem) {
                if (bazaarItem.getFlag() == 1) {
                    mView.onSucceed(bazaarItem);
                } else {
                    bazaarItem.getMsg();
                }
            }
        });
        addSubscribe(s);
    }
}