package com.marvel.comicshop.ui.main;

import android.content.Intent;

import com.marvel.comicshop.managers.NavigationManager;
import com.marvel.comicshop.model.Comic;
import com.marvel.comicshop.model.ComicApi;
import com.marvel.comicshop.model.ComicDataWrapper;
import com.marvel.comicshop.navigation.BaseProjectNavRecord;
import com.marvel.comicshop.repositories.ApiMarvel;
import com.marvel.comicshop.repositories.ClientApiMarvel;
import com.marvel.comicshop.repositories.DatabaseRepository;
import com.marvel.comicshop.ui.base.BasePresenter;
import com.marvel.comicshop.utils.LogJam;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by mbenitez
 */
public class ComicsPresenter extends BasePresenter<ComicsContract.View> implements ComicsContract.Presenter {

    //Local variables
    private NavigationManager navigationManager;
    private ApiMarvel apiBaseProject;
    private DatabaseRepository databaseRepository;

    /**
     * Constructor - init variables
     * @param navigationManager navigation manager
     */
    public ComicsPresenter(NavigationManager navigationManager, DatabaseRepository databaseRepository) {

        this.navigationManager = navigationManager;
        this.apiBaseProject = ClientApiMarvel.init();
        this.databaseRepository = databaseRepository;

    }

    /**
     * Get comics from the webserver
     */
    @Override
    public void getComicsAPI() {
        DisposableObserver<ComicDataWrapper> disposable = apiBaseProject.getComics()
                .subscribeWith(new DisposableObserver<ComicDataWrapper>() {
                    @Override
                    public void onNext(ComicDataWrapper dataWrapper) {
                        LogJam.d(this, "onNext");
                        //save data into the database
                        saveDataDatabase(dataWrapper.getData().getResults());
                        //Show received data
                        view.showComics(dataWrapper.getData().getResults());
                    }
                    @Override
                    public void onError(Throwable throwable) {

                        view.showComics(getComicsFromDatabase());
                        throwable.printStackTrace();
                        LogJam.d(this, "onError " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogJam.d(this, "onComplete");
                    }
                });
        addSubscription(disposable);




    }

    /**
     * Get a list of comics from the database
     * @return list of comics
     */
    private List<Comic> getComicsFromDatabase() {

        return  databaseRepository.getComicList();

    }

    /**
     * Save a list of data into the database
     * @param dataList list of data
     */
    private void saveDataDatabase(List dataList) {

        databaseRepository.writeComicList(dataList);

    }

    /**
     * Load FAQs screen
     */
    @Override
    public void loadHelp() {

        BaseProjectNavRecord nextRecord = BaseProjectNavRecord.HELP;
        nextRecord.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        navigationManager.handleRecord(nextRecord);

    }

    /**
     * Load terms and conditions screen
     */
    @Override
    public void loadLegal() {

        BaseProjectNavRecord nextRecord = BaseProjectNavRecord.LEGAL_MENU;
        nextRecord.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        navigationManager.handleRecord(nextRecord);

    }
}
