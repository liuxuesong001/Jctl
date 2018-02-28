package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.NodeDataManageItem;
import com.colud.jctl.mvp.contract.NodeDataManageContract;
import com.colud.jctl.mvp.model.NodeDataManageModel;
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

public class NodeDataManagePresenter extends NodeDataManageContract.Presenter {


    public NodeDataManagePresenter(NodeDataManageContract.View view) {
        mView = view;
        mModel = new NodeDataManageModel();
    }


    @Override
    public void setNodeManage(Map<String, String> map) {
        Subscription s = mModel.getNodeManage(map).subscribe(new Subscriber<NodeDataManageItem>() {

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
            public void onNext(NodeDataManageItem data) {
                if (data != null && 1 == data.getFlag()) {
                    mView.onSucceed(data);
                } else {
                    ToastUtils.showLong(data.getMsg());
                }
            }
        });
        addSubscribe(s);
    }

    @Override
    public void setNodeUpdate(Map<String, String> map) {
        Subscription s = mModel.getNodeUpdate(map).subscribe(new Subscriber<NodeDataManageItem>() {

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
            public void onNext(NodeDataManageItem data) {
                if (data != null && 1 == data.getFlag()) {
                    mView.onSucceedUpdate(data);
                } else {
                    ToastUtils.showLong(data.getMsg());
                }
            }
        });
        addSubscribe(s);
    }
}