package com.example.android.miwok;

import android.app.Fragment;
import android.content.Context;
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

    OnOptionClickListener listener;

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


    // Container Activity must implement this interface
    public interface OnOptionClickListener {
        public void onOptionClick(Fragment fragment);
    }

   //To be sure that the activity implements the OnOptionClick
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOptionClickListener) {
            listener = (OnOptionClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnOptionClickListener");
        }
    }

    @OnClick(R.id.numbers_category)
    public void numbers_category(){
        listener.onOptionClick(new NumbersFragment());
    }

    @OnClick(R.id.family_category)
    public void family_category(){
        listener.onOptionClick(new FamilyFragment());
    }

    @OnClick(R.id.colors_category)
    public void colors_category(){
        listener.onOptionClick(new ColorsFragment());
    }

    @OnClick(R.id.phrases_category)
    public void phrases_category(){
        listener.onOptionClick(new PhrasesFragment());
    }

}
