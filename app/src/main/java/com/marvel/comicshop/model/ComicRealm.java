package com.marvel.comicshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mbenitez
 */
public class ComicRealm extends RealmObject implements Comic {

    //Columns names
    @PrimaryKey
    private Integer id;
    private String title;
    private String thumbnail;
    private Float price;
    private String description;
    private Integer pageNumber;
    private RealmList<AuthorRealm> authors;


    public ComicRealm() {
        //empty constructor need it
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
     * Storing Comic Realm data to Parcel object
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(title);
        dest.writeString(thumbnail);
        dest.writeFloat(price);
        dest.writeString(description);
        dest.writeInt(pageNumber);

        List<AuthorRealm> auxList = new ArrayList<>();
        for (int i = 0; i < authors.size(); i++) {

            auxList.add(authors.get(i));

        }
        dest.writeParcelableArray((AuthorRealm []) auxList.toArray(), flags);

    }

    /**
     * Retrieving Comic Realm data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     *
     * @param in
     */
    protected ComicRealm(Parcel in) {

        this.title = in.readString();
        this.thumbnail = in.readString();
        this.price = in.readFloat();
        this.description = in.readString();
        this.pageNumber = in.readInt();

        Parcelable[] parcelableArray = in.readParcelableArray(AuthorRealm.class.getClassLoader());
        AuthorRealm[] authorsArray = null;
        if (parcelableArray != null) {
            authorsArray = Arrays.copyOf(parcelableArray, parcelableArray.length, AuthorRealm[].class);
            ArrayList<AuthorRealm> authorList = new ArrayList<>(Arrays.asList(authorsArray));
            RealmList<AuthorRealm> authorRealmList = new RealmList<>();
            for (int i = 0; i < authorList.size(); i++) {

                authorRealmList.add(authorList.get(i));

            }
            this.authors = authorRealmList;


        }


    }


    /**
     * Creator class of the parcelable
     */
    public static final Creator<AuthorRealm> CREATOR = new Creator<AuthorRealm>() {

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
     * Get comic title
     * @return
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Get thumbnail url
     * @param type size of the thumbnail
     * @return thumbnail url
     */
    @Override
    public String getThumbnail(String type) {

        int divider = thumbnail.lastIndexOf(".");
        String thumbnailPart1 = thumbnail.substring(0, divider);
        String thumbnailPart2 = thumbnail.substring(divider + 1);


        return thumbnailPart1 + type + "." +  thumbnailPart2;

    }

    /**
     * Get comic prize
     * @return comic prize
     */
    @Override
    public Float getPrice() {
        return price;
    }

    /**
     * Get description
     * @return description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Get pages number
     * @return pages number
     */
    @Override
    public Integer getPagesNumber() {
        return pageNumber;
    }

    /**
     * Get authors list
     * @return author list
     */
    @Override
    public List<String> getAuthors() {

        List<String> authorList = new ArrayList<>();

        for (int i = 0; i < authorList.size(); i++) {

            authorList.add(authors.get(i).getRole() + ": " + authors.get(i).getName());

        }

        return authorList;

    }

    /**
     * Set comic id
     * @param id comic id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Set comic title
     * @param title comic title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set thumbnail url
     * @param thumbnail thumbnail url
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * Set comic price
     * @param price comic price
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * Set comic description
     * @param description comic description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set number of page of the comic
     * @param pageNumber number of pages of the comic
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Set authors of the comic
     * @param authors authors of the comic
     */
    public void setAuthors(RealmList<AuthorRealm> authors) {

        this.authors = authors;

    }
}
