package com.marvel.comicshop.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.marvel.comicshop.R;
import com.marvel.comicshop.managers.NavigationManager;
import com.marvel.comicshop.managers.NavigationManager.NavRecord;


/**
 * Created by mbenitez
 */
public class BaseActivity extends AppCompatActivity {

    //Local variables
    protected NavRecord record;
    private NavigationManager navigationManager;

    /**
     * onCreate - Init fragment and title
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            record = (NavRecord) intent.getSerializableExtra(NavRecord.NAV_RECORD_ID);
            if (record != null) {

                //Start new navigation to set a possible new fragment
                navigationManager = new NavigationManager(this);
                navigationManager.handleRecord(record, savedInstanceState);

                //Set back button in toolbar
                if (record.getActivityClass().toString().equals(BaseActivity.class.toString())) {

                    setActionBarBackEnable(true);

                }

                //Set title in toolbar
                if (record.getTitle() != null) {

                    setActionBarTitle(record.getTitle());

                }

            }
        }

    }

    /**
     * onDestroy - deinit
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Set title in toolbar
     * @param title
     */
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    /**
     * Set backbutton in toolbar
     * @param enable visible when true
     */
    public void setActionBarBackEnable(boolean enable) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(enable);

    }

    /**
     * This hook is called whenever an item in your options menu is selected
     * @param item The menu item that was selected.
     * @return Return false to allow normal menu processing to proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
