package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.NodeCollectionCycle;
import com.colud.jctl.bean.OffOnItemManage;
import com.colud.jctl.mvp.contract.OffOnManageContract;
import com.colud.jctl.rx.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jcty on 2017/3/11.
 */

public class OffOnManageModel implements OffOnManageContract.Model {


    @Override
    public Observable<OffOnItemManage> getOffOnData(String nodeMac, String status) {
        return ApiEngine.getInstance().getApiService().getOffOnManage(nodeMac, status).compose(RxSchedulers.<OffOnItemManage>switchThread());
    }

    @Override
    public Observable<NodeCollectionCycle> setNodeCycle(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getNodeCollectionCycle(map).compose(RxSchedulers.<NodeCollectionCycle>switchThread());
    }

    @Override
    public Observable<OffOnItemManage> getHandlerResult(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getOffOnHandlerResult(map).compose(RxSchedulers.<OffOnItemManage>switchThread());
    }

    //	@Override
    //	public Observable<BazaarItem> getBazaarData(final String uid) {
    //		return ApiEngine.getInstance().getApiService().getBazaarData(uid).map(new Func1<BazaarItem, BazaarItem>() {
    //			@Override
    //			public BazaarItem call(BazaarItem bazaarItem) {
    //				List<BazaarDetailItem> data = (List<BazaarDetailItem>) getCache().getAsObject(KeyConstants.KAY_MARKET);
    //				String id = getCache().getAsString(KeyConstants.KAY_MARKET_ID);
    //				if (bazaarItem != null) {
    //					if (data != null && data.size() > 0 && uid.equals(id)) {
    //						return bazaarItem;
    //					} else {
    //						mList.clear();
    //						String jsonObject = GsonUtils.newInstance().toJson(bazaarItem.getMktDyns());
    //						//					NewInfoItem newInfoItem=(NewInfoItem) JctlApplication.getCache().getAsObject(KeyConstants.KAY_NEWINFO);
    //						if (!TextUtils.isEmpty(jsonObject)) {
    //							JsonParser parser = new JsonParser();
    //							JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
    //							for (JsonElement user : jsonArray) {
    //								//使用GSON，直接转成Bean对象
    //								BazaarDetailItem bazaar = GsonUtils.newInstance().fromJson(user, BazaarDetailItem.class);
    //								mList.add(bazaar);
    //							}
    //							getCache().put(KeyConstants.KAY_MARKET, (Serializable) mList);
    //							getCache().put(KeyConstants.KAY_MARKET_ID, uid);
    //						}
    //					}
    //
    //				}
    //				return bazaarItem;
    //			}
    //		}).compose(RxSchedulers.<BazaarItem>switchThread());
    //	}
}
