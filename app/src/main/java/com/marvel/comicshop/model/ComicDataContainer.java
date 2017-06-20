package com.marvel.comicshop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mbenitez
 */
public class ComicDataContainer {

    //Json tags
    private static final String JSON_COMIC_DATA_CONTAINER_RESULTS = "results";

    //Local variables
    @SerializedName(JSON_COMIC_DATA_CONTAINER_RESULTS)
    @Expose
    protected List<ComicApi> results;

    /**
     * Get a list of comic
     * @return list of comic
     */
    public List<ComicApi> getResults() {
        return results;
    }

    //Debug
    @Override
    public String toString() {
        return "ComicDataContainer{" +
                "results=" + results +
                '}';
    }
}
