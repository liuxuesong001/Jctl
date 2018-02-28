package com.colud.jctl.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nicholas on 2016/11/2.
 */

public class RxSchedulers {

    /**
     * RxJava2.0
     */
    //	private RxSchedulers(){};
    //
    //
    //		public static <T>ObservableTransformer<T,T> switchThread(){
    //			return new ObservableTransformer<T, T>() {
    //				@Override
    //				public ObservableSource<T> apply(Observable<T> upstream) {
    //					return upstream
    //							.subscribeOn(Schedulers.io())
    //							.unsubscribeOn(Schedulers.io())
    //							.observeOn(AndroidSchedulers.mainThread());
    //				}
    //			};
    //		}


    /**
     * RxJava 1.0
     *
     * @param <T>
     * @return
     */
    public static <T> rx.Observable.Transformer<T, T> switchThread() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())
                        .subscribeOn(Schedulers.newThread())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
