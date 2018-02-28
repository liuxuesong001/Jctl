package com.colud.jctl.base;

import android.text.TextUtils;

import com.colud.jctl.api.ResultResponse;
import com.socks.library.KLog;

import rx.Subscriber;

/**
 * @author ChayChan
 * @description: 抽取CallBack
 * @date 2017/6/18  21:37
 */
public abstract class SubscriberCallBack<T> extends Subscriber<ResultResponse<T>> {

    @Override
    public void onNext(ResultResponse response) {
        boolean isSuccess = (!TextUtils.isEmpty(response.message) && response.message.equals("success"))
                || !TextUtils.isEmpty(response.success) && response.success.equals("true");
        if (isSuccess) {
            onSuccess((T) response.data);
        } else {
            onError((T) response.message);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        KLog.e(e.getLocalizedMessage());
    }

    protected abstract void onSuccess(T response);

    protected abstract void onError(T e);

    protected void onFailure(ResultResponse response) {
        KLog.e(response.toString());
    }

}
