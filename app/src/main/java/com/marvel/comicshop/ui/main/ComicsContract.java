package com.marvel.comicshop.ui.main;


import com.marvel.comicshop.ui.base.BaseMvp;

import java.util.List;

/**
 * Created by mbenitez
 */
public interface ComicsContract extends BaseMvp {

    interface View extends BaseView {
        /**
         * Show received comics
         * @param comics received comics
         */
        void showComics(List comics);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * Get comics from the webserver
         */
        void getComicsAPI();

        /**
         * Load FAQs screen
         */
        void loadHelp();

        /**
         * Load Terms and Condition
         */
        void loadLegal();
    }
}
