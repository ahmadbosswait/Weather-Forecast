package com.a7learn.ahmad.weatherforecast.Model;

import android.support.annotation.DrawableRes;

/**
 * Created by Mody Boss on 5/18/2018.
 */
public class AppFeature {

    public static final int ID_POSTS_ACTIVITY = 0;
    public static final int ID_OPEN_MAP_WEATHER = 1;
    public static final int ID_CONTACT_US = 2;
    public static final int ID_ABOUT_US = 3;

    private int id;
    private String title;
    @DrawableRes
    private int featureImage;
    private Class DestinationActivity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(int featureImage) {
        this.featureImage = featureImage;
    }

    public Class getDestinationActivity() {
        return DestinationActivity;
    }

    public void setDestinationActivity(Class destinationActivity) {
        DestinationActivity = destinationActivity;
    }
}
