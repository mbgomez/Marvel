package com.marvel.comicshop.ui.main;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.marvel.comicshop.ComicShopApplication;
import com.marvel.comicshop.R;
import com.marvel.comicshop.managers.NavigationManager;
import com.marvel.comicshop.ui.base.BaseActivity;
import com.marvel.comicshop.ui.main.adapters.DataRecyclerAdapter;
import com.marvel.comicshop.utils.LogJam;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mbenitez
 */
public class ComicsActivity extends BaseActivity implements ComicsContract.View {

    //Local variables
    private ComicsPresenter presenter;

    private DataRecyclerAdapter adapter;


    @BindView(R.id.activity_main_data_recycler)
    RecyclerView dataRecycler;

    /**
     * onCreate - init
     * @param savedInstanceState If the Activity is being re-created from a previous saved state, this is the state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new ComicsPresenter(new NavigationManager(this),
                ComicShopApplication.getInstance().getDatabaseRepository());
        presenter.attachView(this);

        presenter.getComicsAPI();

    }

    /**
     * onDestroy - deinit
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();

        LogJam.d(this, "onDestroy");
    }

    /**
     * Initialize the contents of the Activity's standard options menu
     * @param menu The options menu in which you place your items
     * @return True to display the menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    /**
     * This hook is called whenever an item in your options menu is selected
     * @param item The menu item that was selected.
     * @return Return false to allow normal menu processing to proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_activity_legal_menu_item:
                //Load legal screen
                presenter.loadLegal();
                break;
            case R.id.main_activity_help_menu_item:
                //Load help screen
                presenter.loadHelp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Show received comic list
     * @param comicList received comic list
     */
    @Override
    public void showComics(List comicList) {

        //Create new adapter
        adapter = new DataRecyclerAdapter();

        //Create and set layout manager
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        dataRecycler.setLayoutManager(manager);
        //Set data to be shown
        adapter.setComicList(comicList);
        //Set adapter
        dataRecycler.setAdapter(adapter);

    }


}
