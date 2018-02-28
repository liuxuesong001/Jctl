package com.colud.jctl.rx.rxbus;

import rx.Subscriber;

/**
 * @package com.colud.jctl.rx.rxbus
 * @FileName RxBusSubscriber
 * @date or 2017/12/6  9:55
 * @Developer 刘雪松
 * @emial liuxuesong001@gmail.com
 */

public abstract class RxBusSubscriber<T> extends Subscriber<T> {

    @Override
    public void onNext(T t) {
        try {
            onEvent(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    protected abstract void onEvent(T t);
}
