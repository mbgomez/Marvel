package com.marvel.comicshop.ui.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by mbenitez
 */
public class BasePresenter<V extends BaseMvp.BaseView> implements BaseMvp.BasePresenter<V> {

    /**
     * The Composite subscription.
     */
    private final CompositeDisposable compositeSubscription = new CompositeDisposable();
    /**
     * The View.
     */
    protected V view;

    /**
     * Constructor
     */
    protected BasePresenter() {
    }

    /**
     * Attach view
     * @param view the view
     */
    @Override
    public void attachView(V view) {
        this.view = view;
    }

    /**
     * Detach view
     */
    @Override
    public void detachView() {
        compositeSubscription.clear();
        view = null;
    }

    /**
     * Add subscription.
     *
     * @param subscription the subscription
     */
    protected void addSubscription(Disposable subscription) {
        this.compositeSubscription.add(subscription);
    }


}