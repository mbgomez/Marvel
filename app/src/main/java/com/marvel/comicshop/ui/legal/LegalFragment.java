package com.marvel.comicshop.ui.legal;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.marvel.comicshop.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

/**
 * Created by mbenitez
 */
public class LegalFragment extends Fragment {

    //Local variables
    public static String HTML_LEGAL = "legal.html";
    //Local views
    @Nullable
    @BindView(R.id.fragment_legal_html_legal_tv)
    TextView legalHtlmTV;

    /**
     * onCreateView - Init view
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment,
     * @param container this is the parent view that the fragment's UI should be attached to
     * @param savedInstanceState this fragment is being re-constructed from a previous saved state as given here
     * @return Return the View for the fragment's UI.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_legal, container, false);
        ButterKnife.bind(this, view);

        setupView();

        return view;
    }

    /**
     * Init view - Set tems and conditons from html file
     */
    private void setupView() {

        //Set Legal from html file
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            legalHtlmTV.setText(Html.fromHtml(readHtmlFromFile(HTML_LEGAL), FROM_HTML_MODE_LEGACY));

        } else {

            legalHtlmTV.setText(Html.fromHtml(readHtmlFromFile(HTML_LEGAL)));

        }

    }

    /**
     * Read a file stored in assets
     * @param file file name
     * @return Full file
     */
    private String readHtmlFromFile(String file) {

        AssetManager mgr = getActivity().getAssets();
        int i;
        try {
            InputStream inputStream = mgr.open(file, AssetManager.ACCESS_BUFFER);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();

            return byteArrayOutputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


}
