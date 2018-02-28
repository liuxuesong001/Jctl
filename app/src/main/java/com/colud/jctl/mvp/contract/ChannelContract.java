package com.colud.jctl.mvp.contract;


import com.colud.jctl.base.BaseModel;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.base.BaseView;
import com.colud.jctl.bean.ChannelsBean;

import java.util.Map;

import rx.Observable;


/**
 * 契约类
 * Created by Jcty on 2017/3/11.
 */

public interface ChannelContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(ChannelsBean data);

        void onSucceedPTZ(ChannelsBean data);

        void onFailure(String err, Throwable e);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<ChannelsBean> getChannelData(Map<String, Object> map);

        Observable<ChannelsBean> getPTZ(Map<String, Object> map);

    }

    abstract class Presenter extends BasePesenter<View, Model> {

        public abstract void setChannelData(Map<String, Object> map);

        public abstract void setPTZ(Map<String, Object> map);

    }
}
