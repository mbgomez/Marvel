package com.marvel.comicshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mbenitez
 */
public class ImageApi implements Parcelable {

    //Json tags
    private static final String JSON_IMAGE_API_RESULTS = "path";
    private static final String JSON_IMAGE_API_EXTENSION = "extension";

    //Photo sizes
    public static final String IMAGE_PORTRAIT_SMALL = "/portrait_small";
    public static final String IMAGE_PORTRAIT_MEDIUM = "/portrait_medium";
    public static final String IMAGE_PORTRAIT_LARGE = "/portrait_xlarge";

    //Local variables
    @SerializedName(JSON_IMAGE_API_RESULTS)
    @Expose
    private String path;

    @SerializedName(JSON_IMAGE_API_EXTENSION)
    @Expose
    private String extension;

    /**
     * Describe the kinds of special objects contained in this Parcelable instance's marshaled representation.
     *
     * @return int
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Storing Image data to Parcel object
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(path);
        dest.writeString(extension);

    }

    /**
     * Retrieving Image data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     *
     * @param in
     */
    protected ImageApi(Parcel in) {

        this.path = in.readString();
        this.extension = in.readString();

    }


    /**
     * Creator class of the parcelable
     */
    public static final Creator<ImageApi> CREATOR = new Creator<ImageApi>() {

        @Override
        public ImageApi createFromParcel(Parcel source) {

            return new ImageApi(source);
        }

        @Override
        public ImageApi[] newArray(int size) {
            return new ImageApi[size];
        }
    };

    /**
     * Get path of the image
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * Get extension of the image
     * @return
     */
    public String getExtension() {
        return extension;
    }

    //Debug
    @Override
    public String toString() {
        return "ImageApi{" +
                "path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
