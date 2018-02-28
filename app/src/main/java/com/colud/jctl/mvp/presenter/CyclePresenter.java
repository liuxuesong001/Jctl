package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.CycleUpdateBean;
import com.colud.jctl.mvp.contract.CycleContract;
import com.colud.jctl.mvp.model.CycleModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;


/**
 * Created by Jcty on 2017/3/11.
 */

public class CyclePresenter extends CycleContract.Presenter {


    public CyclePresenter(CycleContract.View view) {
        mView = view;
        mModel = new CycleModel();
    }


    //	@Override
    //	public void getCycleData(Map<String,String>map) {
    //		Subscription s=mModel.setCycleData(map).subscribe(new Subscriber<NodeCollectionCycle>() {
    //
    //			@Override
    //			public void onStart() {
    //				super.onStart();
    //				mView.showDialog();
    //			}
    //
    //			@Override
    //			public void onCompleted() {
    //				mView.hideDialog();
    //			}
    //
    //			@Override
    //			public void onError(Throwable e) {
    //				if (e instanceof JsonParseException){
    //					mView.onFailure("数据异常",e);
    //				}else if(e instanceof IOException){
    //					mView.onFailure("网络异常",e);
    //				}else if(e instanceof TimeoutException){
    //					mView.onFailure("网络异常",e);
    //				}else{
    //					mView.onFail(e.getMessage());
    //				}
    //				onCompleted();
    //			}
    //
    //			@Override
    //			public void onNext(NodeCollectionCycle data) {
    //				if (data.getFlag().equals("1"))
    //					mView.onSucceedLocation(data);
    //			}
    //		});
    //		addSubscribe(s);
    //	}

    @Override
    public void getCycleUpdate(Map<String, String> map) {
        Subscription s = mModel.setCycleUpdate(map).subscribe(new Subscriber<CycleUpdateBean>() {

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
            public void onNext(CycleUpdateBean data) {
                if (data.getFlag().equals("1"))
                    mView.onSucceedCycleUpdate(data);
            }
        });
        addSubscribe(s);
    }
}