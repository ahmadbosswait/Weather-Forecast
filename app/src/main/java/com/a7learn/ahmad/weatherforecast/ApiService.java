package com.a7learn.ahmad.weatherforecast;

import android.content.Context;
import android.util.Log;

import com.a7learn.ahmad.weatherforecast.Model.WeatherInfo;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mody Boss on 7/18/2018.
 */
public class ApiService {
    private static final String TAG = "ApiService";
    private Context context;

    public ApiService(Context context) {
        this.context = context;
    }

    public void getCurrentWeather(final OnWeatherInfoRecieved onWeatherInfoRecieved){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?q=Manchester&apikey=12d2930601d13246446efe738cdec2a2",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: " + response.toString());
                onWeatherInfoRecieved.onReceived(parseResponseToWeatherInfo(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error.toString());
                onWeatherInfoRecieved.onReceived(null);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(8000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    private WeatherInfo parseResponseToWeatherInfo(JSONObject response){
        WeatherInfo weatherInfo=new WeatherInfo();
        try {
            JSONArray weatherJsonArray=response.getJSONArray("weather");
            JSONObject weatherJsonObject=weatherJsonArray.getJSONObject(0);
            weatherInfo.setWeatherId(weatherJsonObject.getInt("id"));
            weatherInfo.setWeatherName(weatherJsonObject.getString("main"));
            weatherInfo.setWeatherDescription(weatherJsonObject.getString("description"));
            JSONObject mainJsonObject=response.getJSONObject("main");
            weatherInfo.setWeatherTemprature((float)mainJsonObject.getDouble("temp"));
            weatherInfo.setHumidity(mainJsonObject.getInt("humidity"));
            weatherInfo.setPressure(mainJsonObject.getInt("pressure"));
            weatherInfo.setMinTemprature((float) mainJsonObject.getDouble("temp_min"));
            weatherInfo.setMaxTemprature((float) mainJsonObject.getDouble("temp_max"));

            JSONObject windJsonObject=response.getJSONObject("wind");
            weatherInfo.setWindSpeed((float) windJsonObject.getDouble("speed"));
            weatherInfo.setWindDegree((float)windJsonObject.getDouble("deg"));

            return weatherInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public interface OnWeatherInfoRecieved{
        void onReceived(WeatherInfo weatherInfo);
    }

}
