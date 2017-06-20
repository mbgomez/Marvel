package com.marvel.comicshop.ui.legal;


import com.marvel.comicshop.managers.NavigationManager;
import com.marvel.comicshop.navigation.BaseProjectNavRecord;
import com.marvel.comicshop.repositories.SharedPreferencesRepositoryImpl;
import com.marvel.comicshop.ui.base.BasePresenter;
import com.marvel.comicshop.utils.RxUtil;

import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by mbenitez
 */
public class LegalPresenter extends BasePresenter<LegalContract.View> implements LegalContract.Presenter {

    //Local variables
    private SharedPreferencesRepositoryImpl sharedPreferencesRepository;
    private NavigationManager navigationManager;

    /**
     * Constructor - init variables
     * @param sharedPreferencesRepository shared preferences repository
     * @param navigationManager navigation manager
     */
    public LegalPresenter(SharedPreferencesRepositoryImpl sharedPreferencesRepository,
                          NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
        this.sharedPreferencesRepository = sharedPreferencesRepository;
    }

    /**
     * Load main screen
     */
    @Override
    public void loadMain() {

        //Set legal visited to true in sharedpreferences and go to main screen
        DisposableCompletableObserver disposable = sharedPreferencesRepository.setLegalCompleted()
                .compose(RxUtil.applyCompletableSchedulers())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        //Go to main screen
                        BaseProjectNavRecord nextRecord = BaseProjectNavRecord.MAIN;
                        navigationManager.handleRecord(nextRecord);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Go to main screen
                        BaseProjectNavRecord nextRecord = BaseProjectNavRecord.MAIN;
                        navigationManager.handleRecord(nextRecord);
                    }
                });

        addSubscription(disposable);

    }

}
