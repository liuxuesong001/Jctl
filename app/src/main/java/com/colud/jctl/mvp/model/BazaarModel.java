package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.BazaarItem;
import com.colud.jctl.mvp.contract.BazaarContract;
import com.colud.jctl.rx.RxSchedulers;

import rx.Observable;
import rx.functions.Func1;

/**
 *
 * Created by Jcty on 2017/3/11.
 */

public class BazaarModel implements BazaarContract.Model {


    @Override
    public Observable<BazaarItem> getBazaarData(final String uid) {
        return ApiEngine.getInstance().getApiService().getBazaarData(uid).map(new Func1<BazaarItem, BazaarItem>() {
            @Override
            public BazaarItem call(BazaarItem bazaarItem) {
                if (bazaarItem != null && bazaarItem.getMktDyns()!=null) {
                    return bazaarItem;
                    //                    mList.clear();
                    //                    String jsonObject = GsonUtils.newInstance().toJson(bazaarItem.getMktDyns());
                    //                    if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                    //                        JsonParser parser = new JsonParser();
                    //                        JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                    //                        for (JsonElement user : jsonArray) {
                    //                            //使用GSON，直接转成Bean对象
                    //                            BazaarItem.MktDynsBean bazaar = GsonUtils.newInstance().fromJson(user, BazaarItem.MktDynsBean.class);
                    //                            mList.add(bazaar);
                    //                        }
                    //                        bazaarItem.setMktDyns(mList);
                    //                    }
                }
                return null;
            }
        }).compose(RxSchedulers.<BazaarItem>switchThread());
    }
}
