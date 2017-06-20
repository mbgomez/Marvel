package com.marvel.comicshop.model;


import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by mbenitez
 */
public class AuthorRealm extends RealmObject implements Parcelable {


    //Columns names
    private String name;
    private String role;

    public AuthorRealm() {
        //empty constructor
    }

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
     * Storing Author realm data to Parcel object
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
     * Retrieving Author realm data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     *
     * @param in
     */
    protected AuthorRealm(Parcel in) {

        this.name = in.readString();
        this.role = in.readString();

    }


    /**
     * Creator class of the parcelable
     */
    public static final Parcelable.Creator<AuthorRealm> CREATOR = new Parcelable.Creator<AuthorRealm>() {

        @Override
        public AuthorRealm createFromParcel(Parcel source) {

            return new AuthorRealm(source);
        }

        @Override
        public AuthorRealm[] newArray(int size) {
            return new AuthorRealm[size];
        }
    };

    /**
     * Get Author name
     * @return author name
     */
    public String getName() {
        return name;
    }

    /**
     * Set author name
     * @param name author name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get author role
     * @return author role
     */
    public String getRole() {
        return role;
    }

    /**
     * Set author role
     * @param role author role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
