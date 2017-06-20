package com.marvel.comicshop.navigation;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.marvel.comicshop.ui.help.HelpFragment;
import com.marvel.comicshop.ui.base.BaseActivity;
import com.marvel.comicshop.ui.main.ComicsActivity;
import com.marvel.comicshop.managers.NavigationManager;
import com.marvel.comicshop.ui.legal.LegalActivity;
import com.marvel.comicshop.ui.legal.LegalFragment;

/**
 * Created by mbenitez
 */
public enum BaseProjectNavRecord implements NavigationManager.NavRecord {

    //Navigations
    LEGAL(LegalActivity.class, LegalFragment.class, "Legal Info"),
    LEGAL_MENU(BaseActivity.class, LegalFragment.class, "Terms and Conditions"),
    HELP(BaseActivity.class, HelpFragment.class, "Help"),
    MAIN(ComicsActivity.class, null, null);

    //local variables
    private final Class<? extends Activity> activityClass;
    private final Class<? extends Fragment> fragmentClass;
    private final String title;
    private int flags;

    /**
     * Constructor - init variables
     * @param activityClass - Activity of the next navigation
     * @param fragmentClass - Fragment of the next navigation
     * @param title - Title of the screen in next navigation
     */
    BaseProjectNavRecord(Class<? extends Activity> activityClass, Class<? extends Fragment> fragmentClass
            , String title) {
        this.flags = -1;
        this.activityClass = activityClass;
        this.fragmentClass = fragmentClass;
        this.title = title;
    }

    /**
     * Get fragment class of the next navigation
     * @return fragment class
     */
    @Override
    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    /**
     * Get activity class of the next navigation
     * @return activity class
     */
    @Override
    public Class<? extends Context> getActivityClass() {
        return activityClass;
    }

    /**
     * Get title of the screen of the next navigation
     * @return Title of the screen
     */
    @Override
    public String getTitle() {

        return title;

    }

    /**
     * Get intent flags
     * @return intent flag
     */
    @Override
    public int getFlags() {
        return flags;
    }

    /**
     * Set intent flags
     * @param flags intent flag
     */
    @Override
    public void setFlags(int flags) {
        this.flags = flags;
    }

}
