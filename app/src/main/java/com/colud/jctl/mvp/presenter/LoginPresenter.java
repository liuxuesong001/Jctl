package com.colud.jctl.mvp.presenter;

import com.colud.jctl.bean.UserItem;
import com.colud.jctl.mvp.contract.LoginContract;
import com.colud.jctl.mvp.model.LoginModel;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;

/**
 * 登录页
 * Created by Jcty on 2017/3/11.
 */

public class LoginPresenter extends LoginContract.Presenter {


    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mModel = new LoginModel();
    }

    @Override
    public void getUserData(Map<String,String> map) {
        Subscription s = mModel.userData(map).subscribe(new Subscriber<UserItem>() {

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
            public void onNext(UserItem userItem) {
                mView.onSucceed(userItem);
            }
        });
        addSubscribe(s);
    }



    //	@Override
    //	public void getWeatherData() {
    //		Subscription subscription=mModel.getWeatherData().subscribe(new Subscriber<Weather>() {
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
    //				mView.onLocatedFail(null);
    //			}
    //
    //			@Override
    //			public void onNext(Weather weatherData) {
    //				//				mView.onSucceedData(weatherData);
    //			}
    //		});
    //	}


}
