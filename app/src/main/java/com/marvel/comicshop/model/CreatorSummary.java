package com.marvel.comicshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mbenitez
 */
public class CreatorSummary implements Parcelable {

    //Json tags
    private static final String JSON_CREATOR_SUMMARY_NAME = "name";
    private static final String JSON_CREATOR_SUMMARY_ROLE = "role";


    //Local variables
    @SerializedName(JSON_CREATOR_SUMMARY_NAME)
    @Expose
    private String name;
    @SerializedName(JSON_CREATOR_SUMMARY_ROLE)
    @Expose
    private String role;

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
     * Storing Creator Summary data to Parcel object
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(role);

    }

    /**
     * Retrieving CreatorSummary data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     *
     * @param in
     */
    protected CreatorSummary(Parcel in) {

        this.name = in.readString();
        this.role = in.readString();

    }


    /**
     * Creator class of the parcelable
     */
    public static final Parcelable.Creator<CreatorSummary> CREATOR = new Parcelable.Creator<CreatorSummary>() {

        @Override
        public CreatorSummary createFromParcel(Parcel source) {

            return new CreatorSummary(source);
        }

        @Override
        public CreatorSummary[] newArray(int size) {
            return new CreatorSummary[size];
        }
    };

    /**
     * Get author name
     * @return author name
     */
    public String getName() {
        return name;
    }

    /**
     * Get author role
     * @return author role
     */
    public String getRole() {
        return role;
    }

    //Debug
    @Override
    public String toString() {
        return "CreatorSummary{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
