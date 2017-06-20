package com.marvel.comicshop.repositories;



import com.marvel.comicshop.model.ComicApi;
import com.marvel.comicshop.model.ComicDataWrapper;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by mbenitez
 */
public interface ApiMarvel {

    //http://gateway.marvel.com/v1/public/comics?format=comic&formatType=comic&limit=100&apikey=54306733de0f5cd1418aa05a85fa062a&hash=359e14db6b6a7bed5c31d81b2c00f36b&ts=1

    //API addresses
    static final String API_WEBSERVER_DATA = "comics?format=comic&formatType=comic&limit=100&apikey=54306733de0f5cd1418aa05a85fa062a&hash=359e14db6b6a7bed5c31d81b2c00f36b&ts=1";

    /**
     * Get data from webserver
     * @return list of data
     */
    @GET(API_WEBSERVER_DATA)
    Observable<ComicDataWrapper> getComics();

    //Post example with Body
    /*
    @POST("data")
    Observable<Void> uploadData(@Body Data request);
    */
}
