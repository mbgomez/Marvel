package com.marvel.comicshop.ui.splash;

import android.os.Bundle;

import com.marvel.comicshop.ComicShopApplication;
import com.marvel.comicshop.R;
import com.marvel.comicshop.managers.NavigationManager;
import com.marvel.comicshop.ui.base.BaseActivity;
import com.marvel.comicshop.utils.LogJam;

import butterknife.ButterKnife;

/**
 * Created by mbenitez
 */
public class SplashActivity extends BaseActivity implements SplashContract.View {

    //Local variables
    private SplashContract.Presenter presenter;

    /**
     * onCreate - init
     * @param savedInstanceState If the Activity is being re-created from a previous saved state, this is the state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        presenter = new SplashPresenter(ComicShopApplication.getInstance().getSharedPreferencesRepository(),
                new NavigationManager(this));
        presenter.attachView(this);
        LogJam.d(this, "onCreate");
    }

    /**
     * onDestroy - deInit
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();

        LogJam.d(this, "onDestroy");
    }

}
