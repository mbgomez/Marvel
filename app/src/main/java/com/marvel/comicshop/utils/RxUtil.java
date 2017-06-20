package com.marvel.comicshop.utils;

import io.reactivex.CompletableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mbenitez
 */
public class RxUtil {

    /**
     * Apply scheduler in the observable for a SingleTransformer
     * @param <T> Single Type
     * @return  observable
     */
    public static <T> SingleTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Apply scheduler in the observable for a CompletableTransformer
     * @return observable
     */
    public static CompletableTransformer applyCompletableSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
