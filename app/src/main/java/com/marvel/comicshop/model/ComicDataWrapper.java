package com.marvel.comicshop.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mbenitez
 */
public class ComicDataWrapper {

    //Json tags
    private static final String JSON_COMIC_DATA_WRAPPER_CODE = "code";
    private static final String JSON_COMIC_DATA_WRAPPER_STATUS = "status";
    private static final String JSON_COMIC_DATA_WRAPPER_DATA = "data";

    //Local variables
    @SerializedName(JSON_COMIC_DATA_WRAPPER_CODE)
    @Expose
    protected String code;
    @SerializedName(JSON_COMIC_DATA_WRAPPER_STATUS)
    @Expose
    protected String status;
    @SerializedName(JSON_COMIC_DATA_WRAPPER_DATA)
    @Expose
    protected ComicDataContainer data;

    /**
     * Get code response
     * @return code response
     */
    public String getCode() {
        return code;
    }

    /**
     * Get status response
     * @return status response
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get data received
     * @return data received
     */
    public ComicDataContainer getData() {
        return data;
    }

    //Debug
    @Override
    public String toString() {
        return "ComicDataWrapper{" +
                "code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
