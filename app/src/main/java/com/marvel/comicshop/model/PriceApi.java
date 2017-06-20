package com.marvel.comicshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mbenitez
 */
public class PriceApi implements Parcelable {

    //Json tags
    private static final String JSON_PRICE_API_TYPE = "type";
    private static final String JSON_PRICE_API_PRICE = "price";



    //Local variables
    @SerializedName(JSON_PRICE_API_TYPE)
    @Expose
    private String type;

    @SerializedName(JSON_PRICE_API_PRICE)
    @Expose
    private Float price;

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
     * Storing Price data to Parcel object
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(type);
        dest.writeFloat(price);

    }

    /**
     * Retrieving Price data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     *
     * @param in
     */
    protected PriceApi(Parcel in) {

        this.type = in.readString();
        this.price = in.readFloat();

    }


    /**
     * Creator class of the parcelable
     */
    public static final Parcelable.Creator<PriceApi> CREATOR = new Parcelable.Creator<PriceApi>() {

        @Override
        public PriceApi createFromParcel(Parcel source) {

            return new PriceApi(source);
        }

        @Override
        public PriceApi[] newArray(int size) {
            return new PriceApi[size];
        }
    };

    /**
     * Get price type
     * @return price type
     */
    public String getType() {
        return type;
    }

    /**
     * Get price comic
     * @return price comic
     */
    public Float getPrice() {
        return price;
    }

    //Debug
    @Override
    public String toString() {
        return "PriceApi{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
