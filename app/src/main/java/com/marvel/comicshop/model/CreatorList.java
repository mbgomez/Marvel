package com.marvel.comicshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mbenitez
 */
public class CreatorList implements Parcelable {

    //Json tags
    private static final String JSON_CREATOR_LIST_ITEMS = "items";


    //Local variables
    @SerializedName(JSON_CREATOR_LIST_ITEMS)
    @Expose
    private List<CreatorSummary> items;

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
     * Storing Creator List data to Parcel object
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelableArray((CreatorSummary[]) items.toArray(), flags);

    }

    /**
     * Retrieving Creator List data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     *
     * @param in
     */
    protected CreatorList(Parcel in) {

        Parcelable[] parcelableArray = in.readParcelableArray(PriceApi.class.getClassLoader());
        CreatorSummary[] creatorsArray = null;
        if (parcelableArray != null) {
            creatorsArray = Arrays.copyOf(parcelableArray, parcelableArray.length, CreatorSummary[].class);
            items = new ArrayList<>(Arrays.asList(creatorsArray));
        }

    }


    /**
     * Creator class of the parcelable
     */
    public static final Parcelable.Creator<CreatorList> CREATOR = new Parcelable.Creator<CreatorList>() {

        @Override
        public CreatorList createFromParcel(Parcel source) {

            return new CreatorList(source);
        }

        @Override
        public CreatorList[] newArray(int size) {
            return new CreatorList[size];
        }
    };


    /**
     * Get a list of authors
     * @return list of authors
     */
    public List<CreatorSummary> getItems() {
        return items;
    }

    //Debug
    @Override
    public String toString() {
        return "CreatorList{" +
                "items=" + items +
                '}';
    }
}
