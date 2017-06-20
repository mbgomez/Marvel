package com.marvel.comicshop.ui.base;

/**
 * Created by mbenitez
 */
public interface BaseMvp {
    /**
     * The interface Base view.
     */
    interface BaseView {

    }

    /**
     * The interface Base presenter.
     *
     * @param <V> the type parameter
     */
    interface BasePresenter<V extends BaseView> {
        /**
         * Attach view.
         *
         * @param view the view
         */
        void attachView(V view);

        /**
         * Detach view.
         */
        void detachView();
    }
}
