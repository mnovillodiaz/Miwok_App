/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OptionsFragment.OnOptionClickListener{

    @Override
    public void onOptionClick(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        boolean isTablet = getResources().getBoolean(R.bool.isLandscape);
        if (isTablet) {
            transaction.replace(R.id.details_framelayout, fragment)
                    /*.addToBackStack(null)*/
                    .commit();
        } else {
            transaction.replace(R.id.options_framelayout, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        // displaying the options fragment inside
        setContentView(R.layout.activity_main);
        Fragment frag = new OptionsFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.options_framelayout, frag)
                .commit();
    }

/*    @Override
    public void onBackPressed() {
        boolean isTablet = getResources().getBoolean(R.bool.isLandscape);
        if(isTablet){
            super.onBackPressed();
        }
        else if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }*/

}
