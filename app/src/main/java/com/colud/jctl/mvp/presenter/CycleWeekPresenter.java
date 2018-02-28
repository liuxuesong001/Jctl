package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.CycleWeekBean;
import com.colud.jctl.mvp.contract.CycleWeekContract;
import com.colud.jctl.mvp.model.CycleWeekModel;
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

public class CycleWeekPresenter extends CycleWeekContract.Presenter {


    public CycleWeekPresenter(CycleWeekContract.View view) {
        mView = view;
        mModel = new CycleWeekModel();
    }


    @Override
    //	public void setCycleWeekData(Map<String,String>date, Map<String, String[]> map) {
    //	public void setCycleWeekData(Map<String,String>date,  List<String> map) {
    public void setCycleWeekData(String cycle, Map<String, String> date, String[] week) {
        Subscription s = mModel.getCycleWeekData(cycle, date, week).subscribe(new Subscriber<CycleWeekBean>() {

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
            public void onNext(CycleWeekBean data) {
                if (data.getFlag().equals("1")) {
                    mView.onSucceed(data);
                } else {
                    ToastUtils.showLong(data.getMsg());
                }
            }
        });
        addSubscribe(s);
    }
}