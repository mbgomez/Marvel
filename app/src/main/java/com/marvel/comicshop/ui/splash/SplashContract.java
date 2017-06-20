package com.marvel.comicshop.ui.splash;


import com.marvel.comicshop.ui.base.BaseMvp;

/**
 * Created by mbenitez
 */
public interface SplashContract extends BaseMvp {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {
        //Check if term and conditions were accepted
        void isLegalComplete();
        //Load main screen
        void loadMain();
        //Load Legal screen
        void loadLegal();
    }

}
