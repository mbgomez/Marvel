package com.marvel.comicshop.ui.splash;

import android.content.Intent;

import com.marvel.comicshop.managers.NavigationManager;
import com.marvel.comicshop.navigation.BaseProjectNavRecord;
import com.marvel.comicshop.repositories.SharedPreferencesRepository;
import com.marvel.comicshop.ui.base.BasePresenter;
import com.marvel.comicshop.utils.RxUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mbenitez
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    //Local variables
    private static final long SPLASH_SCREEN_WAIT = 2;
    private final SharedPreferencesRepository sharedPreferencesRepository;
    private NavigationManager navigationManager;

    /**
     * Constructor - init variables and select next screen
     * @param sharedPreferencesRepository shared preferences
     * @param navigationManager navigation manager
     */
    protected SplashPresenter(SharedPreferencesRepository sharedPreferencesRepository,
                              NavigationManager navigationManager) {
        this.sharedPreferencesRepository = sharedPreferencesRepository;
        this.navigationManager = navigationManager;

        //Select next screen by check if term and conditions were accepted
        isLegalComplete();

    }

    /**
     * Select next screen by check if term and conditions were accepted
     */
    @Override
    public void isLegalComplete() {
        //Check if term and conditions were accepted
        sharedPreferencesRepository.isLegalCompleted()
                .delaySubscription(SPLASH_SCREEN_WAIT, TimeUnit.SECONDS, Schedulers.io())
                .compose(RxUtil.applySchedulers())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onSuccess(Boolean isLegalComplete) {
                        if (isLegalComplete) {
                            //Load main screen
                            loadMain();
                        } else {
                            //Load term and conditions screen
                            loadLegal();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Load term and conditions screen
                        loadLegal();
                    }
                });

    }

    /**
     * Load main screen
     */
    @Override
    public void loadMain() {

        BaseProjectNavRecord nextRecord = BaseProjectNavRecord.MAIN;
        navigationManager.handleRecord(nextRecord);

    }

    /**
     * Load terms and conditions screen
     */
    @Override
    public void loadLegal() {

        BaseProjectNavRecord nextRecord = BaseProjectNavRecord.LEGAL;
        nextRecord.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        navigationManager.handleRecord(nextRecord);

    }


}
