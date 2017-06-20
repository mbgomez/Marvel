package com.marvel.comicshop.repositories;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by mbenitez
 */
public interface SharedPreferencesRepository {

    /**
     * Set legal activity visited to true
     * @return
     */
    Completable setLegalCompleted();

    /**
     * Get legal activity visited flag
     * @return true when It was visited
     */
    Single<Boolean> isLegalCompleted();


}
