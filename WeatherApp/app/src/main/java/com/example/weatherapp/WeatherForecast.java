package com.example.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class WeatherForecast extends AppCompatActivity {
    private String weatherVal;
    private double tempVal;
    private String weatherIcon;
    private String locationVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        Bundle extras = getIntent().getExtras();

        TextView weatherNow = findViewById(R.id.todayWeatherID);
        weatherVal = extras.getString("WEATHER");
        String capital = weatherVal.substring(0,1).toUpperCase();
        String rest = weatherVal.substring(1);
        weatherNow.setText(capital + rest);

        TextView tempNow = findViewById(R.id.forecastValID);
        tempVal = extras.getDouble("TEMPERATURE");
        String tempString = Integer.toString((int) Math.round(tempVal));
        tempNow.setText(tempString + "° C");

        weatherIcon = extras.getString("ICON");
        locationVal = extras.getString("LOCATION");
    }
    public void moveToWeather(View view){

        Intent intent = new Intent(WeatherForecast.this, MainActivity.class);
        intent.putExtra("weather", weatherVal);
        intent.putExtra("temp", tempVal);
        intent.putExtra("location", locationVal);
        intent.putExtra("icon", weatherIcon);

        startActivity(intent);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        weatherVal = savedInstanceState.getString("WEATHER");
        tempVal = savedInstanceState.getDouble("TEMP");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("TEMP", tempVal);
        outState.putString("WEATHER", weatherVal);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
