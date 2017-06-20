package com.marvel.comicshop;

import android.app.Application;
import android.content.Context;

import com.marvel.comicshop.repositories.DatabaseRepository;
import com.marvel.comicshop.repositories.DatabaseRepositoryImpl;
import com.marvel.comicshop.repositories.SharedPreferencesRepositoryImpl;

/**
 * Created by mbenitez
 */
public class ComicShopApplication extends Application {

    //Local variables
    private static ComicShopApplication instance;
    private SharedPreferencesRepositoryImpl sharedPreferencesRepository;
    private DatabaseRepository databaseRepository;

    /**
     * get a instance of this class
     * @return instance of ths clas
     */
    public static ComicShopApplication getInstance() {
        //Created a new instance in case is not any created
        if (instance == null) {
            synchronized (ComicShopApplication.class) {
                if (instance == null) {
                    instance = new ComicShopApplication();
                }
            }
        }
        return instance;
    }

    /**
     * onCreate - init variables
     */
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        sharedPreferencesRepository = new SharedPreferencesRepositoryImpl(
                getSharedPreferences(SharedPreferencesRepositoryImpl.TAG, Context.MODE_PRIVATE)
        );

        //Set realm database
        setRealm();

    }
    /**
     * Set and init database
     */
    private void setRealm() {

        databaseRepository = new DatabaseRepositoryImpl(this);
        databaseRepository.initRealm();

    }


    /**
     * get shared preferences
     * @return shared preferences
     */
    public SharedPreferencesRepositoryImpl getSharedPreferencesRepository() {

        return sharedPreferencesRepository;
    }

    /**
     * get database
     * @return database
     */
    public DatabaseRepository getDatabaseRepository() {
        return databaseRepository;
    }

}
