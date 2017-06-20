package com.marvel.comicshop.managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.marvel.comicshop.R;
import com.marvel.comicshop.utils.LogJam;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mbenitez
 */
public class NavigationManager {

    //local variables
    private Context context;

    /**
     * Constructor - init variables
     * @param context Activity context
     */
    public NavigationManager(Context context) {

        this.context = context;

    }

    /**
     * Handle next navigation
     * @param navRecord next navigation
     */
    public void handleRecord(@NonNull NavRecord navRecord) {
        handleRecord(navRecord, null);
    }

    /**
     * Handle next navigation with bundle
     * @param record next navigation
     * @param bundle bundle to be sent to the next screen
     */
    public void handleRecord(@NonNull NavRecord record, Bundle bundle) {
        if (context.getClass() == record.getActivityClass()) {
            //Handle next navigation creating a fragment
            replaceFragment(record, bundle);
        } else {
            //Handle next navigation starting a new activity.
            startActivity(record, bundle);
        }
    }

    /**
     * Set next navigation using a fragment
     * @param record next navigation
     * @param bundle bundle to be sent to the next screen
     */
    private void replaceFragment(NavRecord record, Bundle bundle) {
        Class<? extends Fragment> fragmentClass = record.getFragmentClass();
        LogJam.d("NavigationManager: FRAGMENT", record.toString());
        FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
        if (fragmentClass != null) {
            //Check if the fragment already exist
            Fragment fragment = fm.findFragmentByTag(fragmentClass.getCanonicalName());
            if (fragment == null) {
                //Create a new fragment
                fragment = Fragment.instantiate(context, fragmentClass.getCanonicalName(), bundle);
            }
            //Set fragment
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            @SuppressLint("RestrictedApi") List<Fragment> fragments = fm.getFragments();
            if (fragments != null && fragments.size() > 0) {
                fragmentTransaction.addToBackStack(fragment.getClass().getCanonicalName());
            }
            fragmentTransaction.replace(R.id.container, fragment, fragment.getClass().getCanonicalName()).commit();

        }
    }

    /**
     * Handle next navigation starting a new activity.
     * @param record next navigation
     * @param bundle bundle to be sent to the next screen
     */
    private void startActivity(NavRecord record, Bundle bundle) {
        LogJam.d("NavigationManager: INTENT", record.toString());
        Intent intent = new Intent(context, record.getActivityClass());
        if (bundle == null) {
            bundle = new Bundle();
        }

        bundle.putSerializable(NavRecord.NAV_RECORD_ID, record);
        if (!(record.getFlags() == -1)) {
            intent.setFlags(record.getFlags());
        }
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    public interface NavRecord extends Serializable {

        String NAV_RECORD_ID = "nav_record_id";

        Class<? extends Fragment> getFragmentClass();

        Class<? extends Context> getActivityClass();

        String getTitle();

        int getFlags();

        void setFlags(int flags);
    }

}
