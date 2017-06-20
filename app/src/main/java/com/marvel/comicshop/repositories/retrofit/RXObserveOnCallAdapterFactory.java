package com.marvel.comicshop.repositories.retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * Created by mbenitez
 */
public class RXObserveOnCallAdapterFactory extends CallAdapter.Factory {

    //Local variable
    private Scheduler scheduler;

    /**
     * Constructor - init
      * @param scheduler
     */
    private RXObserveOnCallAdapterFactory(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * Get a call adapter for an observable
     * @param returnType class type
     * @param annotations annotation
     * @param retrofit retrofit
     * @return
     */
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != Observable.class) {
            return null;
        }
        final CallAdapter<Object, Observable<?>> delegate =
                (CallAdapter<Object, Observable<?>>) retrofit.nextCallAdapter(this, returnType,
                        annotations);
        return new CallAdapter<Object, Observable<?>>() {
            @Override
            public Type responseType() {
                return delegate.responseType();
            }

            @Override
            public Observable<?> adapt(Call<Object> call) {
                Observable<?> o = delegate.adapt(call);
                return o.observeOn(scheduler);
            }
        };
    }

    /**
     * Create a call adapter for a given scheduler
     * @param scheduler
     * @return
     */
    public static CallAdapter.Factory create(Scheduler scheduler) {
        return new RXObserveOnCallAdapterFactory(scheduler);
    }
}
