package com.example.adamschlough.connect4;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = getSharedPreferences("com.example.adamschlough.connect4", Context.MODE_PRIVATE);
    }

    public void onRadioButtonClicked(View view) {
        editor = sharedPreferences.edit();
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.playerOneRed:
                if (checked)
                    editor.putInt("Player One Color", R.color.colorRed);
                    editor.apply();
                    break;
            case R.id.playerOneBlue:
                if (checked)
                    editor.putInt("Player One Color", R.color.colorBlue);
                    editor.apply();
                    break;
            case R.id.playerOneYellow:
                if (checked)
                    editor.putInt("Player One Color", R.color.colorYellow);
                    editor.apply();
                break;
            case R.id.playerOneGreen:
                if (checked)
                    editor.putInt("Player One Color", R.color.colorGreen);
                    editor.apply();
                    break;
            case R.id.playerOnePurple:
                if (checked)
                    editor.putInt("Player One Color", R.color.colorPurple);
                    editor.apply();
                    break;
            case R.id.playerTwoRed:
                if (checked)
                    editor.putInt("Player Two Color", R.color.colorRed);
                    editor.apply();
                    break;
            case R.id.playerTwoBlue:
                if (checked)
                    editor.putInt("Player Two Color", R.color.colorBlue);
                    editor.apply();
                    break;
            case R.id.playerTwoYellow:
                if (checked)
                    editor.putInt("Player Two Color", R.color.colorYellow);
                    editor.apply();
                    break;
            case R.id.playerTwoGreen:
                if (checked)
                    editor.putInt("Player Two Color", R.color.colorGreen);
                    editor.apply();
                    break;
            case R.id.playerTwoPurple:
                if (checked)
                    editor.putInt("Player Two Color", R.color.colorPurple);
                    editor.apply();
                    break;
        }
    }
}
