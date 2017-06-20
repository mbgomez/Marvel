package com.marvel.comicshop.ui.help;

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
public class HelpFragment extends Fragment {


    //Local variables
    public static String HTML_HELP = "faqs.html";

    //Local views
    @Nullable
    @BindView(R.id.fragment_help_html_help_tv)
    TextView helpHtlmTV;

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
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        ButterKnife.bind(this, view);

        setupView();

        return view;
    }

    /**
     * Init view - Set frequent asked uestion from html file
     */
    private void setupView() {

        //Set FAQs from html file
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            helpHtlmTV.setText(Html.fromHtml(readHtmlFromFile(HTML_HELP), FROM_HTML_MODE_LEGACY));

        } else {

            helpHtlmTV.setText(Html.fromHtml(readHtmlFromFile(HTML_HELP)));

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
