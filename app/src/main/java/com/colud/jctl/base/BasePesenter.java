package com.colud.jctl.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jcty on 2017/3/1.
 */

public class BasePesenter<V extends BaseView, M extends BaseModel> {

    protected V mView;
    protected M mModel;

    private CompositeSubscription mCompositeSubscription;

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    /**
     * 取消RxJava 注册，避免内存泄漏
     */
    public void unSubscribe() {
        if (mView != null) {
            mView = null;
        }
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
            mCompositeSubscription.unsubscribe();
        }
    }

}
