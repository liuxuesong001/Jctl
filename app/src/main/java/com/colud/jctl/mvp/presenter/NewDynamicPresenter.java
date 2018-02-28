package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.NewInfoItem;
import com.colud.jctl.mvp.contract.NewDynamicContract;
import com.colud.jctl.mvp.model.NewDynamicModel;
import com.google.gson.JsonParseException;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class NewDynamicPresenter extends NewDynamicContract.Presenter {


    public NewDynamicPresenter(NewDynamicContract.View view) {
        mView = view;
        mModel = new NewDynamicModel();
    }


    @Override
    public void setNewDynamicData() {
        Subscription s = mModel.getNewDynamicData().subscribe(new Subscriber<NewInfoItem>() {
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
            public void onNext(NewInfoItem newInfoItem) {
                mView.onSucceedNewInfo(newInfoItem);
            }
        });
        addSubscribe(s);
    }
}