package com.colud.jctl.mvp.model;


import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.bean.SplashBnBean;
import com.colud.jctl.bean.UserItem;
import com.colud.jctl.bean.VersionUpdateBean;
import com.colud.jctl.mvp.contract.HomeContract;
import com.colud.jctl.rx.RxSchedulers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

import static com.colud.jctl.api.KeyConstants.KAY_BANENRURl;

/**
 * Created by Jcty on 2017/3/11.
 */

public class HomeModel implements HomeContract.Model {


    @Override
    public Observable<SplashBnBean> getBannerNew() {
        return ApiEngine.getInstance().getApiService()
                .getSplashBannerNew().map(new Func1<SplashBnBean, SplashBnBean>() {
                    @Override
                    public SplashBnBean call(SplashBnBean splashBnBean) {
                        if (splashBnBean != null && splashBnBean.getFlag() == 1) {

                            if (splashBnBean.getBanner() != null && splashBnBean.getBanner().size() > 0) {
                                SplashBnBean.BannerBean sp = new SplashBnBean.BannerBean();
                                for (int i =0 ;i<splashBnBean.getBanner().size();i++)
                                {
                                    sp = splashBnBean.getBanner().get(i);
                                }
                                if (sp.getUrl()!=null && sp.getPath()!=null)
                                {
                                    List<String> banls = new ArrayList<String>();
                                    List<String>urls = new ArrayList<String>();

                                    for (SplashBnBean.BannerBean banners:splashBnBean.getBanner())
                                    {
                                        banls.add(banners.getPath());
                                    }

                                    for (SplashBnBean.BannerBean url : splashBnBean.getBanner() )
                                    {
                                        urls.add(url.getUrl());
                                    }

                                    splashBnBean.setBanners(banls);
                                    splashBnBean.setUrls(urls);

                                    JctlApplication.getCache().put(KAY_BANENRURl, splashBnBean);
                                }
                            }
                            JctlApplication.getCache().remove(KeyConstants.KAY_NEWINFO);
                            if (splashBnBean.getNews() != null && splashBnBean.getNews().size() > 0) {
                                JctlApplication.getCache().put(KeyConstants.KAY_NEWINFO, (Serializable) splashBnBean.getNews());

                            }

                        }
                        return splashBnBean;
                    }
                }).compose(RxSchedulers.<SplashBnBean>switchThread());
    }

    @Override
    public Observable<UserItem> getHomeData(Map<String,String>map) {
        return ApiEngine.getInstance().getApiService()
                .loginSingleId(
                        map
                )
//                .map(new Func1<UserItem, UserItem>() {
//                    @Override
//                    public UserItem call(UserItem userItem) {
//                        if (userItem != null && userItem.getFlag() == 0) {
//                            JctlApplication.getCache().remove(KeyConstants.USER_ITEM);
//                        }
//                        return userItem;
//                    }
//                })
                .compose(RxSchedulers.<UserItem>switchThread());
    }

    @Override
    public Observable<UserItem> getRegLogin(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().loginReg(
                map
        )
                .compose(RxSchedulers.<UserItem>switchThread());
    }


    @Override
    public Observable<VersionUpdateBean> getUpdateV(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getVeesionUpdate(map).compose(RxSchedulers.<VersionUpdateBean>switchThread());
    }

}
