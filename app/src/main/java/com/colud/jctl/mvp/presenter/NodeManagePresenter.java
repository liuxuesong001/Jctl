package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.NodeManageItem;
import com.colud.jctl.mvp.contract.NodeManageContract;
import com.colud.jctl.mvp.model.NodeManageModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Jcty on 2017/3/11.
 */

public class NodeManagePresenter extends NodeManageContract.Presenter {


    public NodeManagePresenter(NodeManageContract.View view) {
        mView = view;
        mModel = new NodeManageModel();
    }

    @Override
    public void setNodeManageData(Map<String, String> map) {
        Subscription s = mModel.getNodeManageData(map).subscribe(new Subscriber<NodeManageItem>() {

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
            public void onNext(NodeManageItem data) {
                mView.onSucceed(data);
            }
        });
        addSubscribe(s);
    }

    @Override
    public void setNodeManageMore(Map<String, String> map) {
        Subscription s = mModel.getNodeManageData(map).subscribe(new Subscriber<NodeManageItem>() {

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
            public void onNext(NodeManageItem data) {
                mView.onSucceedMore(data);
            }
        });
        addSubscribe(s);
    }


}
