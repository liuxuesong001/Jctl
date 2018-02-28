package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.UserItem;
import com.colud.jctl.mvp.contract.LoginContract;
import com.colud.jctl.rx.RxSchedulers;

import java.util.Map;

import rx.Observable;


/**
 * Created by Jcty on 2017/3/11.
 */

public class LoginModel implements LoginContract.Model {


    @Override
    public Observable<UserItem> userData(Map<String,String> map) {
        return ApiEngine.getInstance().getApiService().login(
                map
        ).compose(RxSchedulers.<UserItem>switchThread());
    }
}
