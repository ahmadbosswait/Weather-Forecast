package com.a7learn.ahmad.weatherforecast;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;



import com.a7learn.ahmad.weatherforecast.Model.WeatherInfo;

/**
 * Implementation of App Widget functionality.
 */


public class WeatherWidget extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId, WeatherInfo weatherInfo) {
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
        remoteViews.setTextViewText(R.id.text_weather_temp, String.valueOf((int) (weatherInfo.getWeatherTemprature() - 272.15)) + "\u00b0");

        switch (weatherInfo.getWeatherId() / 100) {
            case 2:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.thunderstorm);
                break;
            case 3:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.drizzle);
                break;
            case 5:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.rain);
                break;
            case 6:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.snow);
                break;
            case 7:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.mist);
                break;
            case 8:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.clear_sky);
                break;
            default:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.clear_sky);
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        ApiService apiService = new ApiService(context);
        apiService.getCurrentWeather(new ApiService.OnWeatherInfoRecieved() {
            @Override
            public void onReceived(WeatherInfo weatherInfo) {
                if (weatherInfo != null) {
                    for (int appWidgetId : appWidgetIds) {
                        updateAppWidget(context, appWidgetManager, appWidgetId, weatherInfo);
                    }
                }

            }
        });
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

