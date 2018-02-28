package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.IconItem;
import com.colud.jctl.mvp.contract.IconContract;
import com.colud.jctl.rx.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jcty on 2017/3/11.
 */

public class IconModel implements IconContract.Model {
    @Override
    public Observable<IconItem> getIconData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().uploadImage(map).compose(RxSchedulers.<IconItem>switchThread());

        //	@Override
        //	public Observable<ResponseBody> getIconData(String data) {
        //		File file=new File(data);
        //
        //		ResponseBody body=ResponseBody.create(MediaType.parse("image/jpg"),file);
        //
        //		MultipartBody.Part =MultipartBody.Part.createFormData("picture",file.getAlias(),body);
        //
        //
        //		return ApiEngine.getInstance().getApiService().uploadImage(body);
        //	}


        //	@Override
        //	public Observable<IconItem> getIconData(IconItem file) {
        //创建RequwstBody对象
        //		RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        //		RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //
        //		MultipartBody.Builder builder = new MultipartBody.Builder();
        //		builder.setLandType(MultipartBody.FORM);
        //		builder.addFormDataPart("file", file.getAlias(), requestBody);
        //		MultipartBody body=builder.build();//调用即可


        //		return ApiEngine.getInstance().getApiService().uploadImage("icon",requestBody).subscribeOn(Schedulers.io());
        //		return ApiEngine.getInstance().getApiService().uploadImage("file",body).subscribeOn(Schedulers.io());
        //		return ApiEngine.getInstance().getApiService().uploadImage(file.getBase64(),"png").map(new Func1<IconItem, IconItem>() {
        //			@Override
        //			public String call(String s) {
        //				if (!TextUtils.isEmpty(s)){
        //					JctlApplication.getCache().put(KeyConstants.KAY_ICON,s);
        //				}
        //				return s;
        //			}
        //		}).compose(RxSchedulers.<IconItem>switchThread());

    }
}
