package com.example.android.miwok;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mdiaz on 02.07.17.
 */

public class OptionsFragment  extends Fragment implements View.OnClickListener {

    public OptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.options, container, false);

        TextView numbersTextView = (TextView) rootView.findViewById(R.id.numbers_category);
        TextView familyTextView = (TextView) rootView.findViewById(R.id.family_category);
        TextView colorsTextView = (TextView) rootView.findViewById(R.id.colors_category);
        TextView phrasesTextView = (TextView) rootView.findViewById(R.id.phrases_category);

        numbersTextView.setOnClickListener(this);
        familyTextView.setOnClickListener(this);
        colorsTextView.setOnClickListener(this);
        phrasesTextView.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.numbers_category:
                goToFragment(new NumbersFragment());
                break;
            case R.id.family_category:
                goToFragment(new FamilyFragment());
                break;
            case R.id.colors_category:
                goToFragment(new ColorsFragment());
                break;
            case R.id.phrases_category:
                goToFragment(new PhrasesFragment());
                break;
        }
    }

    private void goToFragment(Fragment frag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        boolean isTablet = getResources().getBoolean(R.bool.isLandscape);
        if (isTablet) {
            transaction.replace(R.id.details_framelayout, frag);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            transaction.replace(R.id.options_framelayout, frag);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }
}
