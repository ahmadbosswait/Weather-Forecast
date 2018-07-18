package com.a7learn.ahmad.weatherforecast.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a7learn.ahmad.weatherforecast.ApiService;
import com.a7learn.ahmad.weatherforecast.R;
import com.a7learn.ahmad.weatherforecast.Model.WeatherInfo;

public class WeatherSampleActivity extends AppCompatActivity implements ApiService.OnWeatherInfoRecieved {
    private static final String TAG = "WeatherSampleActivity";
    private ApiService apiService;
    private ProgressBar progressBar;
    private TextView txtWeatherName;
    private TextView txtWeatherDescription;
    private TextView txtTemp;
    private TextView txtHumidity;
    private TextView txtPressure;
    private TextView txtMinTemp;
    private TextView txtMaxTemp;
    private TextView txtWindSpeed;
    private TextView txtWindDegree;
    private TextView txtLocation;
    private TextView txtTime;
    private ImageView imgIconWeather;
    private ImageView imgMax;
    private ImageView imgMin;
    private ImageView imgWindDegree;
    private ImageView imgHumidity;
    private ImageView imgPressure;
    private ImageView imgWindSpeed;
    private ImageView imgLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_sample);
        apiService = new ApiService(this);

        initViews();

        Button btnSendRequest = (Button) findViewById(R.id.btn_send_request);
        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.getCurrentWeather(WeatherSampleActivity.this);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        imgIconWeather = (ImageView) findViewById(R.id.icon_weather);
        txtWeatherName = (TextView) findViewById(R.id.txt_weather_name);
        txtTemp = (TextView) findViewById(R.id.txt_temperature);
        txtMaxTemp = (TextView) findViewById(R.id.txt_max_temp);
        txtMinTemp = (TextView) findViewById(R.id.txt_min_temp);
        txtWindSpeed = (TextView) findViewById(R.id.txt_wind_speed);
        txtWindDegree = (TextView) findViewById(R.id.txt_wind_degree);
        txtHumidity = (TextView) findViewById(R.id.txt_humidity);
        txtPressure = (TextView) findViewById(R.id.txt_pressure);
        txtLocation = (TextView) findViewById(R.id.txt_location);
        txtTime = (TextView) findViewById(R.id.txt_time);

        imgMax = (ImageView) findViewById(R.id.icon_max);
        imgMin = (ImageView) findViewById(R.id.icon_min);
        imgWindDegree = (ImageView) findViewById(R.id.icon_wind_degree);
        imgHumidity = (ImageView) findViewById(R.id.icon_humidity);
        imgPressure = (ImageView) findViewById(R.id.icon_pressure);
        imgWindSpeed = (ImageView) findViewById(R.id.icon_wind_speed);
        imgLocation = (ImageView) findViewById(R.id.icon_location);

        //txtWeatherDescription = (TextView) findViewById(R.id.txt_weather_description);

    }


    @Override
    public void onReceived(WeatherInfo weatherInfo) {
        if (weatherInfo != null) {
            //show information to user
            switch (weatherInfo.getWeatherId() / 100) {
                case 2:
                    imgIconWeather.setImageResource(R.drawable.thunderstorm);
                    break;
                case 3:
                    imgIconWeather.setImageResource(R.drawable.drizzle);
                    break;
                case 5:
                    imgIconWeather.setImageResource(R.drawable.rain);
                    break;
                case 6:
                    imgIconWeather.setImageResource(R.drawable.snow);
                    break;
                case 7:
                    imgIconWeather.setImageResource(R.drawable.mist);
                    break;
                case 8:
                    imgIconWeather.setImageResource(R.drawable.clear_sky);
                    break;
                default:
                    imgIconWeather.setImageResource(R.drawable.clear_sky);
            }

            txtWeatherName.setText(weatherInfo.getWeatherName());
            //txtWeatherDescription.setText("توضیحات: " + weatherInfo.getWeatherDescription());
            txtTemp.setText(String.valueOf((int) (weatherInfo.getWeatherTemprature() - 272.15)) + "\u00b0");
            txtMaxTemp.setText( String.valueOf((int)(weatherInfo.getMaxTemprature() - 272.15)) + "\u00b0");
            txtMinTemp.setText( String.valueOf((int)(weatherInfo.getMinTemprature() - 272.15)) + "\u00b0");
            txtHumidity.setText(String.valueOf(weatherInfo.getHumidity()));
            txtPressure.setText(String.valueOf(weatherInfo.getPressure()));
            txtWindSpeed.setText(String.valueOf(weatherInfo.getWindSpeed()));
            txtWindDegree.setText(String.valueOf(weatherInfo.getWindDegree()));
            txtLocation.setText("Manchester, United Kingdom");
            txtTime.setText("10:00");


            imgMax.setImageResource(R.drawable.ic_arrow_upward_black_24dp);
            imgMin.setImageResource(R.drawable.ic_arrow_downward_black_24dp);
            imgWindDegree.setImageResource(R.drawable.ic_toys_black_24dp);
            imgHumidity.setImageResource(R.drawable.ic_grain_black_24dp);
            imgPressure.setImageResource(R.drawable.ic_input_black_24dp);
            imgWindSpeed.setImageResource(R.drawable.ic_fast_forward_black_24dp);
            imgLocation.setImageResource(R.drawable.ic_add_location_black_24dp);
        } else {
            Toast.makeText(this, "خطا در دریافت اطلاعات", Toast.LENGTH_LONG).show();
        }
        progressBar.setVisibility(View.INVISIBLE);
    }
}
