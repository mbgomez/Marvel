package com.marvel.comicshop.model;

import android.os.Parcelable;

import java.util.List;

/**
 * Created by mbenitez
 */
public interface Comic extends Parcelable {

    /**
     * Get comic title
     * @return comic title
     */
    String getTitle();

    /**
     * Get comic thumbnail url
     * @param type size of the thumbnaiil
     * @return comic thumbnail url
     */
    String getThumbnail(String type);

    /**
     * Get comic price
     * @return comic price
     */
    Float getPrice();

    /**
     * Get comic description
     * @return comic description
     */
    String getDescription();

    /**
     * Get number of pages of the comic
     * @return  number of pages of the comic
     */
    Integer getPagesNumber();

    /**
     * Get a list of comic's authors
     * @return list of comic's authors
     */
    List<String> getAuthors();

}
