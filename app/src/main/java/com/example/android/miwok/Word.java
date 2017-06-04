package com.example.android.miwok;

/**
 * Created by mdiaz on 17/05/17.
 */

public class Word {

    //String default translation
    private String mDefaultTranslation;

    //String miwok translation
    private String mMiwokTranslation;

    //image
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;


     /*
    Constructor to create a new Word object (without image)
         @param defaultTranslation
         @param miwokTranslation
     * */

    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation= defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /*
    Constructor to create a new Word object with image
         @param defaultTranslation
         @param miwokTranslation
         @param imageResourceId
     * */

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId) {
        mDefaultTranslation= defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
    }

    //getters
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
