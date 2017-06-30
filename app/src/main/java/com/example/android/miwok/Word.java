package com.example.android.miwok;

/**
 * Created by mdiaz on 17/05/17.
 */

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    //String default translation
    private String defaultTranslation;
    //String miwok translation
    private String miwokTranslation;
    //String sound
    private int sound;
    //image
    private int imageResourceId = NO_IMAGE_PROVIDED;


    /**
    Constructor to create a new Word object (without image)
         @param defaultTranslation
         @param miwokTranslation
         @param sound
     * */

    public Word(String defaultTranslation, String miwokTranslation, int sound) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.sound = sound;
    }

    /**
    Constructor to create a new Word object with image
         @param defaultTranslation
         @param miwokTranslation
         @param imageResourceId
         @param sound
     * */

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int sound) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceId = imageResourceId;
        this.sound = sound;
    }

    //getters
    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public String getMiwokTranslation(){
        return miwokTranslation;
    }

    public int getImageResourceId(){
        return imageResourceId;
    }

    public int getSound() {
        return sound;
    }

    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + defaultTranslation + '\'' +
                ", mMiwokTranslation='" + miwokTranslation + '\'' +
                ", mSound=" + sound +
                ", mImageResourceId=" + imageResourceId +
                '}';
    }
}
