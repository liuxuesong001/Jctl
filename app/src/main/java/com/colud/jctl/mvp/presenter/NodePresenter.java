package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.NodeItem;
import com.colud.jctl.mvp.contract.NodeContract;
import com.colud.jctl.mvp.model.NodeModel;
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

public class NodePresenter extends NodeContract.Presenter {


    public NodePresenter(NodeContract.View view) {
        mView = view;
        mModel = new NodeModel();
    }


    @Override
    public void setNodeData(Map<String, String> map) {
        Subscription s = mModel.getNodeData(map).subscribe(new Subscriber<NodeItem>() {

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
            public void onNext(NodeItem data) {
                if (data != null && "1".equals(data.getFlag()))
                    mView.onSucceed(data);
            }
        });
        addSubscribe(s);
    }


}
