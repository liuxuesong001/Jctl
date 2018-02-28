package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.IconItem;
import com.colud.jctl.mvp.contract.IconContract;
import com.colud.jctl.mvp.model.IconModel;
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

public class IconPresenter extends IconContract.Presenter {


    public IconPresenter(IconContract.View view) {
        mView = view;
        mModel = new IconModel();
    }

    @Override
    public void setIconData(Map<String, String> map) {
        Subscription s = mModel.getIconData(map).subscribe(new Subscriber<IconItem>() {
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
            public void onNext(IconItem str) {
                if (str.getFlag() == 1) {
                    mView.onSucceed(str);
                } else {
                    ToastUtils.showLong(str.getMsg());
                }
            }
        });
        addSubscribe(s);
    }


    //	@Override
    //	public void getUserData(S) {
    //		Subscription subscription=mModel.userData(user,pass).subscribe(new Subscriber<UserItem>() {
    //
    //			@Override
    //			public void onStart() {
    //				super.onStart();
    //			}
    //
    //			@Override
    //			public void onCompleted() {
    //
    //			}
    //
    //			@Override
    //			public void onError(Throwable e) {
    //				mView.onFail(e.getMessage());
    //			}
    //
    //			@Override
    //			public void onNext(UserItem userItem) {
    //                    mView.onSucceedHand(userItem);
    //			}
    //		});
    //	}


}
