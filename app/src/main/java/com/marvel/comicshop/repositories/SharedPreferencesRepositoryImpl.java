package com.marvel.comicshop.repositories;

import android.content.SharedPreferences;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by mbenitez
 */
public class SharedPreferencesRepositoryImpl implements SharedPreferencesRepository {

    //Local variables.
    public static final String TAG = SharedPreferencesRepositoryImpl.class.getSimpleName();
    private static final String KEY_LEGAL_COMPLETED = "LEGAL_COMPLETED";

    /**
     * The Shared preferences.
     */
    private final SharedPreferences sharedPreferences;

    /**
     * Instantiates a new Shared preferences repository.
     *
     * @param sharedPreferences the shared preferences
     */
    public SharedPreferencesRepositoryImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }


    /**
     * Set legal activity visited to true
     * @return
     */
    @Override
    public Completable setLegalCompleted() {
        return Completable.fromAction(() -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_LEGAL_COMPLETED, true);
            editor.apply();
        });
    }

    /**
     * Get legal activity visited flag
     * @return true when It was visited
     */
    @Override
    public Single<Boolean> isLegalCompleted() {
        return Single.just(sharedPreferences.getBoolean(KEY_LEGAL_COMPLETED, false));
    }


}
