package com.marvel.comicshop.ui.legal;


import com.marvel.comicshop.ui.base.BaseMvp;

/**
 * Created by mbenitez
 */
public interface LegalContract extends BaseMvp {

    interface View extends BaseView {

        /**
         * Triggered when accept button is clicked
         * @param view - button clicked
         */
        void legalAccept(android.view.View view);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * Load main screen
         */
        void loadMain();
    }
}
