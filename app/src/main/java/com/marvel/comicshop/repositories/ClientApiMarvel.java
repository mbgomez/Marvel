package com.marvel.comicshop.repositories;



import com.marvel.comicshop.repositories.retrofit.RXObserveOnCallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mbenitez
 */
public class ClientApiMarvel {

    //API addresses
    static final String API_WEBSERVER_HOST = "http://gateway.marvel.com/v1/public/";

    /**
     * Initialize retrofit
     * @return retrofit interface
     */
    public static ApiMarvel init() {

        //Create an http logging interceptor
        HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor();
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Create http client
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(mHttpLoggingInterceptor).build();

        //Set retrofit object
        Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_WEBSERVER_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RXObserveOnCallAdapterFactory.create(AndroidSchedulers.mainThread()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(mOkHttpClient)
                    .build();



        return mRetrofit.create(ApiMarvel.class);
    }
}
