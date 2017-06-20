package com.marvel.comicshop.ui.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.marvel.comicshop.R;
import com.marvel.comicshop.model.Comic;
import com.marvel.comicshop.model.ImageApi;
import com.marvel.comicshop.utils.LogJam;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mbenitez
 */
public class DataRecyclerAdapter extends RecyclerView.Adapter<DataRecyclerAdapter.DataHolder> {

    //Local variables
    private List<Comic> comicList;

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_data, parent, false);

        return new DataHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(DataHolder holder, int position) {

        View view = holder.itemView;

        String imageURL = comicList.get(position).getThumbnail(ImageApi.IMAGE_PORTRAIT_MEDIUM);

        LogJam.d(this, "imageURL:" + imageURL);

        if (imageURL == null || imageURL.isEmpty() || imageURL.contains("image_not_available")) {
            Glide.with(view.getContext()).load(R.mipmap.ic_launcher).into(holder.photoIV);
        } else {
            Glide.with(view.getContext()).load(imageURL).into(holder.photoIV);
        }
        holder.data2Tv.setText(comicList.get(position).getTitle());
    }


    /**
     * Returns the total number of items in the data set held by the adapter
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return comicList.size();
    }

    /**
     * Set the comic list to be shown
     * @param comicList comic list
     */
    public void setComicList(List<Comic> comicList) {

        this.comicList = comicList;

    }


    /**
     * View Holder of the Adapter
     */
    class DataHolder extends RecyclerView.ViewHolder {

        //Local views
        @BindView(R.id.item_view_data_photo_iv)
        ImageView photoIV;
        @BindView(R.id.item_view_data_data2_tv)
        TextView data2Tv;

        /**
         * Constructor - init
         * @param itemView - view
         */
        DataHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
