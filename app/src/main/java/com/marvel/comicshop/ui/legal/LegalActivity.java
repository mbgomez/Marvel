package com.marvel.comicshop.ui.legal;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;

import com.marvel.comicshop.ComicShopApplication;
import com.marvel.comicshop.R;
import com.marvel.comicshop.managers.NavigationManager;
import com.marvel.comicshop.ui.base.BaseActivity;
import com.marvel.comicshop.utils.LogJam;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mbenitez
 */
public class LegalActivity extends BaseActivity implements LegalContract.View {

    //Local variables
    private LegalPresenter presenter;

    //Local views
    @BindView(R.id.legal_activity_is_accepted_checkbox)
    CheckBox legalAcceptedCheckBox;

    /**
     * onCreate - init
     * @param savedInstanceState If the Activity is being re-created from a previous saved state, this is the state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal);
        ButterKnife.bind(this);

        presenter = new LegalPresenter(ComicShopApplication.getInstance().getSharedPreferencesRepository(),
                new NavigationManager(this));
        presenter.attachView(this);

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
     * Triggered when accept button is clicked
     * @param view - button clicked
     */
    @Override
    public void legalAccept(View view) {

        if (legalAcceptedCheckBox.isChecked()) {

            //Load main screen
            presenter.loadMain();

        } else {

            //Show legal error
            Dialog dialog = new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(getResources().getString(R.string.legal_title))
                    .setMessage(getResources().getString(R.string.legal_error_message))
                    .setPositiveButton(getResources().getString(R.string.generic_dismiss), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    }).create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

        }

    }

}
