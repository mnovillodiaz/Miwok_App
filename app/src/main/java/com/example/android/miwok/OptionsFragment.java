package com.example.android.miwok;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mdiaz on 02.07.17.
 */

public class OptionsFragment extends Fragment {


    public OptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.options, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.numbers_category)
    public void numbers_category(){
        goToFragment(new NumbersFragment());
    }

    @OnClick(R.id.family_category)
    public void family_category(){
        goToFragment(new FamilyFragment());
    }

    @OnClick(R.id.colors_category)
    public void colors_category(){
        goToFragment(new ColorsFragment());
    }

    @OnClick(R.id.phrases_category)
    public void phrases_category(){
        goToFragment(new PhrasesFragment());
    }


    private void goToFragment(Fragment frag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        boolean isTablet = getResources().getBoolean(R.bool.isLandscape);
        if (isTablet) {
            transaction.replace(R.id.details_framelayout, frag)
                    /*.addToBackStack(null)*/
                    .commit();
        } else {
            transaction.replace(R.id.options_framelayout, frag)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
