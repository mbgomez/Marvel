package com.marvel.comicshop.repositories;


import com.marvel.comicshop.model.Comic;
import com.marvel.comicshop.model.ComicApi;

import java.util.List;

/**
 * Created by mbenitez
 */
public interface DatabaseRepository {

    /**
     * Init database
     */
    void initRealm();

    /**
     * Write a list of comics into comic table
     * @param dataList list of comics
     */
    void writeComicList(List<ComicApi> dataList);

    /**
     * Get a list of comics from comic table
     * @return list of comic
     */
    List<Comic> getComicList();

    /**
     * Delete all the rows of comic table.
     */
    void clearComicTable();



}
