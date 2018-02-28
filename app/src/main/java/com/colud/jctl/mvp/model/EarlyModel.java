package com.colud.jctl.mvp.model;

import com.colud.jctl.bean.EarlyItme;
import com.colud.jctl.mvp.contract.EarlyContract;

import rx.Observable;

/**
 * Created by Jcty on 2017/3/11.
 */

public class EarlyModel implements EarlyContract.Model {


    @Override
    public Observable<EarlyItme> setEarlyData(String uid) {
        return null;
    }


    //	@Override
    //	public Observable<EarlyItme> setEarlyData(String uid) {
    //		return ApiEngine.getInstance().getApiService().getEarlyData(uid).map(new Func1<EarlyItme, EarlyItme>() {
    //			@Override
    //			public EarlyItme call(EarlyItme earlyItme) {
    //				if (earlyItme.getFlag()==1){
    //					mList.clear();
    //					String jsonObject = GsonUtils.newInstance().toJson(earlyItme.getInfo());
    //					if (jsonObject!=null&&!"null".equals(jsonObject)&&!TextUtils.isEmpty(jsonObject)) {
    //						JsonParser parser = new JsonParser();
    //						JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
    //						for (JsonElement user : jsonArray) {
    //							//使用GSON，直接转成Bean对象
    //							EarlyItme.InfoBean early = GsonUtils.newInstance().fromJson(user, EarlyItme.InfoBean.class);
    //							mList.add(early);
    //							earlyItme.setInfoBean(early);
    //						}
    //					}
    //					earlyItme.setInfo(mList);
    //					//					KLog.json(jsonObject);
    //				}
    //				return earlyItme;
    //			}
    //		}).compose(RxSchedulers.<EarlyItme>switchThread());
    //	}
}
