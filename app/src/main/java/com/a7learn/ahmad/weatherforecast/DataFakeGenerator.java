package com.a7learn.ahmad.weatherforecast;

import android.content.Context;

import com.a7learn.ahmad.weatherforecast.Model.AppFeature;

import com.a7learn.ahmad.weatherforecast.View.AboutUs;
import com.a7learn.ahmad.weatherforecast.View.ContactUs;
import com.a7learn.ahmad.weatherforecast.View.WeatherSampleActivity;
import com.a7learn.ahmad.weatherforecast.View.WeatherWebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mody Boss on 7/18/2018.
 */

public class DataFakeGenerator {

    public static List<AppFeature> getAppFeatures(Context context) {
        List<AppFeature> appFeatures = new ArrayList<>();

        AppFeature appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_POSTS_ACTIVITY);
        appFeature.setTitle(context.getString(R.string.app_feature_latest_posts));
        appFeature.setFeatureImage(R.drawable.today);
        appFeature.setDestinationActivity(WeatherSampleActivity.class);
        appFeatures.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_OPEN_MAP_WEATHER);
        appFeature.setTitle(context.getString(R.string.app_feature_fashion));
        appFeature.setFeatureImage(R.drawable.open_map_weather);
        appFeature.setDestinationActivity(WeatherWebView.class);
        appFeatures.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_CONTACT_US);
        appFeature.setTitle(context.getString(R.string.app_feature_music_player));
        appFeature.setFeatureImage(R.drawable.contact_us);
        appFeature.setDestinationActivity(ContactUs.class);
        appFeatures.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_ABOUT_US);
        appFeature.setTitle(context.getString(R.string.app_feature_video_player));
        appFeature.setFeatureImage(R.drawable.about_uss);
        appFeature.setDestinationActivity(AboutUs.class);
        appFeatures.add(appFeature);

        return appFeatures;
    }
}
