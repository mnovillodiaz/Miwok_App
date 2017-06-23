package com.example.android.miwok;

/**
 * Created by mdiaz on 17/05/17.
 */

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    //String default translation
    private String mDefaultTranslation;
    //String miwok translation
    private String mMiwokTranslation;
    //String sound
    private int mSound;
    //image
    private int mImageResourceId = NO_IMAGE_PROVIDED;


    /**
    Constructor to create a new Word object (without image)
         @param defaultTranslation
         @param miwokTranslation
         @param sound
     * */

    public Word(String defaultTranslation, String miwokTranslation, int sound) {
        mDefaultTranslation= defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mSound = sound;
    }

    /**
    Constructor to create a new Word object with image
         @param defaultTranslation
         @param miwokTranslation
         @param imageResourceId
         @param sound
     * */

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int sound) {
        mDefaultTranslation= defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mSound = sound;
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

    public int getSound() {
        return mSound;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mSound=" + mSound +
                ", mImageResourceId=" + mImageResourceId +
                '}';
    }
}
