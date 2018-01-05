package com.example.android.miwok;

/**
 * Created by jirig on 19.11.2017.
 */

public class Word {

    private String miwokWord;
    private String defaultWord;
    private int imageResourceID = 0;
    private int soundResourceID;

    public Word(String defaultWord,String miwokWord, int soundID){
        this.miwokWord = miwokWord;
        this.defaultWord = defaultWord;
        this.soundResourceID = soundID;
    }

    public Word(String defaultWord,String miwokWord, int imageID, int soundID){
        this.miwokWord = miwokWord;
        this.defaultWord = defaultWord;
        this.imageResourceID = imageID;
        this.soundResourceID = soundID;
    }


    public String getMiwokWord() {
        return miwokWord;
    }

    @Override
    public String toString() {
        return "Word{" +
                "miwokWord='" + miwokWord + '\'' +
                ", defaultWord='" + defaultWord + '\'' +
                ", imageResourceID=" + imageResourceID +
                ", soundResourceID=" + soundResourceID +
                '}';
    }

    public String getDefaultWord() {
        return defaultWord;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public int getSoundResourceID() {
        return soundResourceID;
    }
}
