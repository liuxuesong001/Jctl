package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.NodeCollectionCycle;
import com.colud.jctl.bean.OffOnItemManage;
import com.colud.jctl.mvp.contract.OffOnManageContract;
import com.colud.jctl.mvp.model.OffOnManageModel;
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

public class OffOnManagePresenter extends OffOnManageContract.Presenter {


    public OffOnManagePresenter(OffOnManageContract.View view) {
        mView = view;
        mModel = new OffOnManageModel();
    }


    @Override
    public void setOffOnItem(String nodeMac, String status) {
        Subscription s = mModel.getOffOnData(nodeMac, status).subscribe(new Subscriber<OffOnItemManage>() {

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
                KLog.e(e.getMessage());
                onCompleted();
            }

            @Override
            public void onNext(OffOnItemManage data) {
                mView.onSucceedHand(data);
                //				if (data!=null&&data.getFlag().equals("1")){
                //					mView.onSucceedHand(data);
                //				}else {
                //					//					mView.onSucceedState(data);
                //					ToastUtils.showLong(data.getMsg());
                //				}
            }
        });
        addSubscribe(s);
    }

    @Override
    public void getNodeCycle(Map<String, String> map) {
        Subscription s = mModel.setNodeCycle(map).subscribe(new Subscriber<NodeCollectionCycle>() {

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
                KLog.e(e.getMessage());
                onCompleted();
            }

            @Override
            public void onNext(NodeCollectionCycle data) {
                if (data != null && data.getFlag().equals("1")) {
                    mView.onSucceedAll(data);
                } else {
                    //					mView.onSucceedState(data);
                    ToastUtils.showLong(data.getMsg());
                }
            }
        });
        addSubscribe(s);
    }

    @Override
    public void getHandlerResult(Map<String, String> map) {
        Subscription s = mModel.getHandlerResult(map).subscribe(new Subscriber<OffOnItemManage>() {

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
                KLog.e(e.getMessage());
                onCompleted();
            }

            @Override
            public void onNext(OffOnItemManage data) {
                mView.onSucceedHandlerResult(data);
            }
        });
        addSubscribe(s);
    }
}