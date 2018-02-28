package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.ChannelsBean;
import com.colud.jctl.mvp.contract.ChannelContract;
import com.colud.jctl.mvp.model.ChannelModel;
import com.colud.jctl.utils.ToastUtils;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class ChannelPresenter extends ChannelContract.Presenter {


    public ChannelPresenter(ChannelContract.View view) {
        mView = view;
        mModel = new ChannelModel();
    }


    @Override
    public void setChannelData(Map<String, Object> map) {
        Subscription s = mModel.getChannelData(map).subscribe(new Subscriber<ChannelsBean>() {

            @Override
            public void onStart() {
                super.onStart();
                //				mView.showDialog();
            }

            @Override
            public void onCompleted() {
                //				mView.hideDialog();
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
            public void onNext(ChannelsBean data) {
                if (data != null) {
                    mView.onSucceed(data);

                } else {
                    ToastUtils.showLong("暂无摄像头信息");
                }
            }
        });
        addSubscribe(s);
    }

    @Override
    public void setPTZ(Map<String, Object> map) {
        Subscription s = mModel.getPTZ(map).subscribe(new Subscriber<ChannelsBean>() {

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
            public void onNext(ChannelsBean data) {
                if (data != null) {
                    mView.onSucceedPTZ(data);

                } else {
                    ToastUtils.showLong("暂无摄像头信息");
                }
            }
        });
        addSubscribe(s);
    }
}