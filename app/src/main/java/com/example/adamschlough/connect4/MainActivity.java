package com.example.adamschlough.connect4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, getResources().getString(R.string.Admob_AppId));

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        final Button buttonPlay = findViewById(R.id.buttonPlay);
        final Button buttonSettings = findViewById(R.id.buttonSettings);
        buttonPlay.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);
        editor.putString("Player Two Color", "blue").apply();
    }

    @Override
    public void onClick(View view){
        Intent i = new Intent();
        switch (view.getId()) {
            case R.id.buttonPlay:
                i = new Intent(this, GameActivity.class);
                break;
            case R.id.buttonSettings:
                i = new Intent(this, SettingsActivity.class);
                break;
            default:
                break;
        }
        startActivity(i);
    }

}
