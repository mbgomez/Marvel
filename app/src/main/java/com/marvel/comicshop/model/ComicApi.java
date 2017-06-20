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
public class ComicApi implements Comic {

    //Json tags
    private static final String JSON_COMIC_API_ID = "id";
    private static final String JSON_COMIC_API_TITLE = "title";
    private static final String JSON_COMIC_API_DESCRIPTION = "description";
    private static final String JSON_COMIC_API_PAGE_COUNT = "pageCount";
    private static final String JSON_COMIC_API_THUMBNAIL = "thumbnail";
    private static final String JSON_COMIC_API_PRICES = "prices";
    private static final String JSON_COMIC_API_CREATORS = "creators";

    //Local variables
    @SerializedName(JSON_COMIC_API_ID)
    @Expose
    protected String id;
    @SerializedName(JSON_COMIC_API_TITLE)
    @Expose
    protected String title;
    @SerializedName(JSON_COMIC_API_DESCRIPTION)
    @Expose
    protected String description;
    @SerializedName(JSON_COMIC_API_PAGE_COUNT)
    @Expose
    protected Integer pageCount;
    @SerializedName(JSON_COMIC_API_THUMBNAIL)
    @Expose
    protected ImageApi thumbnail;
    @SerializedName(JSON_COMIC_API_PRICES)
    @Expose
    protected List<PriceApi> prices;

    @SerializedName(JSON_COMIC_API_CREATORS)
    @Expose
    protected CreatorList creators;

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
     * Storing ComicApi data to Parcel object
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(pageCount);
        dest.writeParcelable(thumbnail, flags);
        dest.writeParcelableArray((PriceApi[]) prices.toArray(), flags);
        dest.writeParcelable(creators, flags);

    }

    /**
     * Retrieving ComicApi data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     *
     * @param in
     */
    protected ComicApi(Parcel in) {

        this.title = in.readString();
        this.description = in.readString();
        this.pageCount = in.readInt();
        this.thumbnail = in.readParcelable(ImageApi.class.getClassLoader());
        Parcelable[] parcelableArray = in.readParcelableArray(PriceApi.class.getClassLoader());
        PriceApi[] pricesArray = null;
        if (parcelableArray != null) {
            pricesArray = Arrays.copyOf(parcelableArray, parcelableArray.length, PriceApi[].class);
            prices = new ArrayList<>(Arrays.asList(pricesArray));
        }
        this.creators = in.readParcelable(CreatorList.class.getClassLoader());

    }


    /**
     * Creator class of the parcelable
     */
    public static final Creator<ComicApi> CREATOR = new Creator<ComicApi>() {

        @Override
        public ComicApi createFromParcel(Parcel source) {

            return new ComicApi(source);
        }

        @Override
        public ComicApi[] newArray(int size) {
            return new ComicApi[size];
        }
    };

    /**
     * get comic uuid
     * @return comic uuid
     */
    public String getId() {
        return id;
    }

    /**
     * Get comic title
     * @return comic title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Get comic thumbnail url
     * @param type size of the thumbnaiil
     * @return comic thumbnail url
     */
    @Override
    public String getThumbnail(String type) {

        return thumbnail.getPath() + type + "." + thumbnail.getExtension();
    }

    /**
     * Get comic price
     * @return comic price
     */
    @Override
    public Float getPrice() {

        if (prices != null && prices.size() > 0) {
            return prices.get(0).getPrice();
        } else {

            return 0.0f;

        }
    }

    /**
     * Get comic description
     * @return comic description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Get number of pages of the comic
     * @return  number of pages of the comic
     */
    @Override
    public Integer getPagesNumber() {
        return pageCount;
    }

    /**
     * Get a list of comic's authors
     * @return list of comic's authors
     */
    @Override
    public List<String> getAuthors() {

        List<String> authorList = new ArrayList<>();
        List<CreatorSummary> creatorsList = creators.getItems();

        for (int i = 0; i < creatorsList.size(); i++) {

            authorList.add(creatorsList.get(i).getRole() + ": " + creatorsList.get(i).getName());

        }

        return authorList;
    }

    public CreatorList getCreators() {

        return creators;

    }

    public String getThumbnail() {

        return thumbnail.getPath() + "." + thumbnail.getExtension();

    }

    //Debug

    @Override
    public String toString() {
        return "ComicApi{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", pageCount=" + pageCount +
                ", thumbnail=" + thumbnail +
                ", prices=" + prices +
                ", creators=" + creators +
                '}';
    }
}
